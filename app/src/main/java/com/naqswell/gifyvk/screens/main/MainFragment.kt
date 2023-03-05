package com.naqswell.gifyvk.screens.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.naqswell.gifyvk.MyApplication
import com.naqswell.gifyvk.R
import com.naqswell.gifyvk.databinding.FragmentMainBinding
import com.naqswell.gifyvk.screens.hideKeyboard
import com.naqswell.gifyvk.screens.snack
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter by lazy {
        MainAdapter { gifId ->
            findNavController().navigate(MainFragmentDirections.actionToDetail(gifId))
        }
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.provideFactory(((requireActivity().application) as MyApplication).giphyRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.apply {
            initObservers()
            initAdapter()
            initRecyclerView()
        }.root
    }

    private fun initObservers() {
        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getGifsList().observe(viewLifecycleOwner) {
                    mainAdapter.submitData(lifecycle, it)
                }
            }

            searchInputLayout.editText?.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchInputLayout.hideKeyboard()
                    return@OnEditorActionListener true
                }
                false
            })

            searchInputLayout.setEndIconOnClickListener {
                rvGifs.scrollToPosition(0)
                val searchQuery = searchInputLayout.editText?.text.toString()

                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getGifsList(searchQuery).observe(viewLifecycleOwner) {
                        mainAdapter.submitData(lifecycle, it)
//                        rvGifs.adapter = mainAdapter
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        mainAdapter.addLoadStateListener { loadState ->
            val snackBar = activity?.window?.decorView?.rootView?.snack(
                resources.getString(R.string.check_your_internet_connection),
            )

            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                snackBar?.show()
            }
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            rvGifs.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = mainAdapter
            }
        }
    }
}
package com.naqswell.gifyvk.screens.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.naqswell.gifyvk.MyApplication
import com.naqswell.gifyvk.R
import com.naqswell.gifyvk.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var gifId: String

    private val detailViewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(((requireActivity().application) as MyApplication).giphyRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        gifId = args.gifId
        detailViewModel.fetchGifData(gifId)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        with(binding) {
            detailViewModel.gif.observe(viewLifecycleOwner) { gifData ->
                gifData.data.title?.let { data ->
                    txtTitleLabel.visibility = View.VISIBLE
                    txtTitle.apply {
                        text = data
                        visibility = View.VISIBLE
                    }
                }

                gifData.data.source?.let { source ->
                    txtSourceLabel.visibility = View.VISIBLE
                    txtSource.apply {
                        text = source
                        visibility = View.VISIBLE
                    }

                    txtSource.setOnClickListener {
                        if (URLUtil.isValidUrl(source)) {
                            val internetIntent = Intent(Intent.ACTION_VIEW)
                            internetIntent.data = Uri.parse(source)
                            startActivity(internetIntent)
                        } else {
                            Snackbar.make(txtSource, resources.getString(R.string.wrong_url), Snackbar.LENGTH_SHORT)
                                .setAction("Ok") {}
                                .show()
                        }
                    }
                }

                val imageData = gifData.data.images
                imgGif.apply {
                    Glide.with(this.context)
                        .asGif()
                        .load(imageData?.fixedHeight?.url)
                        .fitCenter()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imgGif)
                }
            }
        }
    }


}
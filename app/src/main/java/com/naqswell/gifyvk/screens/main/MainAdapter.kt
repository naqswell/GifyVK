package com.naqswell.gifyvk.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.naqswell.gifyvk.data.entities.search.GifResponse
import com.naqswell.gifyvk.databinding.RvGifItemBinding

class MainAdapter(private val onClickListener: (gifId: String) -> Unit) :
    PagingDataAdapter<GifResponse, MainAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RvGifItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class ViewHolder(private val binding: RvGifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                item?.let { gifData ->
                    gifData.id?.let { it -> onClickListener(it) }
                }
            }
        }

        fun bind(item: GifResponse) {
            with(binding) {
                Glide.with(itemView.context)
                    .asGif()
                    .load(item.images?.fixedHeight?.url)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgGif)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<GifResponse>() {
        override fun areItemsTheSame(oldItem: GifResponse, newItem: GifResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifResponse, newItem: GifResponse): Boolean {
            return oldItem == newItem
        }
    }
}
package com.example.newsfeedapp.ui.newsfeed

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcomentry.moonlight.binding.binding
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.ItemNewsFeedImageBinding
import com.example.newsfeedapp.model.entity.ImageEntity

class NewsImageViewHolder(val binding: ItemNewsFeedImageBinding) :
    RecyclerView.ViewHolder(binding.root)

class ImagePagerAdapter(
    private val onImageClick: (ImageEntity) -> Unit
) : RecyclerView.Adapter<NewsImageViewHolder>() {
    private var items: MutableList<ImageEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsImageViewHolder {
        return NewsImageViewHolder(parent.binding(R.layout.item_news_feed_image, false))
    }

    override fun onBindViewHolder(holder: NewsImageViewHolder, position: Int) {
        holder.binding.image = items[position]
        holder.binding.root.setOnClickListener {
            onImageClick(items[position])
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setImages(images: List<ImageEntity>) {
        items.clear()
        items.addAll(images)
        notifyDataSetChanged()
    }

}
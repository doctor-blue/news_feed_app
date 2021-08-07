package com.example.newsfeedapp.ui.newsfeed

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewpager2.widget.ViewPager2
import com.devcomentry.moonlight.binding.binding
import com.example.newsfeedapp.R
import com.example.newsfeedapp.base.BaseViewHolder
import com.example.newsfeedapp.databinding.ItemNewsFeed1Binding
import com.example.newsfeedapp.databinding.ItemNewsFeed2Binding
import com.example.newsfeedapp.databinding.ItemNewsFeed3Binding
import com.example.newsfeedapp.databinding.ItemNewsFeedVideoBinding
import com.example.newsfeedapp.model.NewsFeedModel

private val DIFF_UTIL = object : DiffUtil.ItemCallback<NewsFeedModel>() {
    override fun areItemsTheSame(
        oldItem: NewsFeedModel,
        newItem: NewsFeedModel
    ): Boolean {
        return oldItem.documentId == newItem.documentId
    }

    override fun areContentsTheSame(
        oldItem: NewsFeedModel,
        newItem: NewsFeedModel
    ): Boolean {
        return oldItem.title == newItem.title
    }

}

class NewsFeedAdapter(
    private val onClick: ((NewsFeedModel) -> Unit)
) : ListAdapter<NewsFeedModel, BaseViewHolder<NewsFeedModel>>(DIFF_UTIL) {
    companion object {
        const val NEWS_FEED_1 = 0
        const val NEWS_FEED_2 = 1
        const val NEWS_FEED_VIDEO = 3
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].contentType == "video" && currentList[position].content != null) {
            NEWS_FEED_VIDEO
        } else {
            position % 3
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<NewsFeedModel> {
        return when (viewType) {
            NEWS_FEED_1 -> NewsFeedViewHolder1(
                parent.binding(R.layout.item_news_feed_1)
            )
            NEWS_FEED_2 -> NewsFeedViewHolder2(
                parent.binding(R.layout.item_news_feed_2)
            )
            NEWS_FEED_VIDEO -> NewsFeedVideoViewHolder(
                parent.binding(R.layout.item_news_feed_video)
            )
            else -> NewsFeedViewHolder3(
                parent.binding(R.layout.item_news_feed_3)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<NewsFeedModel>, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class NewsFeedViewHolder1(private val binding: ItemNewsFeed1Binding) :
        BaseViewHolder<NewsFeedModel>(binding.root) {
        private var item: NewsFeedModel? = null

        init {
            binding.root.setOnClickListener {
                item?.let {
                    onClick(it)
                }
            }
        }

        override fun onBind(item: NewsFeedModel) {
            this.item = item
            binding.newsFeed = item
            binding.executePendingBindings()
        }

    }


    inner class NewsFeedViewHolder2(private val binding: ItemNewsFeed2Binding) :
        BaseViewHolder<NewsFeedModel>(binding.root) {
        private var item: NewsFeedModel? = null

        init {
            binding.root.setOnClickListener {
                item?.let {
                    onClick(it)
                }
            }
        }

        override fun onBind(item: NewsFeedModel) {
            this.item = item
            binding.newsFeed = item
        }

    }

    inner class NewsFeedViewHolder3(private val binding: ItemNewsFeed3Binding) :
        BaseViewHolder<NewsFeedModel>(binding.root) {
        private var item: NewsFeedModel? = null

        init {
            binding.root.setOnClickListener {
                item?.let {
                    onClick(it)
                }
            }

            binding.newsFeedViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    item?.let { news ->
                        news.images?.let {
                            binding.page = "${position + 1}/${it.size}"
                        }
                    }
                }
            })
        }

        override fun onBind(item: NewsFeedModel) {
            this.item = item

            with(binding) {
                newsFeed = item

                item.images?.let {
                    binding.page = "${1}/${it.size}"
                }

                val pagerAdapter = ImagePagerAdapter {
                    onClick(item)
                }
                newsFeedViewPager.adapter = pagerAdapter

                item.images?.let { pagerAdapter.setImages(images = it) }
            }
        }

    }

    inner class NewsFeedVideoViewHolder(private val binding: ItemNewsFeedVideoBinding) :
        BaseViewHolder<NewsFeedModel>(binding.root) {
        private var item: NewsFeedModel? = null

        init {
            binding.root.setOnClickListener {
                item?.let {
                    onClick(it)
                }
            }

            binding.imgThumbnail.setOnClickListener {
                binding.videoView.start()
                binding.imgThumbnail.visibility = View.GONE
                binding.imgPause.visibility = View.GONE
            }
        }

        override fun onBind(item: NewsFeedModel) {
            this.item = item
//             val mediaControls= MediaController(binding.root.context)
//                binding.videoView.setMediaController(mediaControls)

            with(binding) {
                newsFeed = item
            }

        }

    }
}
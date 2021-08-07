package com.example.newsfeedapp.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewpager2.widget.ViewPager2
import com.devcomentry.moonlight.binding.binding
import com.example.newsfeedapp.R
import com.example.newsfeedapp.base.BaseViewHolder
import com.example.newsfeedapp.databinding.ItemSection1Binding
import com.example.newsfeedapp.databinding.ItemSection2Binding
import com.example.newsfeedapp.databinding.ItemSection3Binding
import com.example.newsfeedapp.model.Markup
import com.example.newsfeedapp.model.Section


private val DIFF_UTIL = object : DiffUtil.ItemCallback<Section>() {
    override fun areItemsTheSame(
        oldItem: Section,
        newItem: Section
    ): Boolean {
        return oldItem.sectionContent == newItem.sectionContent
    }

    override fun areContentsTheSame(
        oldItem: Section,
        newItem: Section
    ): Boolean {
        return oldItem.sectionContent == newItem.sectionContent
    }

}

class SectionAdapter(
    private val onClick: ((Section) -> Unit)
) : ListAdapter<Section, BaseViewHolder<Section>>(DIFF_UTIL) {
    companion object {
        const val SECTION_1 = 1
        const val SECTION_2 = 2
        const val SECTION_3 = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].sectionType) {
            1 -> SECTION_1
            2 -> SECTION_2
            else -> SECTION_3
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Section> {
        return when (viewType) {
            SECTION_1 -> SectionWebViewHolder(
                parent.binding(R.layout.item_section_1)
            )
            SECTION_2 -> Section2ViewHolder(
                parent.binding(R.layout.item_section_2)
            )
            else -> Section3ViewHolder(
                parent.binding(R.layout.item_section_3)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Section>, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class SectionWebViewHolder(private val binding: ItemSection1Binding) :
        BaseViewHolder<Section>(binding.root) {
        private var markupList: List<Markup>? = null

        init {
            binding.webViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    markupList?.let { list ->
                        binding.page = "${position + 1}/${list.size}"
                    }
                }
            })
        }

        override fun onBind(item: Section) {
            with(binding) {
                section = item
                executePendingBindings()
                item.sectionContent?.let { content ->
                    content.markups?.let { markupEntities ->

                        val markupList = markupEntities.filter {
                            it.markupType == 4 && !it.href.isNullOrBlank()
                        }

                        this@SectionWebViewHolder.markupList = markupList

                        if (markupList.isEmpty()) {

                            webViewPager.visibility = View.GONE
                            txtPage.visibility = View.GONE

                        } else {

                            val webPagerAdapter = WebPagerAdapter()
                            webViewPager.adapter = webPagerAdapter
                            webPagerAdapter.setMarkupEntities(markupList)
                            page = "${1}/${markupList.size}"

                        }
                    }


                }

            }
        }

    }

    inner class Section2ViewHolder(private val binding: ItemSection2Binding) :
        BaseViewHolder<Section>(binding.root) {
        init {
            binding.imgThumbnail.setOnClickListener {
                binding.videoView.start()
                binding.imgThumbnail.visibility = View.GONE
                binding.imgPause.visibility = View.GONE
            }
        }
        override fun onBind(item: Section) {
//            val mediaControls= MediaController(binding.root.context)
//            binding.videoView.setMediaController(mediaControls)

            with(binding) {
                section = item
            }
        }

    }

    inner class Section3ViewHolder(private val binding: ItemSection3Binding) :
        BaseViewHolder<Section>(binding.root) {

        override fun onBind(item: Section) {
            with(binding) {
                section = item
            }
        }

    }

}
package com.example.newsfeedapp.ui.newsfeed

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcomentry.moonlight.binding.BindingFragment
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.FragmentNewFeedBinding
import com.example.newsfeedapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedFragment : BindingFragment<FragmentNewFeedBinding>(R.layout.fragment_new_feed) {

    private val newsFeedViewModel: NewsFeedViewModel by activityViewModels()
    private val newsFeedAdapter: NewsFeedAdapter by lazy {
        NewsFeedAdapter {
            //on click
        }
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        binding {
            rvNewsFeed.adapter = newsFeedAdapter
            rvNewsFeed.layoutManager = LinearLayoutManager(requireContext())
            rvNewsFeed.setHasFixedSize(true)

            rvNewsFeed.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        newsFeedViewModel.newsFeedLiveData.observe(viewLifecycleOwner, {
            binding.pbNewsFeed.isVisible = it is Resource.Loading

            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.d(
                        "NewsFeedFragment",
                        "data: " + (it.data.itemsEntity?.get(0)!!.contentType ?: "")
                    )
                    newsFeedAdapter.submitList(it.data.itemsEntity)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun initEvents() {
        super.initEvents()
        binding.sfNewsFeed.setOnRefreshListener {
            newsFeedViewModel.refresh()
            binding.sfNewsFeed.isRefreshing = false
        }
    }
}
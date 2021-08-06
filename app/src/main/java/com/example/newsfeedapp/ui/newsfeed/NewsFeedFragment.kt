package com.example.newsfeedapp.ui.newsfeed

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcomentry.moonlight.binding.BindingFragment
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.FragmentNewFeedBinding
import com.example.newsfeedapp.ui.home.HomeFragment
import com.example.newsfeedapp.ui.home.HomeFragmentDirections
import com.example.newsfeedapp.utils.Resource
import com.example.newsfeedapp.utils.isNetworkConnected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedFragment : BindingFragment<FragmentNewFeedBinding>(R.layout.fragment_new_feed) {

    private val newsFeedViewModel: NewsFeedViewModel by activityViewModels()
    private val newsFeedAdapter: NewsFeedAdapter by lazy {
        NewsFeedAdapter {
            //on click
            findNavController().navigate(HomeFragmentDirections.openNewsFeedDetail())

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
                    newsFeedAdapter.submitList(it.data.itemsEntity)
                }
                is Resource.Error -> {
                    if (!requireContext().isNetworkConnected()) {
                        Toast.makeText(requireContext(), R.string.no_network_mess, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
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
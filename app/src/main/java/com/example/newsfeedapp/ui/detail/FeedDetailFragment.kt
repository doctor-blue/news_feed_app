package com.example.newsfeedapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcomentry.moonlight.binding.BindingFragment
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.FragmentFeedDetailBinding
import com.example.newsfeedapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.newsfeedapp.utils.isNetworkConnected


@AndroidEntryPoint
class FeedDetailFragment :
    BindingFragment<FragmentFeedDetailBinding>(R.layout.fragment_feed_detail) {

    private val detailViewModel: DetailViewModel by activityViewModels()

    private val adapter: SectionAdapter by lazy {
        SectionAdapter {

        }
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        binding {
            rvSection.adapter = adapter
            rvSection.layoutManager = LinearLayoutManager(requireContext())

            rvSection.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                detailViewModel.getDetailFeed().observe(viewLifecycleOwner, {

                    pbDetail.isVisible = it is Resource.Loading

                    when (it) {
                        is Resource.Loading -> {
                            txtLink.isVisible = false
                            txtAnother.isVisible = false
                        }
                        is Resource.Success -> {
                            newsFeed = it.data
                            adapter.submitList(it.data.sectionEntities)
                            txtLink.isVisible = true
                            txtAnother.isVisible = true
                        }
                        is Resource.Error -> {
                            if (!requireContext().isNetworkConnected()) {
                                Toast.makeText(
                                    requireContext(),
                                    R.string.no_network_mess,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            txtLink.isVisible = false
                            txtAnother.isVisible = false
                        }
                    }
                })
            }
        }
    }

    override fun initEvents() {
        super.initEvents()
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.txtLink.setOnClickListener {
            binding.newsFeed?.let {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.originUrl))
                startActivity(browserIntent)
            }
        }
    }

}
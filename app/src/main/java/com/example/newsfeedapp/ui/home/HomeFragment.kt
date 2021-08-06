package com.example.newsfeedapp.ui.home

import android.os.Bundle
import com.devcomentry.moonlight.binding.BindingFragment
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        requireActivity().supportFragmentManager.let {
            val adapter = HomePagerAdapter(requireContext(), childFragmentManager)
            binding {
                homeViewPager.adapter = adapter
                homeTabLayout.setupWithViewPager(homeViewPager)
            }
            adapter.notifyDataSetChanged()
        }
    }

}
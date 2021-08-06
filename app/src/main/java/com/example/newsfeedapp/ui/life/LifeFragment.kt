package com.example.newsfeedapp.ui.life

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcomentry.moonlight.binding.BindingFragment
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.FragmentLifeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifeFragment : BindingFragment<FragmentLifeBinding>(R.layout.fragment_life) {
    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
    }

    override fun initEvents() {
        super.initEvents()
    }
}
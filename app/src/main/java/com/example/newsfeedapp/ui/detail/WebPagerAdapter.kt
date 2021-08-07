package com.example.newsfeedapp.ui.detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcomentry.moonlight.binding.binding
import com.example.newsfeedapp.R
import com.example.newsfeedapp.databinding.ItemWebviewBinding
import com.example.newsfeedapp.model.Markup

class WebViewHolder(val binding: ItemWebviewBinding) :
    RecyclerView.ViewHolder(binding.root)

class WebPagerAdapter(
) : RecyclerView.Adapter<WebViewHolder>() {
    private var items: MutableList<Markup> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebViewHolder {
        return WebViewHolder(parent.binding(R.layout.item_webview, false))
    }

    override fun onBindViewHolder(holder: WebViewHolder, position: Int) {
        holder.binding.markup = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMarkupEntities(markupList: List<Markup>) {
        items.clear()
        items.addAll(markupList)
        notifyDataSetChanged()
    }

}
package com.example.newsfeedapp.utils.binding

import android.webkit.WebView
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsfeedapp.model.entity.ImageEntity

@BindingAdapter("imageUrl")
fun loadFromImage(view: ImageView, urls: List<ImageEntity>?) {
    urls?.let {
        Glide.with(view.context)
            .load(it[0].href)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(view)
    }

}

@BindingAdapter(value = ["imageUrls", "at"])
fun loadImageAt(view: ImageView, urls: List<ImageEntity>?, index: Int) {
    urls?.let {
        if (urls.size > index)
            Glide.with(view.context).load(urls[index].href)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(view)
    }
}

@BindingAdapter("url")
fun loadImageFromUrl(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(12))
            .into(view)
    }
}

@BindingAdapter("urlVideo")
fun loadVideoFromUrl(videoView: VideoView, url: String) {
    videoView.setVideoPath(url)
}

@BindingAdapter("webUrl")
fun loadWebView(webView: WebView, url: String) {
    webView.loadUrl(url)
}
package com.example.newsfeedapp.utils.binding

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
    Glide.with(view.context)
        .load(url ?: "https://cdn.24h.com.vn/upload/3-2021/images/2021-07-22/1626797773_362712_1626797891_noticia_normal_recorte1-740-1626913920-838-width740height416.jpg")
        .transform(CenterCrop(), RoundedCorners(12))
        .into(view)
}
@BindingAdapter("urlVideo")
fun loadVideoFromUrl(videoView: VideoView, url: String) {
    videoView.setVideoPath(url)
//    videoView.start()
}
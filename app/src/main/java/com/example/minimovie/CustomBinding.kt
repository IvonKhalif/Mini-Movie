package com.example.minimovie

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomBinding {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: AppCompatImageView, path: String?) {
        if (path == null) {
            return
        }

        val url = "https://image.tmdb.org/t/p/w342${path}"
        if (path.isNotBlank()) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}
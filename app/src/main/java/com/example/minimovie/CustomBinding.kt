package com.example.minimovie

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomBinding {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: AppCompatImageView, path: String?) {
        if (path == null) {
            view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_warning))
            return
        }

        val url = if (path.contains("http")) {
            path.replace("/http", "http")
        } else {
            "https://image.tmdb.org/t/p/w342${path}"
        }
        if (path.isNotBlank()) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}
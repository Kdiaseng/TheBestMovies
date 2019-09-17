package com.kdias.thebestmovies.features.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.anderson.androidtrend.utlis.GlideApp
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, poster: String?) {

    Log.d("POSTER", poster)

    poster?.let{

        GlideApp.with(view.context)
            .load(poster)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }

}
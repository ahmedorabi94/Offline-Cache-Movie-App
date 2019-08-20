package com.example.movieapp.ui

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {

    val baseUrl = "http://image.tmdb.org/t/p/w500"
    val finalUrl = baseUrl + url
    Glide.with(imageView.context).load(finalUrl).into(imageView)
}

@BindingAdapter("voteAverage")
fun setVoteAverage(view : RatingBar,vote:Double){

    val rate = (vote / 2.0).toFloat()
    view.rating= rate
}
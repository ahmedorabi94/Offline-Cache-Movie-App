package com.example.movieapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.repository.MoviesRepository
import com.example.movieapp.viewmodels.MoviesViewModel


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {

    val baseUrl = "http://image.tmdb.org/t/p/w500"
    val finalUrl = baseUrl + url
    Glide.with(imageView.context).load(finalUrl).into(imageView)
}

@BindingAdapter("voteAverage")
fun setVoteAverage(view: RatingBar, vote: Double) {

    val rate = (vote / 2.0).toFloat()
    view.rating = rate
}


@BindingAdapter("showLoading")
fun showProgress(progressBar: ProgressBar, status: MoviesRepository.MoviesStatus?) {

    when (status) {
        MoviesRepository.MoviesStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        MoviesRepository.MoviesStatus.SUCCESS -> {
            progressBar.visibility = View.GONE
        }
        MoviesRepository.MoviesStatus.ERROR -> {
            progressBar.visibility = View.GONE

        }

    }


}
package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieapp.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val repo: MoviesRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {

    }

    fun getMovies() {

        coroutineScope.launch {
            repo.getMovies()
        }

    }


    val movies = repo.movies

    val status = repo.status


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

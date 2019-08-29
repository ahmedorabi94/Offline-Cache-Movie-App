package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.movieapp.api.MovieApiInterface
import com.example.movieapp.db.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieDb: MovieDatabase,
    private val apiService: MovieApiInterface
) {

    enum class MoviesStatus {
        SUCCESS, LOADING, ERROR
    }


    private val _status = MutableLiveData<MoviesStatus>()
    val status: LiveData<MoviesStatus>
        get() = _status


    val movies = Transformations.switchMap(movieDb.movieDao.getMovies()) {
        movieDb.movieDao.getMovies()
    }


    suspend fun getMovies() {

        _status.value = MoviesStatus.LOADING
        withContext(Dispatchers.IO) {
            val movies = apiService.getNowPlaying("").await()
            movieDb.movieDao.insertAllMovies(movies.results)

        }
        _status.value = MoviesStatus.SUCCESS

    }


}
package com.example.movieapp.repository

import androidx.lifecycle.Transformations
import com.example.movieapp.api.ApiService
import com.example.movieapp.db.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val movieDb: MovieDatabase) {


    val movies = Transformations.switchMap(movieDb.movieDao.getMovies()) {
        movieDb.movieDao.getMovies()
    }


    suspend fun getMovies() {

        withContext(Dispatchers.IO) {
            val movies = ApiService.service.getNowPlaying("").await()
            movieDb.movieDao.insertAllMovies(movies.results)

        }
    }


}
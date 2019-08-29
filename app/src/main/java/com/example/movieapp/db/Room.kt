package com.example.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.data.Result

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getMovies(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Result>)
}


@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}

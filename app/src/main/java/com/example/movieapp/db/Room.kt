package com.example.movieapp.db

import android.content.Context
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


@Database(entities = [Result::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}

private lateinit var INSTANCE: MovieDatabase

fun getDatabase(context: Context): MovieDatabase {

    synchronized(MovieDatabase::class.java) {

        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context, MovieDatabase::class.java, "movie-kotlin.db")
                .build()
        }

    }
    return INSTANCE
}
package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.api.MovieApiInterface
import com.example.movieapp.db.MovieDao
import com.example.movieapp.db.MovieDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(moshi: Moshi): MovieApiInterface {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://api.themoviedb.org/3/")
            .build()
            .create(MovieApiInterface::class.java)
    }


    @Singleton
    @Provides
    fun provideDb(app: Application): MovieDatabase {

        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie-kotlin.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao
    }


}
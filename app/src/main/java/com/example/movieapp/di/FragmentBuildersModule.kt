package com.example.movieapp.di

import com.example.movieapp.ui.GridMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeGridMoviesFragment(): GridMoviesFragment
}
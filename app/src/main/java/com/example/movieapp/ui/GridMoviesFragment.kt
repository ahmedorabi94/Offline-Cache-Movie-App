package com.example.movieapp.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentGridMoviesBinding
import com.example.movieapp.viewmodels.MoviesViewModel

class GridMoviesFragment : Fragment() {


    private val viewModel: MoviesViewModel by lazy {
        ViewModelProviders.of(this, MoviesViewModel.Factory(activity!!.application)).get(MoviesViewModel::class.java)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding: FragmentGridMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_grid_movies, container, false)

        val adapter = MovieAdapter()
        val itemDecoration = GridItemDecoration(8,12)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = adapter



        viewModel.movies.observe(this, Observer {
            Log.e("MovieResponse", it.size.toString())
            adapter.submitList(it)
        })



        return binding.root
    }


}

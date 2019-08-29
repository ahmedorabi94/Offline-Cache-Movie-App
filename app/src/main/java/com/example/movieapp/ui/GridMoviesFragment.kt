package com.example.movieapp.ui


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentGridMoviesBinding
import com.example.movieapp.di.Injectable
import com.example.movieapp.viewmodels.MoviesViewModel
import javax.inject.Inject

class GridMoviesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MoviesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentGridMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_grid_movies, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoviesViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        val adapter = MovieAdapter()
        val itemDecoration = GridItemDecoration(8, 12)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = adapter

        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true

        if (isConnected) {
            viewModel.getMovies()
        } else {
            binding.progressBar.visibility = View.GONE
        }


        viewModel.movies.observe(this, Observer {
            Log.e("MovieResponse", it.size.toString())
            adapter.submitList(it)
        })



        return binding.root
    }


}

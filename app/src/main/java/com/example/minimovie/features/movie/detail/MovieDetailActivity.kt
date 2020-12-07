package com.example.minimovie.features.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.minimovie.R
import com.example.minimovie.databinding.ActivityMovieBinding
import com.example.minimovie.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this
    }
}

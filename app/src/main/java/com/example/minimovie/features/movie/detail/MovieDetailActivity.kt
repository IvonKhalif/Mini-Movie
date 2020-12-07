package com.example.minimovie.features.movie.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.minimovie.R
import com.example.minimovie.databinding.ActivityMovieDetailBinding
import com.example.minimovie.features.movie.list.MovieActivity
import com.example.minimovie.features.review.ReviewActivity
import com.example.minimovie.features.youtubeplayer.YoutubePlayerActivity
import com.example.minimovie.repository.movie.MoviesDTO
import com.example.minimovie.setYoutubeThumbnail
import com.jakewharton.rxbinding2.view.clicks
import java.util.concurrent.TimeUnit

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TRAILER_KEY = "EXTRA_TRAILER_KEY"
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        viewModel.resultMovieModel =
            intent.getParcelableExtra(MovieActivity.EXTRA_MOVIE) as MoviesDTO.ResultMovie

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getTrailer()
        listener()
        observer()
    }

    @SuppressLint("CheckResult")
    private fun listener() {
        binding.buttonPlay.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                val intent = Intent(this, YoutubePlayerActivity::class.java)
                intent.putExtra(EXTRA_TRAILER_KEY, viewModel.trailerMovieList.value!![0].key)
                startActivity(intent)
            }

        binding.buttonAllReview.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                val intent = Intent(this, ReviewActivity::class.java)
                intent.putExtra(EXTRA_MOVIE_ID, viewModel.resultMovieModel.id)
                startActivity(intent)
            }
    }

    private fun observer() {
        viewModel.trailerMovieList.observe(this, Observer { resultTrailer ->
            resultTrailer[0].key?.let { binding.youtubeThumbnailView.setYoutubeThumbnail(it) }
        })
    }
}

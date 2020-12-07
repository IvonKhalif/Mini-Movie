package com.example.minimovie.features.movie.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minimovie.R
import com.example.minimovie.databinding.ActivityMovieBinding
import com.example.minimovie.features.genres.GenresBottomSheet
import com.example.minimovie.features.movie.detail.MovieDetailActivity
import com.example.minimovie.repository.movie.MoviesDTO

class MovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel
    private val movieAdapter by lazy {
        MovieAdapter { movieModel ->
            goToDetailMovie(
                movieModel
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        binding.viewModelList = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()
//        viewModel.loadMovieByGenre(1)
        viewModel.loadGenres()
        listener()
        observer()
    }

    private fun initRecyclerView() {
        binding.recyclerviewList.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 2)
            adapter = movieAdapter
        }
    }

    private fun listener() {
        binding.recyclerviewList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = itemCount.minus(1) == lastVisiblePosition
                if (isLastPosition && viewModel.page < viewModel.totalPage) {
                    viewModel.page = viewModel.page.plus(1)
                    viewModel.loadMovieByGenre(viewModel.page)
                }
            }
        })

        binding.buttonFilter.setOnClickListener {
            val genreBottomSheet = GenresBottomSheet()
            genreBottomSheet.show(supportFragmentManager, genreBottomSheet.tag)
        }
    }

    private fun observer() {
        viewModel.movieList.observe(this, Observer {
            movieAdapter.addMovieList(it.results)
        })

        viewModel.genreModel.observe(this, Observer {
            movieAdapter.clearList()
            viewModel.loadMovieByGenre(1)
        })
    }

    private fun goToDetailMovie(movieModel: MoviesDTO.ResultMovie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movieModel)
        startActivity(intent)
    }
}
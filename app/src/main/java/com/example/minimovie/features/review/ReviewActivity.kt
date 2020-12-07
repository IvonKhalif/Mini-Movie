package com.example.minimovie.features.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minimovie.R
import com.example.minimovie.databinding.ActivityReviewBinding
import com.example.minimovie.features.movie.detail.MovieDetailActivity
import com.example.minimovie.repository.review.ResultReviews

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding
    private lateinit var viewModel: ReviewViewModel
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        viewModel.movieId = intent.getIntExtra(MovieDetailActivity.EXTRA_MOVIE_ID, -1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review)
        binding.viewModelReview = viewModel
        binding.lifecycleOwner = this

        initAdapter(mutableListOf())
        viewModel.getReview(viewModel.movieId.toString())
        observer()
        listener()
    }

    private fun listener() {
        binding.recyclerReview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (isLastPosition && viewModel.page < viewModel.totalPages) {
                    viewModel.page = viewModel.page.plus(1)
                    viewModel.getReview(viewModel.movieId.toString())
                }
            }
        })
    }

    private fun observer() {
        viewModel.reviewModel.observe(this, Observer { reviewModel ->
            reviewModel.results?.let { it ->
                reviewAdapter.addReviewList(it)
            }
        })
    }

    private fun initAdapter(results: MutableList<ResultReviews>?) {
        reviewAdapter = ReviewAdapter(results!!)
        binding.recyclerReview.apply {
            layoutManager = LinearLayoutManager(this@ReviewActivity)
            adapter = reviewAdapter
            reviewAdapter.notifyDataSetChanged()
        }
    }
}

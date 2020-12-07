package com.example.minimovie.features.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.minimovie.BaseViewModel
import com.example.minimovie.repository.review.ReviewRepository
import com.example.minimovie.repository.review.ReviewsDTO

class ReviewViewModel : BaseViewModel() {
    val reviewModel = MutableLiveData<ReviewsDTO>()
    var movieId = 0

    val isHasReview = Transformations.map(reviewModel) {
        !it.results.isNullOrEmpty()
    }

    var page = 1
    var totalPages = 0
    fun getReview(movieId: String) {
        addToDisposable(
            ReviewRepository.list(movieId, page)
                .subscribe {
                    totalPages = it.total_pages ?: 0
                    reviewModel.value = it
                }
        )
    }
}
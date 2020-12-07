package com.example.minimovie.repository.review

import com.example.minimovie.network.ApiBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ReviewRepository {
    fun list(movieId: String, page: Int): Observable<ReviewsDTO> {
        return ApiBuilder.getService().getReviews(movieId, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
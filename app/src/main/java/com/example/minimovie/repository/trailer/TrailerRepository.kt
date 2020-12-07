package com.example.minimovie.repository.trailer

import com.example.minimovie.network.ApiBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object TrailerRepository {
    fun getTrailer(movieId: String): Observable<TrailerDTO> {
        return ApiBuilder.getService().getTrailer(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
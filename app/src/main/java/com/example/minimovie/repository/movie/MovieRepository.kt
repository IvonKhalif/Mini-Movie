package com.example.minimovie.repository.movie

import com.example.minimovie.network.ApiBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieRepository {
    fun list(genreId: String, page: Int): Observable<MoviesDTO> {
        return ApiBuilder.getService().getMovieList(genreId, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
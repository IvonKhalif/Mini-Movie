package com.example.minimovie.repository.genre

import com.example.minimovie.network.ApiBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object GenreRepository {
    fun list(): Observable<GenresDTO>{
        return ApiBuilder.getService().getGenreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
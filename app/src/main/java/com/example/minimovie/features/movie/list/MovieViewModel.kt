package com.example.minimovie.features.movie.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.minimovie.BaseViewModel
import com.example.minimovie.repository.genre.GenreRepository
import com.example.minimovie.repository.genre.GenresDTO
import com.example.minimovie.repository.movie.MovieRepository
import com.example.minimovie.repository.movie.MoviesDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel: BaseViewModel() {
    var movieList = MutableLiveData<MoviesDTO>()
    var genreList = MutableLiveData<List<GenresDTO.Genre>>()
    var genreModel = MutableLiveData<GenresDTO.Genre>(GenresDTO.Genre(0, ""))
    var textGenre = Transformations.map(genreModel){
        if (it == null || it.name == "") "Pilih Genre" else it.name
    }

    var genreId = ""
    var page = 1
    var totalPage = 0
    fun loadMovieByGenre(page: Int) {
        addToDisposable(
            MovieRepository.list(genreId, page)
                .subscribe({
                    movieList.value = it
                    totalPage = it.total_pages ?: 0
                }, {

                })
        )
    }

    fun loadGenres() {
        addToDisposable(
            GenreRepository.list()
                .subscribe({
                    genreList.value = it.genres
                }, {
                    Log.d("Error Genre", it.message)
                })
        )
    }
}
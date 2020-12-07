package com.example.minimovie.features.movie.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.minimovie.BaseViewModel
import com.example.minimovie.repository.movie.MoviesDTO
import com.example.minimovie.repository.trailer.ResultTrailer
import com.example.minimovie.repository.trailer.TrailerRepository

class MovieDetailViewModel: BaseViewModel() {

    lateinit var resultMovieModel: MoviesDTO.ResultMovie
    var trailerMovieList = MutableLiveData<List<ResultTrailer>>()
    val titleTrailer = Transformations.map(trailerMovieList) {
        it[0].name
    }
    val isHasTrailer = Transformations.map(trailerMovieList) {
        !it.isNullOrEmpty()
    }

    fun getTrailer() {
        addToDisposable(
            TrailerRepository.getTrailer(resultMovieModel.id.toString())
                .subscribe { trailerResponse ->
                    trailerMovieList.value = trailerResponse.results
                }
        )
    }
}
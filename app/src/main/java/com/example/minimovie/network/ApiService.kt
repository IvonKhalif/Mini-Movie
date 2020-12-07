package com.example.minimovie.network

import com.example.minimovie.repository.genre.GenresDTO
import com.example.minimovie.repository.movie.MoviesDTO
import com.example.minimovie.repository.review.ReviewsDTO
import com.example.minimovie.repository.trailer.TrailerDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list?api_key=9b4be72ecb73ef228afbadeb36f9e865")
    fun getGenreList(): Observable<GenresDTO>

    @GET("discover/movie?api_key=9b4be72ecb73ef228afbadeb36f9e865&region=ID&sort_by=popularity.desc")
    fun getMovieList(
        @Query("with_genres") idGenre: String,
        @Query("page") page: Int
    ): Observable<MoviesDTO>

    @GET("https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=9b4be72ecb73ef228afbadeb36f9e865&language=en-US")
    fun getReviews(
        @Path("movie_id") id: String,
        @Query("page") page: Int
    ): Observable<ReviewsDTO>

    @GET("movie/{movie_id}/videos?api_key=9b4be72ecb73ef228afbadeb36f9e865")
    fun getTrailer(@Path("movie_id") id: String): Observable<TrailerDTO>
}
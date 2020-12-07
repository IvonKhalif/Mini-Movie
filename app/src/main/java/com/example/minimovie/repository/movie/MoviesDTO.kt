package com.example.minimovie.repository.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesDTO(
    var page: Int,
    var results: List<ResultMovie>?,
    var total_pages: Int?,
    var total_results: Int?
): Parcelable {

    @Parcelize
    data class ResultMovie(
        var adult: Boolean?,
        var backdrop_path: String?,
        var genre_ids: List<Int>?,
        var id: Int?,
        var original_title: String?,
        var overview: String?,
        var popularity: Double?,
        var poster_path: String?,
        var release_date: String?,
        var title: String?,
        var video: Boolean?,
        var vote_average: Double?,
        var vote_count: Int?
    ): Parcelable
}
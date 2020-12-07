package com.example.minimovie.repository.genre

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresDTO(
    var genres: List<Genre>?
): Parcelable {

    @Parcelize
    data class Genre(
        var id: Int?,
        var name: String?
    ): Parcelable
}
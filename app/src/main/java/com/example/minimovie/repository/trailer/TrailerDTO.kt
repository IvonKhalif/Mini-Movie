package com.example.minimovie.repository.trailer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrailerDTO(
    var id: Int?,
    var results: List<ResultTrailer>?
): Parcelable

@Parcelize
data class ResultTrailer(
    var id: String?,
    var iso31661: String?,
    var iso6391: String?,
    var key: String?,
    var name: String?,
    var site: String?,
    var size: Int?,
    var type: String?
): Parcelable

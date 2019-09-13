package com.kdias.thebestmovies.features.listaFilmes.fragment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String? = "",
    val tipo: String? = "",
    val description: String? = "",
    val released: String? = "",
    val title: String? = "",
    val poster: String?= "",
    val runtime: String ?= "",
    val urlTrailer: String?= ""
): Parcelable


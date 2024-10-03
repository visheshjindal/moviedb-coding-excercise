package com.vishesh.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    val genres: List<GenreResponse>,
    val runtime: Int,
    @SerialName("vote_average")
    val voteAverage: Double,
    val status: String,
)

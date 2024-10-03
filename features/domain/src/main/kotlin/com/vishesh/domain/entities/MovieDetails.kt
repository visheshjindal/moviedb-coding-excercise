package com.vishesh.domain.entities

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val genreNames: List<String>,
    val releaseDate: String,
    val voteAverage: Double,
    val status: String,
    val tagline: String,
    val runtime: Int,
)

package com.vishesh.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    val genres: List<GenreResponse>,
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String,
)

package com.vishesh.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    val id: Int,
    val cast: List<CastResponse>,
)

@Serializable
data class CastResponse(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?,
    val character: String,
)

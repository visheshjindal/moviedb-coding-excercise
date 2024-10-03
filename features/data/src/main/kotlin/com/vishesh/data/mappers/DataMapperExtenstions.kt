package com.vishesh.data.mappers

import com.vishesh.data.models.CastResponse
import com.vishesh.data.models.MovieDetailsResponse
import com.vishesh.data.models.MovieResponse
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.entities.MovieDetails

internal fun MovieResponse.toMovie(genreNames: List<String> = emptyList()) =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w342$posterPath",
        backdropPath = "https://image.tmdb.org/t/p/w780$backdropPath",
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreNames = genreNames,
    )

internal fun CastResponse.toCast() =
    Cast(
        id = id,
        name = name,
        character = character,
        profilePath = "https://image.tmdb.org/t/p/w235_and_h235_face$profilePath",
    )

internal fun MovieDetailsResponse.toMovieDetails() =
    MovieDetails(
        id = id,
        title = title,
        overview = overview,
        backdropPath = "https://image.tmdb.org/t/p/w780${backdropPath ?: ""}",
        genreNames = genres.map { it.name },
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        runtime = runtime,
        status = status,
        tagline = tagline,
    )

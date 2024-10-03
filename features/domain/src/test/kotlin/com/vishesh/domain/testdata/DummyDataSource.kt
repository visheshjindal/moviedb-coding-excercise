package com.vishesh.domain.testdata

import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.entities.MovieDetails

val dummyMovieListData =
    listOf(
        Movie(
            id = 1,
            title = "loreum ipsum",
            overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            posterPath = "/poster1.jpg",
            backdropPath = "/backdrop1.jpg",
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            genreNames = listOf("Action", "Adventure"),
        ),
        Movie(
            id = 2,
            title = "loreum ipsum",
            overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            posterPath = "/poster2.jpg",
            backdropPath = "/backdrop2.jpg",
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            genreNames = listOf("Action", "Adventure"),
        ),
        Movie(
            id = 3,
            title = "loreum ipsum",
            overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            posterPath = "/poster3.jpg",
            backdropPath = "/backdrop3.jpg",
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            genreNames = listOf("Action", "Adventure"),
        ),
        Movie(
            id = 4,
            title = "loreum ipsum",
            overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            posterPath = "/poster4.jpg",
            backdropPath = "/backdrop4.jpg",
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            genreNames = listOf("Action", "Adventure"),
        ),
    )

val dummyCastList: List<Cast> =
    listOf(
        Cast(
            id = 1,
            name = "Actor 1",
            character = "Character 1",
            profilePath = "/profile1.jpg",
        ),
        Cast(
            id = 2,
            name = "Actor 2",
            character = "Character 2",
            profilePath = "/profile2.jpg",
        ),
        Cast(
            id = 3,
            name = "Actor 3",
            character = "Character 3",
            profilePath = "/profile3.jpg",
        ),
        Cast(
            id = 4,
            name = "Actor 4",
            character = "Character 4",
            profilePath = "/profile4.jpg",
        ),
    )

val dummyMovieDetails =
    MovieDetails(
        id = 1,
        title = "Lorem Ipsum",
        overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        backdropPath = "/backdrop1.jpg",
        genreNames = listOf("Action", "Adventure"),
        releaseDate = "2021-01-01",
        status = "Released",
        voteAverage = 7.5,
        tagline = "Tagline",
        runtime = 120,
    )

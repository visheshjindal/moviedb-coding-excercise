package com.vishesh.data.testdata

import com.vishesh.data.models.CastResponse
import com.vishesh.data.models.CreditsResponse
import com.vishesh.data.models.GenreResponse
import com.vishesh.data.models.GenresResponse
import com.vishesh.data.models.MovieDetailsResponse
import com.vishesh.data.models.MovieResponse
import com.vishesh.data.models.MoviesResponse
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.entities.MovieDetails
import retrofit2.Response

object MockData {
    val castList =
        listOf(
            Cast(
                id = 1,
                name = "Actor 1",
                profilePath = "https://image.tmdb.org/t/p/w235_and_h235_face/profile1.jpg",
                character = "Character 1",
            ),
            Cast(
                id = 2,
                name = "Actor 2",
                profilePath = "https://image.tmdb.org/t/p/w235_and_h235_face/profile2.jpg",
                character = "Character 2",
            ),
            Cast(
                id = 3,
                name = "Actor 3",
                profilePath = "https://image.tmdb.org/t/p/w235_and_h235_face/profile3.jpg",
                character = "Character 3",
            ),
        )

    val castResponseList =
        listOf(
            CastResponse(
                id = 1,
                name = "Actor 1",
                profilePath = "/profile1.jpg",
                character = "Character 1",
            ),
            CastResponse(
                id = 2,
                name = "Actor 2",
                profilePath = "/profile2.jpg",
                character = "Character 2",
            ),
            CastResponse(
                id = 3,
                name = "Actor 3",
                profilePath = "/profile3.jpg",
                character = "Character 3",
            ),
        )

    val castsResponse: Response<CreditsResponse> =
        Response.success(
            CreditsResponse(
                id = 1,
                cast = castResponseList,
            ),
        )
    val movieDetails =
        MovieDetails(
            id = 1,
            title = "Movie 1",
            overview = "Overview 1",
            backdropPath = "https://image.tmdb.org/t/p/w780/backdrop1.jpg",
            genreNames = listOf("Genre 1", "Genre 2"),
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            runtime = 120,
            status = "Released",
            tagline = "Tagline 1",
        )
    val movieDetailResponse: MovieDetailsResponse =
        MovieDetailsResponse(
            id = 1,
            title = "Movie 1",
            overview = "Overview 1",
            backdropPath = "/backdrop1.jpg",
            genres = listOf(GenreResponse(1, "Genre 1"), GenreResponse(2, "Genre 2")),
            releaseDate = "2021-01-01",
            voteAverage = 7.5,
            runtime = 120,
            status = "Released",
            tagline = "Tagline 1",
        )
    val movieDetailsResponse: Response<MovieDetailsResponse> =
        Response.success(movieDetailResponse)
    val movies: List<Movie> =
        listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                overview = "Overview 1",
                posterPath = "https://image.tmdb.org/t/p/w342/poster1.jpg",
                backdropPath = "https://image.tmdb.org/t/p/w780/backdrop1.jpg",
                releaseDate = "2021-01-01",
                voteAverage = 7.5,
                genreNames = listOf("Genre 1", "Genre 2"),
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                overview = "Overview 2",
                posterPath = "https://image.tmdb.org/t/p/w342/poster2.jpg",
                backdropPath = "https://image.tmdb.org/t/p/w780/backdrop2.jpg",
                releaseDate = "2021-01-02",
                voteAverage = 8.5,
                genreNames = listOf("Genre 2", "Genre 3"),
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                overview = "Overview 3",
                posterPath = "https://image.tmdb.org/t/p/w342/poster3.jpg",
                backdropPath = "https://image.tmdb.org/t/p/w780/backdrop3.jpg",
                releaseDate = "2021-01-03",
                voteAverage = 9.5,
                genreNames = listOf("Genre 3", "Genre 4"),
            ),
        )
    val genreResponseList: List<GenreResponse> =
        listOf(
            GenreResponse(
                id = 1,
                name = "Genre 1",
            ),
            GenreResponse(
                id = 2,
                name = "Genre 2",
            ),
            GenreResponse(
                id = 3,
                name = "Genre 3",
            ),
            GenreResponse(
                id = 4,
                name = "Genre 4",
            ),
        )
    val genresResponse: Response<GenresResponse> =
        Response.success(
            GenresResponse(
                genres = genreResponseList,
            ),
        )
    val movieResponseList: List<MovieResponse> =
        listOf(
            MovieResponse(
                id = 1,
                title = "Movie 1",
                overview = "Overview 1",
                posterPath = "/poster1.jpg",
                backdropPath = "/backdrop1.jpg",
                releaseDate = "2021-01-01",
                voteAverage = 7.5,
                genreIds = listOf(1, 2),
            ),
            MovieResponse(
                id = 2,
                title = "Movie 2",
                overview = "Overview 2",
                posterPath = "/poster2.jpg",
                backdropPath = "/backdrop2.jpg",
                releaseDate = "2021-01-02",
                voteAverage = 8.5,
                genreIds = listOf(2, 3),
            ),
            MovieResponse(
                id = 3,
                title = "Movie 3",
                overview = "Overview 3",
                posterPath = "/poster3.jpg",
                backdropPath = "/backdrop3.jpg",
                releaseDate = "2021-01-03",
                voteAverage = 9.5,
                genreIds = listOf(3, 4),
            ),
        )
    val movieResponse: Response<MoviesResponse> =
        Response.success(
            MoviesResponse(
                page = 1,
                results = movieResponseList,
                totalPages = 1,
                totalResults = 3,
            ),
        )
}

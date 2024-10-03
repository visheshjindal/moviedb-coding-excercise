package com.vishesh.moviedetails.ui.previewproviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.MovieDetails
import com.vishesh.moviedetails.state.MovieDetailsUiState

class MovieDetailsScreenPreviewProvider : PreviewParameterProvider<MovieDetailsUiState> {
    override val values: Sequence<MovieDetailsUiState>
        get() =
            sequenceOf(
                MovieDetailsUiState.Success(
                    movieDetails =
                        MovieDetails(
                            id = 1,
                            title = "The Godfather Part II",
                            overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York.",
                            backdropPath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                            tagline = "I don't feel I have to wipe everybody out, Tom. Just my enemies.",
                            releaseDate = "1974-12-20",
                            voteAverage = 8.5,
                            runtime = 202,
                            status = "Released",
                            genreNames = listOf("Crime", "Drama"),
                        ),
                    castList =
                        listOf(
                            Cast(
                                id = 1,
                                name = "Al Pacino",
                                character = "Michael Corleone",
                                profilePath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                            ),
                            Cast(
                                id = 2,
                                name = "Robert De Niro",
                                character = "Vito Corleone",
                                profilePath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                            ),
                        ),
                ),
                MovieDetailsUiState.Error(
                    message = "Failed to fetch movie details",
                ),
                MovieDetailsUiState.Idle,
                MovieDetailsUiState.Loading,
            )
}

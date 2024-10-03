package com.vishesh.movielist.ui.previewproviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vishesh.domain.entities.Movie
import com.vishesh.movielist.state.MovieListUiState

class MovieScreenPreviewProvider : PreviewParameterProvider<MovieListUiState> {
    override val values: Sequence<MovieListUiState>
        get() =
            sequenceOf(
                MovieListUiState.Success(
                    movies =
                        listOf(
                            Movie(
                                id = 1,
                                title = "Star Wars: The Rise of Skywalker",
                                overview = "The surviving members of the resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak bringing the Skywalker saga to its end.",
                                posterPath = "https://image.tmdb.org/t/p/w500/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                                genreNames = listOf("Action", "Adventure"),
                                releaseDate = "2021-01-01",
                                voteAverage = 7.5,
                                backdropPath = "https://image.tmdb.org/t/p/w780/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                            ),
                            Movie(
                                id = 2,
                                title = "The Godfather",
                                overview = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                                posterPath = "https://image.tmdb.org/t/p/w500/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                                genreNames = listOf("Action", "Adventure"),
                                releaseDate = "2021-01-01",
                                voteAverage = 7.5,
                                backdropPath = "https://image.tmdb.org/t/p/w780/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                            ),
                            Movie(
                                id = 3,
                                title = "Operation Red Sea",
                                overview = "The film is loosely based on the evacuation of the 225 foreign nationals and almost 600 Chinese citizens from Yemen's southern port of Aden during the 2015 Yemeni Civil War.",
                                posterPath = "https://image.tmdb.org/t/p/w500/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                                genreNames = listOf("Action", "Adventure"),
                                releaseDate = "2021-01-01",
                                voteAverage = 7.5,
                                backdropPath = "https://image.tmdb.org/t/p/w780/8bRIfStf8yJfJGJoIYQf5aA1K3Z.jpg",
                            ),
                        ),
                ),
                MovieListUiState.Loading,
                MovieListUiState.Idle,
            )
}

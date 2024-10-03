package com.vishesh.movielist.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vishesh.domain.entities.Movie
import com.vishesh.movielist.ui.component.MovieListItem

@Composable
internal fun MovieList(
    onGotoMovieDetails: (Int) -> Unit,
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(movies, key = { it.id }) { movie ->
            with(movie) {
                MovieListItem(
                    title = title,
                    posterUrl = posterPath,
                    genreNames = genreNames,
                    overview = overview,
                    voteAverage = voteAverage,
                    onItemClick = { onGotoMovieDetails(id) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListPreview() {
    MovieList(
        onGotoMovieDetails = {},
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
            ),
    )
}

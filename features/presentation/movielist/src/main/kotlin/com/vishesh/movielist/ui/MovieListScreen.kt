package com.vishesh.movielist.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vishesh.designsystem.component.EmptyContent
import com.vishesh.designsystem.component.FullScreenLoadingIndicator
import com.vishesh.movielist.R
import com.vishesh.movielist.intent.MovieListIntent
import com.vishesh.movielist.state.MovieListUiState
import com.vishesh.movielist.ui.previewproviders.MovieScreenPreviewProvider

@Composable
internal fun MovieListScreen(
    state: MovieListUiState,
    onAction: (MovieListIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is MovieListUiState.Error ->
            EmptyContent(
                text = state.message,
                buttonText = stringResource(R.string.try_again),
                modifier = modifier,
                onButtonClick = { onAction(MovieListIntent.FetchMovies) },
            )

        MovieListUiState.Idle ->
            EmptyContent(
                text = stringResource(R.string.no_movies_found),
                buttonText = stringResource(R.string.try_again),
                modifier = modifier,
                onButtonClick = { onAction(MovieListIntent.FetchMovies) },
            )

        MovieListUiState.Loading -> FullScreenLoadingIndicator(modifier)
        is MovieListUiState.Success ->
            MovieList(
                onGotoMovieDetails = { onAction(MovieListIntent.NavigateToMovieDetails(it)) },
                movies = state.movies,
                modifier = modifier.fillMaxHeight(),
            )
    }
}


@Preview(showBackground = true)
@Composable
private fun MovieListScreenPreview(
    @PreviewParameter(MovieScreenPreviewProvider::class) state: MovieListUiState,
) {
    MovieListScreen(
        state = state,
        onAction = {},
    )
}

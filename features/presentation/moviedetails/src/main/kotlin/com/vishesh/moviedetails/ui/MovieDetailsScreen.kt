package com.vishesh.moviedetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vishesh.designsystem.component.EmptyContent
import com.vishesh.designsystem.component.FullScreenLoadingIndicator
import com.vishesh.moviedetail.R
import com.vishesh.moviedetails.intent.MovieDetailsIntent
import com.vishesh.moviedetails.state.MovieDetailsUiState
import com.vishesh.moviedetails.ui.previewproviders.MovieDetailsScreenPreviewProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailsIntent) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.movie_details),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(MovieDetailsIntent.OnBackNavigation) }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        MovieDetailsContent(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding),
            onAction = onAction,
        )
    }
}

@Composable
internal fun MovieDetailsContent(
    uiState: MovieDetailsUiState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailsIntent) -> Unit,
) {
    Column(modifier = modifier) {
        when (uiState) {
            is MovieDetailsUiState.Error ->
                EmptyContent(
                    text = uiState.message,
                    buttonText = stringResource(R.string.try_again),
                    onButtonClick = { onAction(MovieDetailsIntent.FetchMoviesDetail) },
                )

            MovieDetailsUiState.Idle ->
                EmptyContent(
                    text = stringResource(R.string.no_movie_details_found),
                    buttonText = stringResource(R.string.try_again),
                    onButtonClick = { onAction(MovieDetailsIntent.FetchMoviesDetail) },
                )

            MovieDetailsUiState.Loading -> FullScreenLoadingIndicator()
            is MovieDetailsUiState.Success -> {
                MovieDetailsView(
                    movieDetails = uiState.movieDetails,
                    castList = uiState.castList,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview(
    @PreviewParameter(MovieDetailsScreenPreviewProvider::class) uiState: MovieDetailsUiState,
) {
    MovieDetailsScreen(uiState, onAction = {})
}

package com.vishesh.moviedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vishesh.moviedetails.intent.MovieDetailsIntent
import com.vishesh.moviedetails.ui.MovieDetailsScreen
import com.vishesh.moviedetails.viewmodel.MovieDetailsViewModel
import com.vishesh.navigation.AppNavigationAction
import com.vishesh.navigation.NavigationManager

@Composable
fun MovieDetailsRoute(
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiState.collectAsStateWithLifecycle()
    MovieDetailsScreen(
        uiState = movieDetailsUiState,
        onAction = {
            if (it is MovieDetailsIntent.OnBackNavigation) {
                navigationManager(AppNavigationAction.NavigateUp)
            }
            movieDetailsViewModel.processIntent(it)
        },
        modifier = modifier,
    )
}

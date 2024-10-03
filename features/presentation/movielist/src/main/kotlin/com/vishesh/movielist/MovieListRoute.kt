package com.vishesh.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vishesh.movielist.intent.MovieListIntent
import com.vishesh.movielist.ui.MovieListScreen
import com.vishesh.movielist.viewmodel.MovieListViewModel
import com.vishesh.navigation.AppNavigationAction
import com.vishesh.navigation.NavigationManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListRoute(
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val movieListUiState by viewModel.movieListUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.top_charts)) },
            )
        },
    ) { innerPadding ->
        MovieListScreen(
            state = movieListUiState,
            onAction = {
                if (it is MovieListIntent.NavigateToMovieDetails) {
                    navigationManager(AppNavigationAction.GoToMovieDetails(it.movieId))
                } else {
                    viewModel.processIntent(it)
                }
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

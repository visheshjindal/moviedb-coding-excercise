package com.vishesh.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

class NavigationManager
    @Inject
    constructor(
        private val navigator: NavHostController,
    ) {
        operator fun invoke(action: AppNavigationAction) {
            when (action) {
                is AppNavigationAction.GoToMovieList -> navigator.navigate(NavRoutes.MovieList)
                is AppNavigationAction.GoToMovieDetails ->
                    navigator.navigate(
                        NavRoutes.MovieDetails.createRoute(action.movieId),
                    )

                AppNavigationAction.NavigateUp -> navigator.navigateUp()
            }
        }
    }

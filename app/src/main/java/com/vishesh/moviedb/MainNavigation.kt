package com.vishesh.moviedb

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vishesh.moviedetails.MovieDetailsRoute
import com.vishesh.movielist.MovieListRoute
import com.vishesh.navigation.NavRoutes
import com.vishesh.navigation.NavigationManager

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = NavRoutes.MovieList.route) {
        val navigationManager = NavigationManager(navController)
        composable(NavRoutes.MovieList.route) {
            MovieListRoute(
                navigationManager = navigationManager,
                modifier = modifier,
            )
        }

        composable(
            NavRoutes.MovieDetails.route,
            listOf(navArgument(NavRoutes.MovieDetails.MOVIE_ID) { type = NavType.IntType }),
        ) {
            MovieDetailsRoute(
                navigationManager = navigationManager,
                modifier = modifier,
            )
        }
    }
}

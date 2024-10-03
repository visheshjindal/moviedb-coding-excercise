package com.vishesh.navigation

sealed class AppNavigationAction {
    data object GoToMovieList : AppNavigationAction()

    class GoToMovieDetails(
        val movieId: Int,
    ) : AppNavigationAction()

    data object NavigateUp : AppNavigationAction()
}

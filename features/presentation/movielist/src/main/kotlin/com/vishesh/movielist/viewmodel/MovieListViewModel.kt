package com.vishesh.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.common.fold
import com.vishesh.domain.usecases.GetMoviesUseCase
import com.vishesh.movielist.intent.MovieListIntent
import com.vishesh.movielist.state.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
    @Inject
    constructor(
        private val getMoviesUseCase: GetMoviesUseCase,
    ) : ViewModel() {
        private val _movieListUiState = MutableStateFlow<MovieListUiState>(MovieListUiState.Idle)
        val movieListUiState: StateFlow<MovieListUiState> =
            _movieListUiState
                .onStart {
                    processIntent(MovieListIntent.FetchMovies)
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = MovieListUiState.Idle,
                )

        fun processIntent(movieListIntent: MovieListIntent) {
            when (movieListIntent) {
                is MovieListIntent.FetchMovies -> fetchMovieList()
                is MovieListIntent.NavigateToMovieDetails -> Unit
            }
        }

        private fun fetchMovieList() {
            viewModelScope.launch {
                _movieListUiState.tryEmit(MovieListUiState.Loading)
                getMoviesUseCase().fold(
                    onSuccess = { _movieListUiState.tryEmit(MovieListUiState.Success(it)) },
                    onError = { msg, _ ->
                        _movieListUiState.tryEmit(
                            MovieListUiState.Error(
                                msg,
                            ),
                        )
                    },
                )
            }
        }
    }

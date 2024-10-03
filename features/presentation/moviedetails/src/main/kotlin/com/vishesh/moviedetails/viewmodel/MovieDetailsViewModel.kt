package com.vishesh.moviedetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.common.Constants
import com.vishesh.common.fold
import com.vishesh.common.getOr
import com.vishesh.domain.usecases.GetCastsUseCase
import com.vishesh.domain.usecases.GetMovieDetailsUseCase
import com.vishesh.moviedetails.intent.MovieDetailsIntent
import com.vishesh.moviedetails.state.MovieDetailsUiState
import com.vishesh.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
    @Inject
    constructor(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
        private val getCastsUseCase: GetCastsUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val movieId: Int? = savedStateHandle.get<Int>(NavRoutes.MovieDetails.MOVIE_ID)
        private val _movieDetailsUiState =
            MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Idle)
        val movieDetailsUiState: StateFlow<MovieDetailsUiState> =
            _movieDetailsUiState
                .onStart {
                    processIntent(MovieDetailsIntent.FetchMoviesDetail)
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = MovieDetailsUiState.Idle,
                )

        fun processIntent(movieDetailsIntent: MovieDetailsIntent) {
            when (movieDetailsIntent) {
                is MovieDetailsIntent.FetchMoviesDetail -> fetchMovieDetailsAndCastList()
                is MovieDetailsIntent.OnBackNavigation -> Unit
            }
        }

        private fun fetchMovieDetailsAndCastList() {
            viewModelScope.launch {
                movieId?.let {
                    _movieDetailsUiState.tryEmit(MovieDetailsUiState.Loading)
                    getMovieDetailsUseCase(it).fold(
                        onSuccess = { movieDetails ->
                            val castList = getCastsUseCase(it).getOr(emptyList())
                            _movieDetailsUiState.tryEmit(
                                MovieDetailsUiState.Success(
                                    movieDetails,
                                    castList,
                                ),
                            )
                        },
                        onError = { msg, _ ->
                            _movieDetailsUiState.tryEmit(
                                MovieDetailsUiState.Error(
                                    msg,
                                ),
                            )
                        },
                    )
                }
                    ?: _movieDetailsUiState.tryEmit(MovieDetailsUiState.Error(Constants.MALFORMED_DATA_ERROR))
            }
        }
    }

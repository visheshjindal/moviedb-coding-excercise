package com.vishesh.moviedetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vishesh.designsystem.component.Dimensions
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.MovieDetails
import com.vishesh.moviedetail.R

@Composable
internal fun MovieDetailsView(
    movieDetails: MovieDetails,
    castList: List<Cast>,
    modifier: Modifier = Modifier,
) {
    Surface {
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item {
                Card(
                    modifier =
                        Modifier
                            .padding(horizontal = Dimensions.paddingLarge)
                            .aspectRatio(Dimensions.aspectRatioBackdrop),
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model =
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(movieDetails.backdropPath)
                                    .error(android.R.drawable.ic_menu_report_image)
                                    .placeholder(android.R.drawable.ic_menu_report_image)
                                    .build(),
                            contentDescription = movieDetails.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                        )
                        Text(
                            text = movieDetails.tagline,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                            modifier =
                            Modifier
                                    .padding(Dimensions.paddingSmall)
                                    .align(Alignment.BottomEnd),
                        )
                    }
                }
            }
            item {
                ListItem(
                    headlineContent = {
                        Text(text = movieDetails.title)
                    },
                    supportingContent = {
                        Text(text = movieDetails.overview)
                    },
                    overlineContent = {
                        Text(text = movieDetails.genreNames.joinToString(stringResource(R.string.genre_seprator)))
                    },
                    trailingContent = {
                        Text(text = movieDetails.releaseDate)
                    },
                )
            }
            item {
                RatingView(
                    rating = movieDetails.voteAverage,
                    modifier = Modifier.padding(horizontal = Dimensions.paddingLarge),
                )
                Spacer(modifier = Modifier.height(Dimensions.paddingSmall))
            }
            item {
                Text(
                    text = stringResource(R.string.cast),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = Dimensions.paddingLarge),
                )
                CastListView(castList)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsViewPreview() {
    MovieDetailsView(
        movieDetails =
            MovieDetails(
                id = 1,
                title = "Movie Title",
                backdropPath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                genreNames = listOf("Action", "Adventure"),
                overview = "Movie Overview",
                voteAverage = 7.5,
                releaseDate = "2021-09-01",
                runtime = 120,
                status = "Released",
                tagline = "Movie Tagline",
            ),
        castList =
            listOf(
                Cast(
                    id = 1,
                    name = "Actor 1",
                    character = "Character 1",
                    profilePath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Cast(
                    id = 2,
                    name = "Actor 2",
                    character = "Character 2",
                    profilePath = "https://image.tmdb.org/t/p/w500/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
            ),
    )
}

package com.vishesh.movielist.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vishesh.designsystem.component.Dimensions
import com.vishesh.movielist.R

@Composable
fun MovieListItem(
    title: String,
    posterUrl: String,
    genreNames: List<String>,
    overview: String,
    voteAverage: Double,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            Text(text = title)
        },
        modifier = modifier.clickable { onItemClick() },
        leadingContent = {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(posterUrl)
                        .error(android.R.drawable.ic_menu_report_image)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .build(),
                contentDescription = title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(Dimensions.aspectRatioOneThird),
            )
        },
        overlineContent = {
            Text(text = genreNames.joinToString(separator = stringResource(R.string.genre_seprator)))
        },
        supportingContent = {
            Text(
                text = overview,
                maxLines = Dimensions.maxLinesBigItem,
                overflow = TextOverflow.Ellipsis,
            )
        },
        trailingContent = {
            Text(text = voteAverage.toString())
        },
    )
}

@Preview
@Composable
private fun MovieListItemPreview() {
    MovieListItem(
        title = "The Terminator",
        posterUrl = "https://image.tmdb.org/t/p/w500/8bRIfStfY6jG9GbzpJ3AKdI8hPE.jpg",
        genreNames = listOf("Action", "Adventure"),
        overview = "Imprisoned in the 1980s, and his future self is tasked with tracking down and terminating a future serial killer.",
        voteAverage = 8.0,
        onItemClick = {},
    )
}


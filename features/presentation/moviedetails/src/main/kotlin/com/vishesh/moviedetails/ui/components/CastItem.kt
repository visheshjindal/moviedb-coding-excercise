package com.vishesh.moviedetails.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vishesh.designsystem.component.Dimensions

@Composable
fun CastItem(
    profileUrl: String,
    name: String,
    character: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(Dimensions.thumbnailSizeSquare)
                .padding(Dimensions.paddingExtraSmall),
        horizontalAlignment = Alignment.Start,
    ) {
        Card(
            modifier =
                Modifier
                    .width(Dimensions.thumbnailSizeSquare)
                    .aspectRatio(Dimensions.aspectRatioSquare),
        ) {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(profileUrl)
                        .error(android.R.drawable.ic_menu_report_image)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .build(),
                contentDescription = name,
                contentScale = ContentScale.FillBounds,
            )
        }
        Column(modifier = Modifier.padding(Dimensions.paddingSmall)) {
            Text(
                text = name,
                maxLines = Dimensions.singleLine,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = character,
                minLines = Dimensions.doubleLine,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun CastItemPreview() {
    CastItem(
        profileUrl = "https://image.tmdb.org/t/p/w185/8YyJzKBtZGzU2k2pG6l8e1d8Izx.jpg",
        name = "Tom Holland",
        character = "Peter Parker / Spider",
    )
}

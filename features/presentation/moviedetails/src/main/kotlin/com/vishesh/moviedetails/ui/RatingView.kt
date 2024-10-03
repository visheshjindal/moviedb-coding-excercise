package com.vishesh.moviedetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vishesh.moviedetail.R

@Composable
internal fun RatingView(
    rating: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(R.string.overall_rating),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
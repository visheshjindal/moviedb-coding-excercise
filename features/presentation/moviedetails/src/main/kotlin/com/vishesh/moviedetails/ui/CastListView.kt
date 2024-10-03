package com.vishesh.moviedetails.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vishesh.designsystem.component.Dimensions
import com.vishesh.domain.entities.Cast
import com.vishesh.moviedetails.ui.components.CastItem

@Composable
internal fun CastListView(
    casts: List<Cast>,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier) {
        item { Spacer(modifier = Modifier.width(Dimensions.paddingMedium)) }
        items(casts, key = { cast -> cast.id }) { cast ->
            CastItem(
                profileUrl = cast.profilePath,
                name = cast.name,
                character = cast.character,
            )
        }
    }
}

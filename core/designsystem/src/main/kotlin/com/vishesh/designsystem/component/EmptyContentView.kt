package com.vishesh.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmptyContent(
    text: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text, modifier = Modifier.wrapContentWidth(), textAlign = TextAlign.Center)
        TextButton(
            onClick = onButtonClick,
            modifier = Modifier.wrapContentSize(),
        ) { Text(buttonText) }
    }
}

@Preview
@Composable
private fun EmptyContentPreview() {
    EmptyContent(
        text = "lorep ipusm dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        buttonText = "Try again",
    )
}

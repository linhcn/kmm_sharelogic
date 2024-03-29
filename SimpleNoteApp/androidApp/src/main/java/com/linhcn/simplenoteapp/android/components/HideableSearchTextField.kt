package com.linhcn.simplenoteapp.android.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhcn.simplenoteapp.android.MyApplicationTheme

@Composable
fun HideableSearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    isSearchActive: Boolean,
    onCloseClick: () -> Unit,
    onSearchClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            // Input search value
            TransparentTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp
                ),
                onFocusChanged = {},
                hint = hint,
                singleLine = true,
                isHintVisible = value.isEmpty(),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50.dp))
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(start = 10.dp, end = 35.dp)
            )
        }
        // Open search button
        AnimatedVisibility(
            visible = !isSearchActive,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Open Search")
            }
        }
        // Close search button
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = onCloseClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Search")
            }
        }
    }
}


@Preview
@Composable
fun HideableSearchTextFieldPreview() {
    MyApplicationTheme(darkTheme = true) {
        HideableSearchTextField(
            value = "",
            isSearchActive = true,
            onCloseClick = {},
            onSearchClick = {},
            onValueChange = {},
            hint = "Search"
        )
    }
}
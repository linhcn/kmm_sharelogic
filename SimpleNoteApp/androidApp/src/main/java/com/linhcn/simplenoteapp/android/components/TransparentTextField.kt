package com.linhcn.simplenoteapp.android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhcn.simplenoteapp.android.MyApplicationTheme

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    isHintVisible: Boolean,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
    singleLine: Boolean = false,
    onFocusChanged: (FocusState) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            readOnly = readOnly,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state -> onFocusChanged(state) }
        ) { innerTextField ->
            if (isHintVisible) {
                Text(text = hint, style = textStyle.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)))
            }
            innerTextField()
        }
    }
}

@Preview
@Composable
fun TransparentTextFieldPreview() {
    MyApplicationTheme(darkTheme = true) {
        TransparentTextField(
            value = "",
            hint = "Type something",
            isHintVisible = true,
            readOnly = true,
            onValueChange = {},
            onFocusChanged = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
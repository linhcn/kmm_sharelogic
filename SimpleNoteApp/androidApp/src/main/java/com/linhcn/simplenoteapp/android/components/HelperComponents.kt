package com.linhcn.simplenoteapp.android.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.linhcn.simplenoteapp.presentation.Strings
import dev.icerock.moko.resources.StringResource

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}
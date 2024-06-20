package com.linhcn.simplenoteapp.android.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.linhcn.simplenoteapp.resources.SharedRes
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.format

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return id.format(args).toString(LocalContext.current)
}
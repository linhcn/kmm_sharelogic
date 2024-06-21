package com.linhcn.simplenoteapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhcn.simplenoteapp.resources.SharedRes

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            secondary = Color(0xFF494949),
            onSecondary = Color(0xFFFFFFFF),
            tertiary = Color(0xFF3700B3),
            surface = Color(0xFF1B1B1B),
            onSurface = Color(0xFFE9E9E9),
            background = Color(0xFF1B1B1B)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFFCCCCCC),
            onSecondary = Color(0xFF2C2C2C),
            tertiary = Color(0xFF3700B3),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF1B1B1B),
            background = Color(0xFFFFFFFF)
        )
    }
    val typography = Typography(
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = myFontFamily),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = myFontFamily),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = myFontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = myFontFamily)
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(6.dp),
        large = RoundedCornerShape(8.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

val myFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = SharedRes.fonts.playwrite_regular.fontResourceId,
            weight = FontWeight.Normal,
        ),
        Font(
            resId = SharedRes.fonts.playwrite_thin.fontResourceId,
            weight = FontWeight.Thin,
        ),
        Font(
            resId = SharedRes.fonts.playwrite_light.fontResourceId,
            weight = FontWeight.Light,
        ),
        Font(
            resId = SharedRes.fonts.playwrite_extralight.fontResourceId,
            weight = FontWeight.ExtraLight,
        ),
    )
)

internal val defaultTypography = Typography()
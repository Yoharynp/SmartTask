package com.example.smarttask.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = primaryColorDark,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = backGroundDark,
    surface = backGroundDark,
    onBackground = textColorDark,
    onSurface = textColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColorLight,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = backGroundLight,
    surface = backGroundLight,
    onBackground = textColorLight,
    onSurface = textColorLight
)

data class CustomColors(
    val iconBackground: androidx.compose.ui.graphics.Color,
    val primaryColor: androidx.compose.ui.graphics.Color,
    val backgroundTextFieldColor: androidx.compose.ui.graphics.Color,
    val textColor: androidx.compose.ui.graphics.Color,
    val textSecondaryColor: androidx.compose.ui.graphics.Color,
    val backgroundColor: androidx.compose.ui.graphics.Color,
    val borderColor: androidx.compose.ui.graphics.Color,
    val textFieldBackgroundColor: androidx.compose.ui.graphics.Color
)

private val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("No se han proporcionado CustomColors")
}

val customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current

@Composable
fun ProvideCustomColors(darkTheme: Boolean ,content: @Composable () -> Unit) {
    val colors = if (!darkTheme) {
        CustomColors(
            iconBackground = iconBackgroundColorLight,
            primaryColor = primaryColorLight,
            backgroundTextFieldColor = backGroundTextFieldColorLight,
            textColor = textColorLight,
            textSecondaryColor = textSecondaryColorLight,
            backgroundColor = backGroundLight,
            borderColor = borderColorLight,
            textFieldBackgroundColor = TextFieldBackgroundColorLight
        )
    } else {
        CustomColors(
            iconBackground = iconBackgroundColorDark,
            primaryColor = primaryColorDark,
            backgroundTextFieldColor = backGroundTextFieldColorDark,
            textColor = textColorDark,
            textSecondaryColor = textSecondaryColorDark,
            backgroundColor = backGroundDark,
            borderColor = borderColorDark,
            textFieldBackgroundColor = TextFieldBackgroundColorDark
        )
    }

    CompositionLocalProvider(LocalCustomColors provides colors) {
        content()
    }
}

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)
@Composable
fun SmartTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes
    ) {
        ProvideCustomColors(darkTheme) {
            content()
        }
    }
}
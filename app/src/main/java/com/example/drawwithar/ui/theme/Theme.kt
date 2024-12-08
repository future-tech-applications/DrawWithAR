package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.drawwithar.ui.theme.backgroundDark
import com.example.drawwithar.ui.theme.backgroundDarkHighContrast
import com.example.drawwithar.ui.theme.backgroundDarkMediumContrast
import com.example.drawwithar.ui.theme.backgroundLight
import com.example.drawwithar.ui.theme.backgroundLightHighContrast
import com.example.drawwithar.ui.theme.backgroundLightMediumContrast
import com.example.drawwithar.ui.theme.errorContainerDark
import com.example.drawwithar.ui.theme.errorContainerDarkHighContrast
import com.example.drawwithar.ui.theme.errorContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.errorContainerLight
import com.example.drawwithar.ui.theme.errorContainerLightHighContrast
import com.example.drawwithar.ui.theme.errorContainerLightMediumContrast
import com.example.drawwithar.ui.theme.errorDark
import com.example.drawwithar.ui.theme.errorDarkHighContrast
import com.example.drawwithar.ui.theme.errorDarkMediumContrast
import com.example.drawwithar.ui.theme.errorLight
import com.example.drawwithar.ui.theme.errorLightHighContrast
import com.example.drawwithar.ui.theme.errorLightMediumContrast
import com.example.drawwithar.ui.theme.inverseOnSurfaceDark
import com.example.drawwithar.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.drawwithar.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.drawwithar.ui.theme.inverseOnSurfaceLight
import com.example.drawwithar.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.drawwithar.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.drawwithar.ui.theme.inversePrimaryDark
import com.example.drawwithar.ui.theme.inversePrimaryDarkHighContrast
import com.example.drawwithar.ui.theme.inversePrimaryDarkMediumContrast
import com.example.drawwithar.ui.theme.inversePrimaryLight
import com.example.drawwithar.ui.theme.inversePrimaryLightHighContrast
import com.example.drawwithar.ui.theme.inversePrimaryLightMediumContrast
import com.example.drawwithar.ui.theme.inverseSurfaceDark
import com.example.drawwithar.ui.theme.inverseSurfaceDarkHighContrast
import com.example.drawwithar.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.drawwithar.ui.theme.inverseSurfaceLight
import com.example.drawwithar.ui.theme.inverseSurfaceLightHighContrast
import com.example.drawwithar.ui.theme.inverseSurfaceLightMediumContrast
import com.example.drawwithar.ui.theme.onBackgroundDark
import com.example.drawwithar.ui.theme.onBackgroundDarkHighContrast
import com.example.drawwithar.ui.theme.onBackgroundDarkMediumContrast
import com.example.drawwithar.ui.theme.onBackgroundLight
import com.example.drawwithar.ui.theme.onBackgroundLightHighContrast
import com.example.drawwithar.ui.theme.onBackgroundLightMediumContrast
import com.example.drawwithar.ui.theme.onErrorContainerDark
import com.example.drawwithar.ui.theme.onErrorContainerDarkHighContrast
import com.example.drawwithar.ui.theme.onErrorContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.onErrorContainerLight
import com.example.drawwithar.ui.theme.onErrorContainerLightHighContrast
import com.example.drawwithar.ui.theme.onErrorContainerLightMediumContrast
import com.example.drawwithar.ui.theme.onErrorDark
import com.example.drawwithar.ui.theme.onErrorDarkHighContrast
import com.example.drawwithar.ui.theme.onErrorDarkMediumContrast
import com.example.drawwithar.ui.theme.onErrorLight
import com.example.drawwithar.ui.theme.onErrorLightHighContrast
import com.example.drawwithar.ui.theme.onErrorLightMediumContrast
import com.example.drawwithar.ui.theme.onPrimaryContainerDark
import com.example.drawwithar.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.onPrimaryContainerLight
import com.example.drawwithar.ui.theme.onPrimaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.onPrimaryDark
import com.example.drawwithar.ui.theme.onPrimaryDarkHighContrast
import com.example.drawwithar.ui.theme.onPrimaryDarkMediumContrast
import com.example.drawwithar.ui.theme.onPrimaryLight
import com.example.drawwithar.ui.theme.onPrimaryLightHighContrast
import com.example.drawwithar.ui.theme.onPrimaryLightMediumContrast
import com.example.drawwithar.ui.theme.onSecondaryContainerDark
import com.example.drawwithar.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.onSecondaryContainerLight
import com.example.drawwithar.ui.theme.onSecondaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.onSecondaryDark
import com.example.drawwithar.ui.theme.onSecondaryDarkHighContrast
import com.example.drawwithar.ui.theme.onSecondaryDarkMediumContrast
import com.example.drawwithar.ui.theme.onSecondaryLight
import com.example.drawwithar.ui.theme.onSecondaryLightHighContrast
import com.example.drawwithar.ui.theme.onSecondaryLightMediumContrast
import com.example.drawwithar.ui.theme.onSurfaceDark
import com.example.drawwithar.ui.theme.onSurfaceDarkHighContrast
import com.example.drawwithar.ui.theme.onSurfaceDarkMediumContrast
import com.example.drawwithar.ui.theme.onSurfaceLight
import com.example.drawwithar.ui.theme.onSurfaceLightHighContrast
import com.example.drawwithar.ui.theme.onSurfaceLightMediumContrast
import com.example.drawwithar.ui.theme.onSurfaceVariantDark
import com.example.drawwithar.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.drawwithar.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.drawwithar.ui.theme.onSurfaceVariantLight
import com.example.drawwithar.ui.theme.onSurfaceVariantLightHighContrast
import com.example.drawwithar.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.drawwithar.ui.theme.onTertiaryContainerDark
import com.example.drawwithar.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.onTertiaryContainerLight
import com.example.drawwithar.ui.theme.onTertiaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.onTertiaryDark
import com.example.drawwithar.ui.theme.onTertiaryDarkHighContrast
import com.example.drawwithar.ui.theme.onTertiaryDarkMediumContrast
import com.example.drawwithar.ui.theme.onTertiaryLight
import com.example.drawwithar.ui.theme.onTertiaryLightHighContrast
import com.example.drawwithar.ui.theme.onTertiaryLightMediumContrast
import com.example.drawwithar.ui.theme.outlineDark
import com.example.drawwithar.ui.theme.outlineDarkHighContrast
import com.example.drawwithar.ui.theme.outlineDarkMediumContrast
import com.example.drawwithar.ui.theme.outlineLight
import com.example.drawwithar.ui.theme.outlineLightHighContrast
import com.example.drawwithar.ui.theme.outlineLightMediumContrast
import com.example.drawwithar.ui.theme.outlineVariantDark
import com.example.drawwithar.ui.theme.outlineVariantDarkHighContrast
import com.example.drawwithar.ui.theme.outlineVariantDarkMediumContrast
import com.example.drawwithar.ui.theme.outlineVariantLight
import com.example.drawwithar.ui.theme.outlineVariantLightHighContrast
import com.example.drawwithar.ui.theme.outlineVariantLightMediumContrast
import com.example.drawwithar.ui.theme.primaryContainerDark
import com.example.drawwithar.ui.theme.primaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.primaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.primaryContainerLight
import com.example.drawwithar.ui.theme.primaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.primaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.primaryDark
import com.example.drawwithar.ui.theme.primaryDarkHighContrast
import com.example.drawwithar.ui.theme.primaryDarkMediumContrast
import com.example.drawwithar.ui.theme.primaryLight
import com.example.drawwithar.ui.theme.primaryLightHighContrast
import com.example.drawwithar.ui.theme.primaryLightMediumContrast
import com.example.drawwithar.ui.theme.scrimDark
import com.example.drawwithar.ui.theme.scrimDarkHighContrast
import com.example.drawwithar.ui.theme.scrimDarkMediumContrast
import com.example.drawwithar.ui.theme.scrimLight
import com.example.drawwithar.ui.theme.scrimLightHighContrast
import com.example.drawwithar.ui.theme.scrimLightMediumContrast
import com.example.drawwithar.ui.theme.secondaryContainerDark
import com.example.drawwithar.ui.theme.secondaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.secondaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.secondaryContainerLight
import com.example.drawwithar.ui.theme.secondaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.secondaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.secondaryDark
import com.example.drawwithar.ui.theme.secondaryDarkHighContrast
import com.example.drawwithar.ui.theme.secondaryDarkMediumContrast
import com.example.drawwithar.ui.theme.secondaryLight
import com.example.drawwithar.ui.theme.secondaryLightHighContrast
import com.example.drawwithar.ui.theme.secondaryLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceBrightDark
import com.example.drawwithar.ui.theme.surfaceBrightDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceBrightDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceBrightLight
import com.example.drawwithar.ui.theme.surfaceBrightLightHighContrast
import com.example.drawwithar.ui.theme.surfaceBrightLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerDark
import com.example.drawwithar.ui.theme.surfaceContainerDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighDark
import com.example.drawwithar.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighLight
import com.example.drawwithar.ui.theme.surfaceContainerHighLightHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighestDark
import com.example.drawwithar.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighestLight
import com.example.drawwithar.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerLight
import com.example.drawwithar.ui.theme.surfaceContainerLightHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowDark
import com.example.drawwithar.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowLight
import com.example.drawwithar.ui.theme.surfaceContainerLowLightHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowestDark
import com.example.drawwithar.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowestLight
import com.example.drawwithar.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.drawwithar.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceDark
import com.example.drawwithar.ui.theme.surfaceDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceDimDark
import com.example.drawwithar.ui.theme.surfaceDimDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceDimDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceDimLight
import com.example.drawwithar.ui.theme.surfaceDimLightHighContrast
import com.example.drawwithar.ui.theme.surfaceDimLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceLight
import com.example.drawwithar.ui.theme.surfaceLightHighContrast
import com.example.drawwithar.ui.theme.surfaceLightMediumContrast
import com.example.drawwithar.ui.theme.surfaceVariantDark
import com.example.drawwithar.ui.theme.surfaceVariantDarkHighContrast
import com.example.drawwithar.ui.theme.surfaceVariantDarkMediumContrast
import com.example.drawwithar.ui.theme.surfaceVariantLight
import com.example.drawwithar.ui.theme.surfaceVariantLightHighContrast
import com.example.drawwithar.ui.theme.surfaceVariantLightMediumContrast
import com.example.drawwithar.ui.theme.tertiaryContainerDark
import com.example.drawwithar.ui.theme.tertiaryContainerDarkHighContrast
import com.example.drawwithar.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.drawwithar.ui.theme.tertiaryContainerLight
import com.example.drawwithar.ui.theme.tertiaryContainerLightHighContrast
import com.example.drawwithar.ui.theme.tertiaryContainerLightMediumContrast
import com.example.drawwithar.ui.theme.tertiaryDark
import com.example.drawwithar.ui.theme.tertiaryDarkHighContrast
import com.example.drawwithar.ui.theme.tertiaryDarkMediumContrast
import com.example.drawwithar.ui.theme.tertiaryLight
import com.example.drawwithar.ui.theme.tertiaryLightHighContrast
import com.example.drawwithar.ui.theme.tertiaryLightMediumContrast
import com.example.drawwithar.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun DrawWithARTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}


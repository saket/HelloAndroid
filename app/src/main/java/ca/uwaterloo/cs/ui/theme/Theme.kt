package ca.uwaterloo.cs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun HelloAndroidTheme(content: @Composable () -> Unit) {
  val darkColors = darkColors(
    background = Color.VeryDarkGray,
    surface = Color.VeryDarkGray,
    primary = Color.VeryDarkGray,
  )
  val lightColors = lightColors(
    primary = Color.White,
    onPrimary = Color.DarkGray,
    surface = Color.DarkGray,
  )
  MaterialTheme(
    colors = if (isSystemInDarkTheme()) darkColors else lightColors,
    content = {
      val defaultTextStyle = LocalTextStyle.current.copy(
        color = MaterialTheme.colors.onBackground
      )
      CompositionLocalProvider(LocalTextStyle provides defaultTextStyle) {
        content()
      }
    }
  )
}

val Color.Companion.VeryDarkGray get() = Color(0xFF121212)
val Color.Companion.InstagramOrange get() = Color(0xFFFF7A00)
val Color.Companion.InstagramPeach get() = Color(0xFFFF0069)
val Color.Companion.InstagramPurple get() = Color(0xFFD300C5)

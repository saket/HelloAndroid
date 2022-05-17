package ca.uwaterloo.cs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun HelloAndroidTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colors = if (isSystemInDarkTheme()) darkColors() else lightColors(),
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

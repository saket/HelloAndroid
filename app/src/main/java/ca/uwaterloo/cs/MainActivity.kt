package ca.uwaterloo.cs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uwaterloo.cs.ui.theme.HelloAndroidTheme
import androidx.compose.ui.*  // Wildcard imports are terrible, but this will avoid confusion from duplicate imports for beginners.

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HelloAndroidTheme {
        MainContent()
      }
    }
  }
}

@Composable
fun MainContent() {
  Box(
    Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.background),
  ) {
    Text(
      modifier = Modifier
        .align(Alignment.Center)
        .padding(64.dp),
      text = "🦄🐳🐈🐿🦔🌞",
      fontSize = 64.sp,
      textAlign = TextAlign.Center
    )
  }
}

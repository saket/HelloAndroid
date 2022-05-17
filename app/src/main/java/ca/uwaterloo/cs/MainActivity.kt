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
import ca.uwaterloo.cs.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainContent()
    }
  }
}

@Composable
fun MainContent() {
  HelloAndroidTheme {
    Box(
      Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
    ) {
      Text(
        modifier = Modifier
          .align(Alignment.Center)
          .padding(64.dp),
        text = "If you're able to see this screen then you're ready!",
        textAlign = TextAlign.Center
      )
    }
  }
}

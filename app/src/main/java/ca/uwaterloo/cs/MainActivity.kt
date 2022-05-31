package ca.uwaterloo.cs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uwaterloo.cs.ui.theme.HelloAndroidTheme
import ca.uwaterloo.cs.ui.theme.InstagramOrange
import ca.uwaterloo.cs.ui.theme.InstagramPeach
import ca.uwaterloo.cs.ui.theme.InstagramPurple
import coil.compose.AsyncImage
import kotlin.concurrent.thread

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
    val clickedDogImage = remember { mutableStateOf<String?>(null) }

    Column {
      Text(
        modifier = Modifier.padding(16.dp),
        text = "Instagram",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
      )
      Row(
        Modifier
          .horizontalScroll(rememberScrollState())
          .padding(horizontal = 16.dp, vertical = 16.dp)
      ) {
        val dogImages = remember { mutableStateOf(emptyList<String>()) }

        LaunchedEffect(Unit) {
          val presenter = HomePresenter()
          dogImages.value = presenter.fetchDogImages()
        }

        for (dogImage in dogImages.value) {
          StoryAvatar(
            imageUrl = dogImage,
            onClick = {
              clickedDogImage.value = dogImage
            }
          )
        }
      }
    }

    if (clickedDogImage.value != null) {
      FullScreenStory(
        imageUrl = clickedDogImage.value!!,
        onClick = {
          clickedDogImage.value = null
        }
      )
    }
  }
}

@Composable
fun FullScreenStory(imageUrl: String, onClick: () -> Unit) {
  AsyncImage(
    modifier = Modifier
      .fillMaxSize()
      .clickable { onClick() },
    model = imageUrl,
    contentDescription = null,
    contentScale = ContentScale.Crop,
  )
}

@Composable
fun StoryAvatar(imageUrl: String, onClick: () -> Unit) {
  Box(
    Modifier
      .padding(end = 8.dp)
      .border(
        width = 2.dp,
        brush = Brush.verticalGradient(listOf(Color.InstagramOrange, Color.InstagramPeach, Color.InstagramPurple)),
        shape = CircleShape
      )
      .padding(6.dp)
      .size(60.dp)
      .clip(CircleShape)
      .background(Color.LightGray)
      .clickable { onClick() }
  ) {
    AsyncImage(
      model = imageUrl,
      contentDescription = null,
      contentScale = ContentScale.Crop,
    )
  }
}

package ca.uwaterloo.cs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
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
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import ca.uwaterloo.cs.ui.theme.HelloAndroidTheme
import ca.uwaterloo.cs.ui.theme.InstagramOrange
import ca.uwaterloo.cs.ui.theme.InstagramPeach
import ca.uwaterloo.cs.ui.theme.InstagramPurple
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainContent()
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
  HelloAndroidTheme {
    Box(
      Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
    ) {
      var isStoryVisible by remember { mutableStateOf(false) }
      var clickedStoryPosition: IntOffset by remember { mutableStateOf(IntOffset.Zero) }

      Column {
        TopAppBar(
          title = { Text("Instagram") }
        )
        Row(
          Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
          repeat(10) {
            StoryAvatar(
              onClick = { offset ->
                clickedStoryPosition = offset
                isStoryVisible = true
              }
            )
          }
        }
      }

      AnimatedVisibility(
        visible = isStoryVisible,
        enter = scaleIn(transformOrigin = TransformOrigin.TopLeft) + slideIn(initialOffset = { clickedStoryPosition }),
        exit = scaleOut(transformOrigin = TransformOrigin.TopLeft) + slideOut(targetOffset = { clickedStoryPosition }),
      ) {
        FullScreenStory()
      }
      BackHandler(enabled = isStoryVisible) {
        isStoryVisible = false
      }
    }
  }
}

@Composable
private fun StoryAvatar(onClick: (position: IntOffset) -> Unit) {
  var position: IntOffset? by remember { mutableStateOf(null) }

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
      .onGloballyPositioned { coordinates ->
        position = coordinates
          .positionInRoot()
          .round() + coordinates.size.center
      }
      .clickable {
        onClick(position!!)
      }
  )
}

@Composable
private fun FullScreenStory() {
  Image(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.DarkGray),
    painter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1571972269933-1289cd01e0aa?w=1080"),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}

private val TransformOrigin.Companion.TopLeft
  get() = TransformOrigin(pivotFractionX = 0f, pivotFractionY = 0f)

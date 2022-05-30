@file:Suppress("SameParameterValue")

package ca.uwaterloo.cs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Presenter for the home/main screen. In a real world application, this class would
 * take an interface for running HTTP calls so that they can be faked in unit test.
 */
class HomePresenter {
  suspend fun present(): HomeModel {
    val doggoImages = withContext(Dispatchers.IO) {
      try {
        downloadDoggoImages(count = 10)
      } catch (e: Throwable) {
        e.printStackTrace()
        // Network call failed.
        emptyList()
      }
    }

    return HomeModel(
      stories = doggoImages.map { url ->
        HomeModel.Story(imageUrl = url)
      }
    )
  }

  private fun downloadDoggoImages(count: Int): List<String> {
    val request = Request(
      url = "https://dog.ceo/api/breeds/image/random/$count".toHttpUrl(),
      method = "GET"
    )
    val response = OkHttpClient().newCall(request).execute()
    val responseBody = Json.decodeFromString<DogCeoResponseBody>(response.body.string())
    return responseBody.message
  }
}

data class HomeModel(
  val stories: List<Story> = emptyList()
) {

  data class Story(
    val imageUrl: String
  )
}

@Serializable
private data class DogCeoResponseBody(
  val message: List<String>,
  val status: String,
)

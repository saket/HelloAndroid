package ca.uwaterloo.cs

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttp
import okhttp3.OkHttpClient

class HomePresenter {
  fun fetchDogImages(): List<String> {
    val request = okhttp3.Request(
      url = "https://dog.ceo/api/breeds/image/random/10".toHttpUrl()
    )
    val response = OkHttpClient().newCall(request).execute()

    val json = response.body.string()
    val responseBody = Json.decodeFromString<DogCeoResponseBody>(json)
    return responseBody.message
  }
}

@Serializable
data class DogCeoResponseBody(
  val message: List<String>,
  val status: String,
)

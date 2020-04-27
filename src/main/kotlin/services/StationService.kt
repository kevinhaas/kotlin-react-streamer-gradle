package services

import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.fetch.RequestInit
import view.index.model.Result
import view.index.model.Station
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlin.js.json

class StationService(private val coroutineContext: CoroutineContext) {
    private val serializer = Json(JsonConfiguration.Stable)

    suspend fun getStations(url: String = "", limit: Int = 20): Result {
        val (response, stationsJson) = fetchStations("$url/json/stations?limit=$limit")
        console.log("stationsJson")
        console.log(serializer.parse(Station.serializer().list, stationsJson))
        return Result(
            response,
            serializer.parse(Station.serializer().list, stationsJson)
        )
    }

    private suspend fun fetchStations(url: String = ""): Result
        = withContext(coroutineContext) {
            val response = window.fetch("http://$url", RequestInit(
                "GET",
                headers = json(
                    "Accept" to "application/json",
                    "Content-Type" to "application/json"
                )
            )).await()

            console.log("response")
            console.log(response)

            return@withContext Result(
                response,
                response.text().await()
            )
    }
}
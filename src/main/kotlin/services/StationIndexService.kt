package services

import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.fetch.RequestInit
import view.index.API_SERVERS_URL
import view.index.model.Result
import view.index.model.StationIndex
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlin.js.json

// Station index is used as the API endpoint for all requests
class StationIndexService(private val coroutineContext: CoroutineContext) {
    //TODO: Make serializer easily accessible by other services
    private val serializer = Json(JsonConfiguration.Stable)

    //TODO: Handle retrying:
    //  when(response.status) {
    //      in 200..299 ->
    //      else -> retry
    //  }
    suspend fun getRandomStationIndex(): Result {
        val (response, serversJson) = fetchStationIndexes()
        console.log("serversJson")
        console.log(serializer.parse(StationIndex.serializer().list, serversJson).random())
        return Result(
            response,
            serializer.parse(StationIndex.serializer().list, serversJson).random()
        )
    }

    private suspend fun fetchStationIndexes(url: String = API_SERVERS_URL): Result
        = withContext(coroutineContext) {
            val response = window.fetch(url, RequestInit(
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
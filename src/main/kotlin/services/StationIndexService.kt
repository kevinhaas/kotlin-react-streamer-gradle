package services

import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit
import view.index.API_SERVERS_URL
import view.index.CORS_PROXY_URL
import view.index.DEFAULT_HEADERS
import view.index.model.Result
import view.index.model.StationIndex

// Station index is used as the API endpoint for all requests
class StationIndexService(private val coroutineContext: CoroutineContext, private val jsonSerializer: Json) {
    suspend fun getAvailableStationIndex(): StationIndex {
        val (response, serversJson) = fetchStationIndexes()

        // Can't get any station indexes
        if (response.status !in 200..299) {
            throw Throwable(response.statusText)
        }

        val stationIndexes = jsonSerializer.parse(StationIndex.serializer().list, serversJson)

        // Get the first station index that responds successfully
        return stationIndexes.first {
            window.fetch("${CORS_PROXY_URL}http://${it.url}/stats", RequestInit(
                    "GET"
            )).await().status in 200..399
        }
    }

    private suspend fun fetchStationIndexes(url: String = API_SERVERS_URL): Result =
        withContext(coroutineContext) {
            val response = window.fetch(url, RequestInit(
                "GET",
                DEFAULT_HEADERS
            )).await()

            return@withContext Result(
                response,
                response.text().await()
            )
    }
}

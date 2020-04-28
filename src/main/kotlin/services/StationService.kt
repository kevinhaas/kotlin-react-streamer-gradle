package services

import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit
import view.index.CORS_PROXY_URL
import view.index.DEFAULT_HEADERS
import view.index.model.Result
import view.index.model.Station
import view.index.model.StationIndex

class StationService(
    private val stationIndex: StationIndex,
    private val coroutineContext: CoroutineContext,
    private val jsonSerializer: Json
) {
        // Some Station Index servers have CORS disabled, temp work around
        private val stationIndexUrl = "${CORS_PROXY_URL}http://${stationIndex.url}/json/stations"

        suspend fun getStations(limit: Int = 20): List<Station> {
            val (response, stationsJson) = fetchStations("?limit=$limit")

            // can't get any stations
            if (response.status !in 200..299) {
                throw Throwable(response.statusText)
            }

            return jsonSerializer.parse(Station.serializer().list, stationsJson)
        }

        private suspend fun fetchStations(queryParams: String = ""): Result =
            withContext(coroutineContext) {
                val response = window.fetch("$stationIndexUrl$queryParams", RequestInit(
                    "GET",
                    DEFAULT_HEADERS
                )).await()

                return@withContext Result(
                    response,
                    response.text().await()
                )
        }
}

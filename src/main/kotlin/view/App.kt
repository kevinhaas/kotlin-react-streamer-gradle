package view

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h2
import react.dom.p
import react.setState
import services.StationIndexService
import services.StationService
import view.components.station
import view.index.model.Station

interface AppProps : RProps {
    var coroutineScope: CoroutineScope
    var jsonSerializer: Json
}

class AppState : RState {
    var stations: List<Station> = listOf()
    var loadingStations: Boolean = true
    var errorMessage: String = ""
}

class App : RComponent<AppProps, AppState>() {
    init {
        state = AppState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    private val jsonSerializer
        get() = props.jsonSerializer

    override fun componentDidMount() {
        val stationIndexService = StationIndexService(coroutineContext, jsonSerializer)

        props.coroutineScope.launch {
            try {
                // get a station index to use as API endpoint
                val stationIndex = stationIndexService.getAvailableStationIndex()

                // get/set stations
                val stationService = StationService(stationIndex, coroutineContext, jsonSerializer)
                val initialStations = stationService.getStations()
                setState {
                    stations = initialStations
                    loadingStations = false
                }
            } catch (t: Throwable) {
                setState {
                    loadingStations = false
                    errorMessage = ":("
                }
            }
        }
    }

    override fun RBuilder.render() {
        div {
            +state.errorMessage
            h2 {
                +"Stations"
            }

            if (state.loadingStations) {
                p { +"Loading..." }
            }

            state.stations.map {
                station(it)
            }
        }
    }
}

fun RBuilder.app(coroutineScope: CoroutineScope, jsonSerializer: Json) = child(App::class) {
    attrs.coroutineScope = coroutineScope
    attrs.jsonSerializer = jsonSerializer
}

package view

import react.*
import react.dom.*
import kotlinx.coroutines.*
import services.StationIndexService
import services.StationService
import view.index.model.Station
import view.index.model.StationIndex

interface AppProps : RProps {
    var coroutineScope: CoroutineScope
}

class AppState : RState {
    var stationIndex: StationIndex = StationIndex()
    var stations: List<Station> = listOf()
    var errorMessage: String = ""
}

class App : RComponent<AppProps, AppState>() {
    init {
        state = AppState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        val stationIndexService = StationIndexService(coroutineContext)
        val stationService = StationService(coroutineContext)

        props.coroutineScope.launch {
            // get/set a random station index to use as API endpoint
            val (stationIndexResponse, randomStationIndex) = stationIndexService.getRandomStationIndex()
            setState {
                stationIndex = randomStationIndex as StationIndex
            }

            // get/set stations
            val (stationsResponse, initialStations) = stationService.getStations(state.stationIndex.url)
            setState {
                stations = initialStations as List<Station>
            }
        }
    }

    override fun RBuilder.render() {
        div {
            h2 {
                +"Stations"
            }
            state.stations.map {
                p("station") {
                    +it.name
                    +it.homepage
                    +it.url
                    +it.tags
                }
            }
        }
    }
}

fun RBuilder.app(coroutineScope: CoroutineScope) = child(App::class) {
    attrs.coroutineScope = coroutineScope
}

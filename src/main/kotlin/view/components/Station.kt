package view.components

import react.RBuilder
import react.dom.div
import react.dom.p
import view.index.model.Station

fun RBuilder.station(station: Station) {
    div("station") {
        p { +"Name: ${station.name}" }
        p { +"Homepage: ${station.homepage}" }
        p { +"URL: ${station.url}" }
        p { +"Tags: ${station.tags}" }
    }
}

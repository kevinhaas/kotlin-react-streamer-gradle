package view.index.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Station(
    @SerialName("stationuuid")
    val stationUUID: String,
    val name: String,
    val url: String,
    val url_resolved: String,
    val homepage: String,
    @SerialName("favicon")
    val faviconUrl: String,
    val tags: String,
    @SerialName("countrycode")
    val countryCode: String,
    val codec: String,
    val bitrate: Int
)

package view.index.model

import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val stationUUID: String,
    val name: String,
    val url: String,
    val url_resolved: String,
    val homepage: String,
    val faviconUrl: String,
    val tags: String,
    val countryCode: String,
    val codec: String,
    val bitrate: Int
)

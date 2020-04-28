package view.index.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationIndex(
    val ip: String = "",
    @SerialName("name")
    val url: String = ""
)

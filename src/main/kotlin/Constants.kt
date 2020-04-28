package view.index

import kotlin.js.json

const val API_SERVERS_URL = "http://all.api.radio-browser.info/json/servers"
const val CORS_PROXY_URL = "https://cors-anywhere.herokuapp.com/"
val DEFAULT_HEADERS = json(
    "Accept" to "application/json",
    "Content-Type" to "application/json"
)

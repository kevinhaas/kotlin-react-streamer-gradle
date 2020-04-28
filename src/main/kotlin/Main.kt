package view.index

import kotlin.browser.document
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import react.dom.render
import view.app

private class Application : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()
    private val jsonSerializer = Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true, isLenient = true))

    fun start() {
        render(document.getElementById("root")) {
            app(this@Application, jsonSerializer)
        }
    }
}

fun main(args: Array<String>) {
    Application().start()
}

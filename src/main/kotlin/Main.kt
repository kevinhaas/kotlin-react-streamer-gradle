package view.index

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import react.dom.*
import view.app
import kotlin.browser.*
import kotlin.coroutines.CoroutineContext

private class Application : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    fun start() {
        render(document.getElementById("root")) {
            app(this@Application)
        }
    }
}

fun main(args: Array<String>) {
    Application().start()
}

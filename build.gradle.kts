plugins {
    kotlin("js") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js", "1.3.72"))

    implementation(npm("@jetbrains/kotlin-react", "16.9.0-pre.89"))
    implementation(npm("@jetbrains/kotlin-react-dom", "16.9.0-pre.89"))
    implementation(npm("@jetbrains/kotlin-extensions", "1.0.1-pre.89"))
    implementation(npm("kotlinx-coroutines-core", "1.3.5"))
    implementation(npm("@jetbrains/kotlin-css", "1.0.0-pre.89"))
    implementation(npm("@jetbrains/kotlin-css-js", "1.0.0-pre.89"))
    implementation(npm("@jetbrains/kotlin-styled", "1.0.0-pre.89"))
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))
    implementation(npm("css-in-js-utils", "^3.0.2"))
    implementation(npm("inline-style-prefixer", "5.1.0"))
    implementation(npm("styled-components", "4.3.2"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:0.20.0")
}

kotlin.target.browser { }

ktlint {
    verbose.set(true)
    outputToConsole.set(true)

    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
    }
}

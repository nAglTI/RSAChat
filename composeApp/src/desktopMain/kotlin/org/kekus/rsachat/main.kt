package org.kekus.rsachat

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.kekus.rsachat.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "RSAChat",
    ) {
        App()
    }
}
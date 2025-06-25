package org.kekus.rsachat

import androidx.compose.ui.window.ComposeUIViewController
import org.kekus.rsachat.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}

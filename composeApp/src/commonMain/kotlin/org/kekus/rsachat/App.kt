package org.kekus.rsachat

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kekus.rsachat.chats.ChatListScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ChatListScreen()
    }
}

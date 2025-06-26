package org.kekus.rsachat.chats

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** ViewModel for the chat list screen. */
class ChatListViewModel : ViewModel() {

    // TODO: replace to states with chats list or empty banner
    private val _chats = MutableStateFlow(sampleChats())
    val chats: StateFlow<List<ChatUI>> = _chats.asStateFlow()

    private fun sampleChats(): List<ChatUI> = listOf(
        ChatUI(1, "Alice", "Hey, how's it going?"),
        ChatUI(2, "Bob", "Let's meet tomorrow."),
        ChatUI(3, "Charlie", "See you soon!")
    )
}

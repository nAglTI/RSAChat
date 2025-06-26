package org.kekus.rsachat.chats

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** ViewModel managing messages within a chat. */
class ChatViewModel(private val chatUI: ChatUI) : ViewModel() {

    private val _messages = MutableStateFlow(sampleMessages())
    val messages: StateFlow<List<ChatMessageUI>> = _messages.asStateFlow()

    fun sendMessage(text: String) {
        val list = _messages.value.toMutableList()
        val id = (list.maxOfOrNull { it.id } ?: 0L) + 1
        list += ChatMessageUI(id, 0, "Me", text, isMine = true)
        _messages.value = list
    }

    fun deleteMessage(id: Long) {
        _messages.value = _messages.value.filterNot { it.id == id }
    }

    private fun sampleMessages(): List<ChatMessageUI> = listOf(
        ChatMessageUI(1, 1, chatUI.title, chatUI.lastMessage, isMine = false)
    )
}

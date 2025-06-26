package org.kekus.rsachat.chats

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** ViewModel managing messages within a chat. */
class ChatViewModel(private val chat: Chat) : ViewModel() {

    private val _messages = MutableStateFlow(sampleMessages())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    fun sendMessage(text: String) {
        val list = _messages.value.toMutableList()
        val id = (list.maxOfOrNull { it.id } ?: 0L) + 1
        list += ChatMessage(id, 0, "Me", text, isMine = true)
        _messages.value = list
    }

    fun deleteMessage(id: Long) {
        _messages.value = _messages.value.filterNot { it.id == id }
    }

    private fun sampleMessages(): List<ChatMessage> = listOf(
        ChatMessage(1, 1, chat.title, chat.lastMessage, isMine = false)
    )
}

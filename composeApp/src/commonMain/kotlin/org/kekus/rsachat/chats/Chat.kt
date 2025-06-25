package org.kekus.rsachat.chats

/** Model representing a chat in the list. */
data class Chat(
    val id: Long,
    val title: String,
    val lastMessage: String
)

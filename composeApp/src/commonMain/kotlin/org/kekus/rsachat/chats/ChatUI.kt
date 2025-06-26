package org.kekus.rsachat.chats

/** Model representing a chat in the list. */
data class ChatUI(
    val id: Long,
    val title: String,
    val lastMessage: String
)

/** Simple chat message model. */
data class ChatMessageUI(
    val id: Long,
    val authorId: Long,
    val authorName: String,
    val text: String,
    val isMine: Boolean
)

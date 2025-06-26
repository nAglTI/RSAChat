package org.kekus.rsachat

/** Screens available in the application. */

sealed class Screen {
    object Password : Screen()

    object ChatList : Screen()

    data class Chat(val chat: chats.Chat) : Screen()

    object Settings : Screen()

    object ChangePassword : Screen()
}

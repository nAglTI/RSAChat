package org.kekus.rsachat

import org.kekus.rsachat.chats.ChatUI

/** Screens available in the application. */

sealed class Screen {
    object Password : Screen()

    object ChatList : Screen()

    data class Chat(val chatUI: ChatUI) : Screen()

    object Settings : Screen()

    object ChangePassword : Screen()
}

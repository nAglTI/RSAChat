package org.kekus.rsachat

/** Screens available in the application. */
sealed class Screen {
    object Password : Screen()
    object ChatList : Screen()
    object Settings : Screen()
    object ChangePassword : Screen()
}

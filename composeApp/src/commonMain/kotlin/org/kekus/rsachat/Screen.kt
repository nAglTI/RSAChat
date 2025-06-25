package org.kekus.rsachat

/** Screens available in the application. */
@Parcelize
sealed class Screen : Parcelable {
    @Parcelize
    object Password : Screen()

    @Parcelize
    object ChatList : Screen()

    @Parcelize
    object Settings : Screen()

    @Parcelize
    object ChangePassword : Screen()
}

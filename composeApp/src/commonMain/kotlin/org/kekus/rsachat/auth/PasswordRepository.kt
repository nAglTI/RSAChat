package org.kekus.rsachat.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO: change to set|get password interactor + PasswordRepository class
object PasswordRepository {
    private val _password = MutableStateFlow<String?>(null)

    /** Flow of the currently set password. */
    val password: StateFlow<String?> = _password.asStateFlow()

    /** Set a new password. */
    fun setPassword(value: String) {
        _password.value = value
    }
}

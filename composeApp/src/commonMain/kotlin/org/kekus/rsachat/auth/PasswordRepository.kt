package org.kekus.rsachat.auth

import kotlinx.coroutines.flow.StateFlow

/** Repository providing access to the current password. */
interface PasswordRepository {
    /** Flow of the currently set password. */
    val password: StateFlow<String?>

    /** Set a new password. */
    fun setPassword(value: String)
}

/** Simple in-memory implementation of [PasswordRepository]. */
class InMemoryPasswordRepository : PasswordRepository {
    private val _password = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)
    override val password: StateFlow<String?> = _password.asStateFlow()

    override fun setPassword(value: String) {
        _password.value = value
    }
}

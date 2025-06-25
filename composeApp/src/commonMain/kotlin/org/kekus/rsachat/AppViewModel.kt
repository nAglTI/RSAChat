package org.kekus.rsachat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kekus.rsachat.auth.PasswordRepository

/** ViewModel controlling navigation and locking logic. */
class AppViewModel : ViewModel() {
    private val _screen = MutableStateFlow<Screen>(Screen.Password)
    val screen: StateFlow<Screen> = _screen.asStateFlow()

    private var lockJob: Job? = null

    fun tryUnlock(code: String) {
        val saved = PasswordRepository.password.value
        if (saved == null || saved == code) {
            unlock()
        }
    }

    fun unlock() {
        _screen.value = Screen.ChatList
        startLockTimer()
    }

    fun lock() {
        lockJob?.cancel()
        _screen.value = Screen.Password
    }

    fun openSettings() {
        _screen.value = Screen.Settings
    }

    fun openChangePassword() {
        _screen.value = Screen.ChangePassword
    }

    fun backFromSettings() {
        _screen.value = Screen.ChatList
    }

    fun backFromChangePassword() {
        _screen.value = Screen.Settings
    }

    fun setPassword(newPassword: String) {
        PasswordRepository.setPassword(newPassword)
        _screen.value = Screen.ChatList
        startLockTimer()
    }

    private fun startLockTimer() {
        lockJob?.cancel()
        lockJob = viewModelScope.launch {
            delay(LOCK_TIMEOUT)
            lock()
        }
    }

    companion object {
        private const val LOCK_TIMEOUT = 30_000L
    }
}

/** Holder to access [AppViewModel] from platform-specific code. */
object AppViewModelHolder {
    val viewModel = AppViewModel()
}

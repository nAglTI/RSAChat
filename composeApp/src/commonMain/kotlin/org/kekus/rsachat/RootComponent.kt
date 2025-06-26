package org.kekus.rsachat

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kekus.rsachat.auth.PasswordRepository
import org.kekus.rsachat.chats.ChatUI

/** Root component managing navigation using Decompose. */
class RootComponent(
    componentContext: ComponentContext,
    private val passwordRepository: PasswordRepository,
    private val scope: CoroutineScope,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Screen>()

    /** Stack of application screens. */
    val childStack: Value<ChildStack<Screen, Screen>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Screen.Password,
        handleBackButton = true
    ) { config, _ -> config }

    private var lockJob: Job? = null

    fun tryUnlock(code: String) {
        val saved = passwordRepository.password.value
        if (saved == null || saved == code) {
            unlock()
        }
    }

    fun unlock() {
        navigation.pop { popped ->
            if (!popped) {
                navigation.replaceCurrent(Screen.ChatList)
            }
            startLockTimer()
        }
    }

    fun lock() {
        lockJob?.cancel()
        navigation.bringToFront(Screen.Password)
    }

    fun openSettings() {
        navigation.bringToFront(Screen.Settings)
    }

    fun openChat(chatUI: ChatUI) {
        navigation.bringToFront(Screen.Chat(chatUI))
    }

    fun openChangePassword() {
        navigation.bringToFront(Screen.ChangePassword)
    }

    fun back() {
        navigation.pop()
    }

    fun setPassword(newPassword: String) {
        passwordRepository.setPassword(newPassword)
        navigation.bringToFront(Screen.ChatList)
        startLockTimer()
    }

    private fun startLockTimer() {
        lockJob?.cancel()
        lockJob = scope.launch {
            delay(LOCK_TIMEOUT)
            lock()
        }
    }

    companion object {
        private const val LOCK_TIMEOUT = 30_000L
    }
}

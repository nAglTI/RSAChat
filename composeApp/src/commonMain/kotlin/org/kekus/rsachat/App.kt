package org.kekus.rsachat

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kekus.rsachat.chats.ChatListScreen
import org.kekus.rsachat.auth.PasswordScreen
import org.kekus.rsachat.settings.ChangePasswordScreen
import org.kekus.rsachat.settings.SettingsScreen

@Composable
@Preview
fun App() {
    val component = remember { RootComponentHolder.component }
    val stack by component.childStack.subscribeAsState()
    val screen = stack.active.instance

    // TODO: remove callbacks (onBack, etc.), use ViewModels
    MaterialTheme {
        when (screen) {
            Screen.Password -> PasswordScreen(onSubmit = component::tryUnlock)
            Screen.ChatList -> ChatListScreen(onOpenSettings = component::openSettings)
            Screen.Settings -> {
                SettingsScreen(
                    onBack = component::back,
                    onChangePassword = component::openChangePassword
                )
            }
            Screen.ChangePassword -> {
                ChangePasswordScreen(
                    onBack = component::back,
                    onSave = component::setPassword
                )
            }
        }
    }
}

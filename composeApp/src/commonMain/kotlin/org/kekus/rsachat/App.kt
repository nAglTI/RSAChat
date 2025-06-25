package org.kekus.rsachat

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kekus.rsachat.chats.ChatListScreen
import org.kekus.rsachat.auth.PasswordScreen
import org.kekus.rsachat.settings.ChangePasswordScreen
import org.kekus.rsachat.settings.SettingsScreen

@Composable
@Preview
fun App() {
    val viewModel = remember { AppViewModelHolder.viewModel }
    val screen by viewModel.screen.collectAsState()

    MaterialTheme {
        when (screen) {
            Screen.Password -> PasswordScreen(onSubmit = viewModel::tryUnlock)
            Screen.ChatList -> ChatListScreen(onOpenSettings = viewModel::openSettings)
            Screen.Settings -> SettingsScreen(
                onBack = viewModel::backFromSettings,
                onChangePassword = viewModel::openChangePassword
            )
            Screen.ChangePassword -> ChangePasswordScreen(
                onBack = viewModel::backFromChangePassword,
                onSave = viewModel::setPassword
            )
        }
    }
}

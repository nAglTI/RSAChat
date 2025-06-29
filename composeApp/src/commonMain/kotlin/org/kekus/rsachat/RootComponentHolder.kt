package org.kekus.rsachat

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.MainScope
import org.kekus.rsachat.auth.PasswordRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/** Provides [RootComponent] instance using Koin. */
object RootComponentHolder : KoinComponent {
    private val repository: PasswordRepository by inject()
    val backDispatcher = BackDispatcher()

    val component: RootComponent by lazy {
        RootComponent(
            DefaultComponentContext(
                lifecycle = LifecycleRegistry(),
                backHandler = backDispatcher,
            ),
            repository,
            MainScope()
        )
    }
}

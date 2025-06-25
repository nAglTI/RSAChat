package org.kekus.rsachat.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.kekus.rsachat.auth.InMemoryPasswordRepository
import org.kekus.rsachat.auth.PasswordRepository

private val appModule = module {
    single<PasswordRepository> { InMemoryPasswordRepository() }
}

fun initKoin() = startKoin {
    modules(appModule)
}

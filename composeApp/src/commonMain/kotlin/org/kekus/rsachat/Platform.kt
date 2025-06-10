package org.kekus.rsachat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
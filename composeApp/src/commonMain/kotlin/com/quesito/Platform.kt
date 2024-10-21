package com.quesito

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
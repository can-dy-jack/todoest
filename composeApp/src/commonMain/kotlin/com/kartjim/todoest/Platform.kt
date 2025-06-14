package com.kartjim.todoest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
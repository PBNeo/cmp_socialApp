package com.neosoft.socialapp

expect class Platform {
    val name: String
}

expect fun getPlatform(): Platform

package com.neosoft.coremodules.navigation


import androidx.compose.runtime.staticCompositionLocalOf

val LocalRouter = staticCompositionLocalOf<AppRouter> {
    error("AppRouter not provided")
}

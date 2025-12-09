package com.neosoft.socialapp

import androidx.compose.ui.window.ComposeUIViewController
import com.neosoft.socialapp.app.App
import com.neosoft.socialapp.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App() }
package com.neosoft.socialapp.splash.presentation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import socialapp.composeapp.generated.resources.Res
import socialapp.composeapp.generated.resources.splash1
import socialapp.composeapp.generated.resources.splash1Message
import socialapp.composeapp.generated.resources.splash1Title
import socialapp.composeapp.generated.resources.splash2
import socialapp.composeapp.generated.resources.splash2Message
import socialapp.composeapp.generated.resources.splash2Title
import socialapp.composeapp.generated.resources.splash3
import socialapp.composeapp.generated.resources.splash3Message
import socialapp.composeapp.generated.resources.splash3Title

data class SplashState(
    val isLoading: Boolean = true,
    val selectedStep:Int =0,
    val splashImages: List<DrawableResource> = listOf(
        Res.drawable.splash1,
        Res.drawable.splash2,
        Res.drawable.splash3
    ),
    val splashTextTitle: List<StringResource> = listOf(
        Res.string.splash1Title,
        Res.string.splash2Title,
        Res.string.splash3Title,
    ),
    val splashTextMessage: List<StringResource> = listOf(
        Res.string.splash1Message,
        Res.string.splash2Message,
        Res.string.splash3Message,
    )

)

package com.neosoft.coremodules.navigation


import com.neosoft.designsystem.components.dashboard.CommentModel
import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

sealed interface Route {
    val path: String // every route must have a path

    @Serializable
    data object Splash : Route {
        override val path: String = "splash"
    }

    @Serializable
    data object Login : Route {
        override val path: String = "auth/login"
    }

    @Serializable
    data object Register : Route {
        override val path: String = "auth/register"
    }

    @Serializable
    data object ForgotPassword : Route {
        override val path: String = "auth/forgotPassword"
    }


    @Serializable
    data object  ChangePassword : Route {
        override val path: String = "auth/changePassword"
    }

    @Serializable
    data object  WelcomeScreen : Route {
        override val path: String = "auth/welcomeScreen"
    }

    @Serializable
    data object  AccountSetup : Route {
        override val path: String = "auth/accountSetup"
    }


    @Serializable
    data class VerifyOtp(val mobile: String) : Route {
        override val path: String = "auth/verifyOtp/${mobile}"

        companion object : Route.WithArgs<String> {
            override val routeName: String = "auth/verifyOtp"
            override val serializer: KSerializer<String> = String.serializer()
        }
    }

    @Serializable
    data object ProfileSetup : Route {
        override val path: String = "auth/profileSetup/"

    }



    @Serializable
    data object Home : Route {
        override val path: String = "home/"

    }

    @Serializable
    data object Root : Route {
        override val path: String = "root/"

    }
    @Serializable
    data class Status(val postId: String) : Route {
        override val path: String = "home/status/${postId}"

    }

    @Serializable
    data class Comments(val comments: String) : Route {
        override val path: String = "home/comments/${comments}"

    }


    @Serializable
    data class CommentsArgs(val comments: List<CommentModel>)


    object CommentsRoute : Route.WithArgs<CommentsArgs> {
        override val routeName: String = "home/comments"
        override val serializer = CommentsArgs.serializer()

    }



    interface WithArgs<T> : Route {
        val routeName: String
        val serializer: KSerializer<T>

        override val path: String
            get() = "$routeName/{args}"
    }

    @Serializable
    data class Profile(val id: String) : WithArgs<Profile> {
        override val path: String = "/Profile"
        override val routeName: String = "profile"
        override val serializer = Profile.serializer()
    }

    @Serializable
    data object  Notification : Route {
        override val path: String = "home/notification"
    }


}
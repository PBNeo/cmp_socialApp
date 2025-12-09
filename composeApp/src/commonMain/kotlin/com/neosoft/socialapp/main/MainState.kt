package com.neosoft.socialapp.com.neosoft.socialapp.main

sealed class MainTab(val index: Int) {
    data object Home : MainTab(0)
    data object Stats : MainTab(1)
    data object Create : MainTab(2)
    data object Messages : MainTab(3)
    data object Profile : MainTab(4)

    companion object {
        fun fromIndex(i: Int) = when (i) {
            0 -> Home
            1 -> Stats
            2 -> Create
            3 -> Messages
            else -> Profile
        }
    }
}

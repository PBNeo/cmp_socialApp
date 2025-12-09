package com.neosoft.socialapp.com.neosoft.socialapp.main

import ChatListScreenRoot
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.neosoft.auth.presentation.profileSetup.ProfileSetupScreenRoot
import com.neosoft.createPost.presentation.CreatePostScreenRoot
import com.neosoft.designsystem.components.components.BottomNavBar
import com.neosoft.lounge.presentation.LoungeListRoot
import neosoft.home.HomeScreenRoot
import neosoft.profile.ProfileScreenRoot

@Composable
fun MainScreenRoot() {

    var selectedTab by remember { mutableStateOf<MainTab>(MainTab.Home) }

    Column(modifier = Modifier.fillMaxSize()) {

        // CONTENT (provided by feature modules)
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                MainTab.Home -> HomeScreenRoot()       // from feature-home module
                MainTab.Stats -> LoungeListRoot()     // from feature-stats module
                MainTab.Create -> CreatePostScreenRoot(currentUserId = "1") // from feature-create module
                MainTab.Messages -> ChatListScreenRoot() // feature-messages
                MainTab.Profile -> ProfileScreenRoot(userId = "1") // feature-profile
            }
        }

        // ALWAYS VISIBLE BOTTOM BAR
        BottomNavBar(
            selectedIndex = selectedTab.index,
            onItemSelected = { selectedTab = MainTab.fromIndex(it) }
        )
    }
}

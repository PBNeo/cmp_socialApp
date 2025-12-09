package com.neosoft.notification.presentation

import NotificationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.designsystem.components.BaseScreen
import com.neosoft.notification.presentation.Notification.NotificationScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotificationScreenRoot(userId: String) {
    val viewModel: NotificationViewModel = koinViewModel()
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(userId) {
        viewModel.load(userId)
    }

    BaseScreen(title = "Notifications") {
        NotificationScreen(
            notifications = state.notifications,
            onBack = { router.pop() },
            onClearAll = { /* TODO: call clear use-case */ }
        )
    }
}

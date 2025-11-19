package com.neosoft.auth.presentation.profileSetup
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import neosoft.profileSetup.ProfileSetupScreenAction
import neosoft.profileSetup.ProfileSetupScreenState
import neosoft.profileSetup.ProfileSetupViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProfileSetupScreenRoot(
    viewModel: ProfileSetupViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileSetupScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ProfileSetupScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun ProfileSetupScreen(state: ProfileSetupScreenState, onAction: (ProfileSetupScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

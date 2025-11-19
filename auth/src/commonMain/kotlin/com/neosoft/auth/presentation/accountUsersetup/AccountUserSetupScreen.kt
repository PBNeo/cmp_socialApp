package com.neosoft.auth.presentation.accountUsersetup
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import neosoft.accountUserSetup.AccountUserSetupScreenAction
import neosoft.accountUserSetup.AccountUserSetupScreenState
import neosoft.accountUserSetup.AccountUserSetupViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountUserSetupScreenRoot(
    viewModel: AccountUserSetupViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    AccountUserSetupScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is AccountUserSetupScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun AccountUserSetupScreen(state: AccountUserSetupScreenState, onAction: (AccountUserSetupScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

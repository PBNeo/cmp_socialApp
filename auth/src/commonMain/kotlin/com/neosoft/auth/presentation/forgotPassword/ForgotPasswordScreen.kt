package com.neosoft.auth.presentation.forgotPassword
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import neosoft.forgotPassword.ForgotPasswordScreenAction
import neosoft.forgotPassword.ForgotPasswordScreenState
import neosoft.forgotPassword.ForgotPasswordViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ForgotPasswordScreenRoot(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgotPasswordScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ForgotPasswordScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun ForgotPasswordScreen(state: ForgotPasswordScreenState, onAction: (ForgotPasswordScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

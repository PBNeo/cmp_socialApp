package com.neosoft.auth.presentation.login

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import neosoft.login.LoginScreenAction
import neosoft.login.LoginScreenState
import neosoft.login.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is LoginScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun LoginScreen(state: LoginScreenState, onAction: (LoginScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

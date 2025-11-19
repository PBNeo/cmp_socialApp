package neosoft.changePassword

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChangePasswordScreenRoot(
    viewModel: ChangePasswordViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChangePasswordScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ChangePasswordScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun ChangePasswordScreen(state: ChangePasswordScreenState, onAction: (ChangePasswordScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

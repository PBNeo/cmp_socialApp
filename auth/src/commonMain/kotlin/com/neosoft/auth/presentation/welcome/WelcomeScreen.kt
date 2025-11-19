package neosoft.welcome

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WelcomeScreenRoot(
    viewModel: WelcomeViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    WelcomeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is WelcomeScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun WelcomeScreen(state: WelcomeScreenState, onAction: (WelcomeScreenAction) -> Unit) {
    TODO("Not yet implemented")
}

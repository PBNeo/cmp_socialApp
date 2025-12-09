package neosoft.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.profile.presentation.Profile.presentation.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreenRoot(userId: String) {
    val viewModel: ProfileViewModel = koinViewModel()
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    // load data once when this root enters composition
    LaunchedEffect(userId) {
        viewModel.load(userId)
    }

    ProfileScreen(
        state = state,
        onAction = { action ->
            when (action) {
                ProfileScreenAction.OnBackPressed -> router.pop()
                ProfileScreenAction.OnNext -> { /* navigate */
                }

                is ProfileScreenAction.ToggleFollow -> {
                    // You may call a use-case in ViewModel to toggle follow; for now navigate or log
                }

                is ProfileScreenAction.OpenMessage -> {
                    // navigate to chat screen: router.push(...)
                }

                is ProfileScreenAction.OpenEditProfile -> {
                  //  router.go("editProfile/${action.userId}") // example
                }
            }
        }
    )
}

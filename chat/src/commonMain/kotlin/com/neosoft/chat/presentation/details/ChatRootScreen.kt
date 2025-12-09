package neosoft.chat.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.chat.presentation.ChatScreen
import com.neosoft.chat.presentation.details.ChatAction
import com.neosoft.chat.presentation.details.ChatViewModel
import com.neosoft.coremodules.navigation.LocalRouter
import org.koin.compose.viewmodel.koinViewModel


/**
 * chatId: the id of the conversation (passed in route)
 * meUserId: current logged-in user id (optional if viewmodel already knows it)
 */
@Composable
fun ChatScreenRoot(chatId: String, meUserId: String) {
    // provide chatId to Koin DI if you use parameters; otherwise use koinViewModel()
    val vm: ChatViewModel = koinViewModel() // or koinViewModel { parametersOf(chatId) }
    val state by vm.state.collectAsStateWithLifecycle()

    val router = LocalRouter.current

    // connect WS and load history when entering
    LaunchedEffect(chatId) {
        // connect websocket (pass token if necessary)
        vm.connect(null)
        // load message history for this chat
        vm.loadHistory(chatId)
    }

    // disconnect when leaving composition
    DisposableEffect(Unit) {
        onDispose {
            vm.disconnect()
        }
    }

    // Compose UI (presentational ChatScreen uses state.messages, etc.)
    ChatScreen(
        messages = state.messages,
        meId = meUserId,
        onSend = { text ->
            // forward action to viewmodel (it will send via WS + optimistic add)
            vm.onAction(ChatAction.SendText(text), chatId)
        },
        onBack = {
            router.pop()
        }
    )
}

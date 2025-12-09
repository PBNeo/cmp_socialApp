import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.chat.presentation.list.ChatListScreen
import com.neosoft.chat.presentation.list.ChatListAction
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.chat.presentation.list.ChatListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatListScreenRoot() {

    val vm: ChatListViewModel = koinViewModel()
    val router = LocalRouter.current
    val state by vm.state.collectAsStateWithLifecycle()

    ChatListScreen(
        chats = state.chats,
        onOpenChat = { chatId ->
            val route = vm.onAction(ChatListAction.OpenChat(chatId))
//            route?.let { r ->
//                if (r == "back") router.pop()
//                else router.go(r)
//            }
        }
    )
}
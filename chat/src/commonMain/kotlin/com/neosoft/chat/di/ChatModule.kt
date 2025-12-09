package neosoft.chat.di
import com.neosoft.chat.presentation.details.ChatViewModel
import org.koin.dsl.module
import com.neosoft.chat.data.remote.ChatWebSocketService
import neosoft.chat.data.repository.ChatRepositoryImpl
import com.neosoft.chat.domain.repository.ChatRepository
import com.neosoft.chat.domain.usecase.ConnectWebSocketUseCase
import com.neosoft.chat.domain.usecase.DisconnectWebSocketUseCase
import com.neosoft.chat.domain.usecase.GetChatsUseCase
import com.neosoft.chat.domain.usecase.GetMessagesUseCase
import com.neosoft.chat.domain.usecase.ObserveMessagesUseCase
import com.neosoft.chat.domain.usecase.SendMessageUseCase
import com.neosoft.chat.presentation.list.ChatListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import org.koin.core.module.dsl.viewModel

val chatModule = module {
    single { ChatWebSocketService(get(), wsUrl = "wss://yourserver.example/ws") }

    single<ChatRepository> { ChatRepositoryImpl(get()) }
    factory { ConnectWebSocketUseCase(get()) }
    factory { DisconnectWebSocketUseCase(get()) }
    factory { SendMessageUseCase(get()) }
    factory { ObserveMessagesUseCase(get()) }
    factory { GetChatsUseCase(get()) }
    factory { GetMessagesUseCase(get()) }
    viewModel { ChatListViewModel(get()) }
    // If ChatViewModel needs a runtime param (meUserId), register using parameters:
    viewModel { (meUserId: String) ->ChatViewModel(
            get(), get(), get(), get(), get(),
            meUserId = meUserId
        )
    }

}



import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.designsystem.components.CommentInput
import com.neosoft.designsystem.components.dashboard.StatusHeader
import com.neosoft.designsystem.components.dashboard.StatusMedia
import neosoft.status.StatusScreenState
import neosoft.status.StatusViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun StatusScreenRoot(
    postId:String?
) {
    val viewModel: StatusViewModel = koinViewModel(parameters = { parametersOf(postId) })
    val state by viewModel.state.collectAsStateWithLifecycle()
    val router = LocalRouter.current

    StatusScreen(
        state = state,
        onClose = { router.pop() },
        onSendComment = { comment -> viewModel.sendComment(comment) }
    )
}

@Composable
fun StatusScreen(
    state: StatusScreenState,
    onClose: () -> Unit,
    onSendComment: (String) -> Unit
) {
    // Full-screen image/video with overlay comment input
    Box(modifier = Modifier.fillMaxSize()) {
        // background image/content placeholder
        StatusMedia(
            mediaUrl = state.mediaUrl,
            isVideo = state.isVideo,
            onPlay = { /* play video */ },
        )


        // top-left header
        StatusHeader(
            avatarUrl = state.avatarUrl,
            author = state.author,
            timeAgo = state.timeAgo,
            viewersCount = state.viewersCount,
            isLive = state.isLive,
            onAvatarClick = { /* open profile */ },
            onClose = { onClose() }
        )

        // bottom comment input
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp)
        ) {
            CommentInput(onSend = onSendComment)
        }
    }
}
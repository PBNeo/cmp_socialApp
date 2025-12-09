package neosoft.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.designsystem.components.components.BottomNavBar
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.designsystem.components.BaseScreen
import com.neosoft.designsystem.components.PostCard
import com.neosoft.designsystem.components.StoryRow
import com.neosoft.designsystem.utils.AppColors.primary
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel()
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is HomeScreenAction.OnBackPressed -> router.pop()
                is HomeScreenAction.OpenStatus -> router.go(Route.Status(postId = "1"))
                is HomeScreenAction.onViewMoreComments -> {
                    val args = Route.CommentsArgs(comments = action.comments)
                    router. go(Route.CommentsRoute, args)
                }
                is HomeScreenAction.OnNotificationClicked ->  router.go(Route.Notification)
                is HomeScreenAction.OnCreatePost -> { /* open create */ }
            }
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit
) {
    var query by remember { mutableStateOf("") }

    BaseScreen(showBackButton = false) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 52.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        onAction(HomeScreenAction.OnNotificationClicked)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                ) {
                    Icon(

                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification",
                        tint = primary
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Stories / status row
            StoryRow(items = state.stories, onStoryClick = { onAction(HomeScreenAction.OpenStatus(it.id)) })

            Spacer(Modifier.height(12.dp))

            // Posts feed
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(state.posts) { post ->
                    PostCard(
                        post = post,
                        onOpenStatus = { onAction(HomeScreenAction.OpenStatus(post.id)) },
                        onViewMoreComments = {c->onAction(HomeScreenAction.onViewMoreComments(c))}
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}


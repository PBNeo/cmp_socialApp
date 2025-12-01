package neosoft.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.designsystem.components.Avatar
import com.neosoft.designsystem.components.BaseScreen
import com.neosoft.designsystem.components.CommentInput
import com.neosoft.designsystem.components.PostCard
import com.neosoft.designsystem.components.dashboard.CommentModel
import com.neosoft.designsystem.components.dashboard.StatusHeader
import com.neosoft.designsystem.components.dashboard.StatusMedia
import neosoft.home.HomeScreenAction
import neosoft.status.StatusScreenState
import neosoft.status.StatusViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CommentScreenRoot(
 comments:   List<CommentModel>
) {     val viewModel: CommentViewModel = koinViewModel(parameters = { parametersOf(comments) })
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    CommentScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is CommentScreenAction.OnNext -> {
                    // TODO: navigate
                }
                is CommentScreenAction.OnBackPressed -> {
                    router.pop()
                }
                else -> Unit
            }
        }
    )
}


@Composable
fun CommentScreen(
    state: CommentScreenState,
    onAction: (CommentScreenAction)->Unit
) {
    BaseScreen(
        title = "Comments",
        showBackButton = true,
        onBackPressed = {
            onAction(CommentScreenAction.OnBackPressed)
        }

    ) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.comments) { comment ->
                Row{
                    Avatar(url = comment.avatarUrl, sizeDp = 48,)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {

                        Text(comment.author)
                        Text(comment.author)
                    }

                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

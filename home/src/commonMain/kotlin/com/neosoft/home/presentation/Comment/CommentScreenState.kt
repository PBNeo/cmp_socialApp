package neosoft.comment

import com.neosoft.designsystem.components.dashboard.CommentModel

data class CommentScreenState (
    val isLoading: Boolean = true,
    val comments: List<CommentModel> = listOf<CommentModel>()
)

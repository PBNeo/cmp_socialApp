package neosoft.home

import com.neosoft.designsystem.components.dashboard.CommentModel

interface HomeScreenAction {
    object OnBackPressed : HomeScreenAction
    object OnNext : HomeScreenAction
    object OnCreatePost : HomeScreenAction

    data class   OpenStatus(
        val id :String,
    ): HomeScreenAction


    data class   onViewMoreComments(
        val comments :List<CommentModel>,
    ): HomeScreenAction
}

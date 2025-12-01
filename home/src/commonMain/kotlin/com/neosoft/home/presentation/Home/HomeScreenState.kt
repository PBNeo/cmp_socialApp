package neosoft.home

import com.neosoft.designsystem.components.PostModel

data class HomeScreenState (
    val isLoading: Boolean = true,
    val posts :List<PostModel> = listOf(),

    val stories :List<PostModel> = listOf()
)


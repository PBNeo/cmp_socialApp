package neosoft.profile

sealed interface ProfileScreenAction {
    object OnBackPressed : ProfileScreenAction
    object OnNext : ProfileScreenAction
    data class ToggleFollow(val userId: String) : ProfileScreenAction
    data class OpenMessage(val userId: String) : ProfileScreenAction
    data class OpenEditProfile(val userId: String) : ProfileScreenAction
}
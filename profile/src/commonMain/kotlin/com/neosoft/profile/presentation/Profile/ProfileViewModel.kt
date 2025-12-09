package neosoft.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.profile.domain.usecase.GetFullProfileUseCase
import com.neosoft.profile.domain.entity.FullProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getFullProfileUseCase: GetFullProfileUseCase
) : ViewModel() {

    // Expose presentation state directly to UI
    private val _state = MutableStateFlow(ProfileScreenState(isLoading = true))
    val state: StateFlow<ProfileScreenState> = _state

    // Call load from the screen root (or automatically for current user)
    fun load(userId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val profile = getFullProfileUseCase(userId)
                _state.value = mapDomainToUi(profile)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message ?: "Unknown error")
            }
        }
    }

    // Simple mapper: domain -> presentation
    private fun mapDomainToUi(profile: FullProfile): ProfileScreenState {
        return ProfileScreenState(
            isLoading = false,
            userId = profile.userId,
            name = profile.name,
            location = profile.location,
            bio = profile.bio,
            avatarUrl = profile.avatarUrl,
            postsCount = profile.postsCount,
            followingCount = profile.followingCount,
            followersCount = profile.followersCount,
            isFollowing = profile.isFollowing,
            followersPreview = profile.followersPreview.map { FollowerItem(it.userId, it.name, it.avatarUrl) },
            posts = profile.posts.map { PostItem(it.postId, it.imageUrl) },
            error = null
        )
    }
}

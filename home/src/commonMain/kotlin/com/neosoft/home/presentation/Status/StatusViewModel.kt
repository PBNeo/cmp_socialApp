package neosoft.status

import androidx.lifecycle.ViewModel
import com.neosoft.designsystem.components.PostModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.Boolean

class StatusViewModel(
    postId : String
) : ViewModel(

) {
    private val _state = MutableStateFlow(StatusScreenState())
    val state = _state


    fun sendComment(comment: String) {}

    init {
        // load dummy data
        _state.value = _state.value.copy(
      isLoading = false,
     mediaUrl = "https://picsum.photos/id/1/200/300",
     isVideo= false,
     avatarUrl = "https://avatar.iran.liara.run/public",
     author = "PB",
     timeAgo = "123",
     viewersCount = 5,
     isLive = false
        )

    }
}

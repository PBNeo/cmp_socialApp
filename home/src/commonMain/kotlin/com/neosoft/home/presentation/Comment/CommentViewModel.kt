package neosoft.comment

import androidx.lifecycle.ViewModel
import com.neosoft.designsystem.components.dashboard.CommentModel
import kotlinx.coroutines.flow.MutableStateFlow

class CommentViewModel(
    comments : List<CommentModel>
) : ViewModel() {
    private val _state = MutableStateFlow(CommentScreenState())
    val state = _state

    init {
        // load dummy data
        _state.value = _state.value.copy(
            comments = comments,

        )
    }
}

package neosoft.home

import androidx.lifecycle.ViewModel
import com.neosoft.designsystem.components.PostModel
import com.neosoft.designsystem.components.dashboard.CommentModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state



    init {
        // load dummy data
        _state.value = _state.value.copy(
            stories = sampleStories(),
            posts = samplePosts()
        )
    }

    private fun sampleStories() = listOf(
        PostModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
        PostModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),

                PostModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
    PostModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),
        PostModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
        PostModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),
        PostModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
        PostModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2)


    )
    private fun samplePosts() = listOf(
        PostModel("p1", "Oyin Dolapo", "https://avatar.iran.liara.run/public", "1hr ago", "Delicious pizza!", "https://picsum.photos/600/400", 247, 57, commentsList = listOf(
            CommentModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
            CommentModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),


            )),
        PostModel("p2", "Abdul Quayyum", "https://avatar.iran.liara.run/public", "1hr ago", "Another post", null, 45, 6),

        PostModel("p1", "Oyin Dolapo", "https://avatar.iran.liara.run/public", "1hr ago", "Delicious pizza!", "https://picsum.photos/600/400", 247, 57, commentsList = listOf(
            CommentModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
            CommentModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),


            )),
        PostModel("p1", "Oyin Dolapo", "https://avatar.iran.liara.run/public", "1hr ago", "Delicious pizza!", "https://picsum.photos/600/400", 247, 57, commentsList = listOf(
            CommentModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
            CommentModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),


            )),
        PostModel("p1", "Oyin Dolapo", "https://avatar.iran.liara.run/public", "1hr ago", "Delicious pizza!", "https://picsum.photos/600/400", 247, 57, commentsList = listOf(
            CommentModel("s1", "Abdul", "https://avatar.iran.liara.run/public","1hr", null, "https://plus.unsplash.com/premium_photo-1666900440561-94dcb6865554?q=80&w=3027&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 5, 0),
            CommentModel("s2", "Chris", "https://avatar.iran.liara.run/public", "2hr", null, "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3164&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 12, 2),


            )),
    )
}

package com.neosoft.createPost.presentation
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.neosoft.coremodules.navigation.LocalRouter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreatePostScreenRoot(currentUserId: String) {
    val viewModel: CreatePostViewModel = koinViewModel()
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    // local presentation state
    val caption = remember { mutableStateOf("") }
    val hashtags = remember { mutableStateOf("") }
    val images = remember { mutableStateOf(listOf<String>()) }

    CreatePostScreen(
        state = CreatePostScreenState(
            isLoading = state.isLoading,
            images = images.value,
            caption = caption.value,
            hashtags = hashtags.value,
            success = state.success,
            error = state.error
        ),
        onPickImages = {
            // TODO: open image picker and update images.value
        },
        onCaptionChange = { caption.value = it },
        onHashtagsChange = { hashtags.value = it },
        onUpload = {
            viewModel.create(currentUserId, caption.value, images.value, hashtags.value.split(" ").filter { it.isNotBlank() })
        }
    )
}

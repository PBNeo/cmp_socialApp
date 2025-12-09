package com.neosoft.lounge.presentation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.neosoft.coremodules.navigation.LocalRouter
import kotlinx.coroutines.launch
import neosoft.lounge.presentation.list.LoungeListViewModel
import com.neosoft.lounge.presentation.ui.LoungeListScreen
import com.neosoft.lounge.presentation.ui.CreateLoungeSheet
import com.neosoft.lounge.presentation.ui.LoungeRoomSheet
import neosoft.lounge.presentation.details.LoungeViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoungeListRoot() {
    val vm: LoungeListViewModel = koinViewModel()
    val state by vm.state.collectAsStateWithLifecycle()
    val router = LocalRouter.current
    val scope = rememberCoroutineScope()

    val showCreate = remember { mutableStateOf(false) }
    val showRoomFor = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        vm.load()
    }

    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
        LoungeListScreen(
            lounges = state.lounges,
            onOpenLounge = { loungeId -> showRoomFor.value = loungeId },
            onCreate = { showCreate.value = true }
        )

        if (showCreate.value) {
            CreateLoungeSheet(onCreate = { title, desc ->
                scope.launch {
                    // call create use-case via VM or repository
                    showCreate.value = false
                }
            }, onClose = { showCreate.value = false })
        }

        showRoomFor.value?.let { id ->
            val roomVm: LoungeViewModel = koinViewModel  () // resolves default VM
            LaunchedEffect(id) {
                roomVm.load(id)
            }
            LoungeRoomSheet(details = roomVm.state.value.details, onClose = { showRoomFor.value = null })
        }
    }
}

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.socialapp.splash.presentation.SplashScreenAction
import com.neosoft.socialapp.splash.presentation.SplashState
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreenRoot (
    viewModel: SplashViewModel = koinViewModel(),
    OnContinueClicked: () -> Unit,
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    SplashScreen(
        state = state,
        onAction = { action ->
        when(action) {
            is SplashScreenAction.OnContinueClicked -> OnContinueClicked()
            else -> Unit
        }
        viewModel.onAction(action)
    })

}


@Composable
fun SplashScreen(
    state: SplashState,
    onAction: (SplashScreenAction) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = state.selectedStep,
        initialPageOffsetFraction =0F,
        pageCount = { 2 }
    )
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Page ${page + 1}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (pagerState.currentPage == 1) { // last page
            Button(onClick = { onAction(SplashScreenAction.OnContinueClicked) }) {
                Text("Continue")
            }
        } else {
            Button(onClick = {
                val next = pagerState.currentPage + 1
                coroutineScope.launch {
                    pagerState.animateScrollToPage(next)
                    onAction(SplashScreenAction.OnPageSwiped(next))
                }
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

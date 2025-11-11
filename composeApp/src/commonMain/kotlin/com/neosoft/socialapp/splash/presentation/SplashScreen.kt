import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.AppSecondaryButton
import com.neosoft.designsystem.utils.AppColors
import com.neosoft.socialapp.core.presentation.primary
import com.neosoft.socialapp.splash.presentation.SplashScreenAction
import com.neosoft.socialapp.splash.presentation.SplashState
import com.neosoft.socialapp.splash.presentation.SplashViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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
    val images = state.splashImages
    val titles = state.splashTextTitle
    val messages = state.splashTextMessage

    val pagerState = rememberPagerState(
        initialPage = state.selectedStep,
        initialPageOffsetFraction =0F,
        pageCount = { 2 }
    )
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()    .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(0.5f)
        ) { page ->
            val imageName = images.getOrNull(page) ?: return@HorizontalPager
            val painter = painterResource(imageName) // Common resource loader

            Image(
                painter = painter,
                contentDescription = "Splash Image $page",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )}

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center)
        {
            val title = titles.getOrNull(state.selectedStep) ?: return
            val message = messages.getOrNull(state.selectedStep) ?: return
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(8.dp)) // small gap

            // Message - normal body
            Text(
                text = stringResource(message),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (pagerState.currentPage == 2) {
                AppPrimaryButton(text ="Continue",
                    onClick = {
                        val next = pagerState.currentPage + 1
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(next)
                            onAction(SplashScreenAction.OnPageSwiped(next))
                        }
                    }
                )
            } else {
                AppPrimaryButton(text ="Next",
                    onClick = {
                        val next = pagerState.currentPage + 1
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(next)
                            onAction(SplashScreenAction.OnPageSwiped(next))
                        }
                    }
                    )
                Spacer(modifier = Modifier.height(8.dp))
                AppSecondaryButton(
                    text = "Skip",
                    onClick = { onAction(SplashScreenAction.OnContinueClicked) },
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

}

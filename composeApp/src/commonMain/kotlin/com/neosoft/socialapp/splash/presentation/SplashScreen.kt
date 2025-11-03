import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreenRoot (
    viewModel: SplashViewModel
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    SplashScreen(viewModel)

}


@Composable
fun SplashScreen(viewModel: SplashViewModel) {


}
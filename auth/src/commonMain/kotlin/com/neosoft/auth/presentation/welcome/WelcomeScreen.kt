package neosoft.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.designsystem.components.AppPrimaryButton
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import socialapp.auth.generated.resources.Res

@Composable
fun WelcomeScreenRoot(
    viewModel: WelcomeViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    WelcomeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is WelcomeScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun WelcomeScreen(state: WelcomeScreenState, onAction: (WelcomeScreenAction) -> Unit) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Avatar + Background Circle + Decorations
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(Color(0xFFF5F5F5), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Avatar Image
//                Image(
//                    painter = painterResource(      Res.drawable.splash3), // Put your image in commonMain/resources
//                    contentDescription = null,
//                    modifier = Modifier.size(140.dp)
//                )

            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Welcome",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(40.dp))

            AppPrimaryButton(text = "Continue", onClick = {
                onAction(WelcomeScreenAction.OnNext)
            })
        }


}

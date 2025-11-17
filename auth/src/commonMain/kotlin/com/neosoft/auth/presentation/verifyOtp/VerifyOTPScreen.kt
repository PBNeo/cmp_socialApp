import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.OtpTextField
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun VerifyOtpScreenRoot (
    viewModel: VerifyOTPViewModel = koinViewModel(),
    OnContinueClicked: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val timeLeft by viewModel.timeLeft.collectAsStateWithLifecycle()
    VerifyOtpScreen(
        state = state,
        timeLeft =timeLeft,
        onAction = { action ->
            when(action) {
                is VerifyOTPAction.onNextClicked -> OnContinueClicked()
                else -> Unit
            }
            viewModel.onAction(action)
        })


}


@Composable
fun VerifyOtpScreen(
    state: VerifyOTPScreenState,
    timeLeft: Int,
    onAction: (VerifyOTPAction) -> Unit
)   {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // --- Top Bar ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { onAction(VerifyOTPAction.onBackPressed) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(16.dp))

        // --- Title ---
        Text(
            text = "OTP sent",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        // --- Subtitle ---
        Text(
            text = "Enter the OTP sent to you",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        OtpTextField(
            otpText = state.otpText,
            onOtpTextChange = {
                s,length->onAction(VerifyOTPAction.onOTPAutoSubmitted)
            }
        )
        // --- Rich Text: Already have an account? Sign in ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Didn’t receive any code? ")

            if (timeLeft > 0) {
                // NOT CLICKABLE while timer is running
                Text(
                    text = "Resend in $timeLeft",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            } else {
                // CLICKABLE WHEN TIMER ENDS
                Text(
                    text = "Resend OTP",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onAction(VerifyOTPAction.onResendOtpClicked)
                    }
                )
            }
        }


        Spacer(Modifier.height(32.dp))


        // --- Next Button ---
        AppPrimaryButton(
            text = "Next",
            onClick = { onAction(VerifyOTPAction.onNextClicked) }
        )

        Spacer(Modifier.height(24.dp))


        // --- Rich Text: Already have an account? Sign in ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ")

            Text(
                text = "Sign in",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onAction(VerifyOTPAction.onSignedUpClicked)
                }
            )
        }
    }
}


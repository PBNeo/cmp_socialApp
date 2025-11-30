package com.neosoft.auth.presentation.register
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.BaseScreen
import com.neosoft.designsystem.components.CountrySelector.CountryWheelPicker
import neosoft.forgotPassword.ForgotPasswordScreenAction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreenRoot (
    viewModel: RegisterViewModel = koinViewModel(),
    OnContinueClicked: () -> Unit,
){
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterScreen(
        state = state,
        onAction = { action ->
            print("action")
            when(action) {
                is RegisterScreenAction.onBackPressed ->{
                    router.pop()
                }
                is RegisterScreenAction.OnSignIn -> {
                    router.go(Route.Login)
                }

                else -> viewModel.onAction(action,router)
            }

        })


}


@Composable
fun RegisterScreen(
    state: RegisterScreenState,
    onAction: (RegisterScreenAction) -> Unit
)   {
    var selectedIndex by remember { mutableStateOf(0) }
    var mobileNo by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("") }
    BaseScreen(
        showBackButton = true,
        onBackPressed = { onAction(RegisterScreenAction.onBackPressed) }
    ) {
        if (state.loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                // --- Title ---
                Text(
                    text = "Phone",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                // --- Subtitle ---
                Text(
                    text = "Enter your phone",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(Modifier.height(24.dp))


                // --- Phone Number Field with flag + country code ---
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp)
                ) {

                    // Country flag passed from state
//            Text(state.selectedIndex,
//            )

                    Spacer(Modifier.width(8.dp))

                    // Country code passed from state
                    Text(
                        text = countryCode,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(Modifier.width(12.dp))

                    // Phone Input
                    TextField(
                        value = mobileNo,
                        onValueChange = { new ->
                            mobileNo = new

                        },
                        placeholder = { Text("123 456 789") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(24.dp))


                // --- Country Wheel Picker (unchanged) ---
                CountryWheelPicker(
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { i, c ->
                        onAction(RegisterScreenAction.OnCountryChange)
                        countryCode = c.callingCode
                        selectedIndex = 1;
                    },
                    visibleItemsCount = 5
                )

                Spacer(Modifier.height(32.dp))


                // --- Next Button ---
                AppPrimaryButton(
                    text = "Next",
                    onClick = { onAction(RegisterScreenAction.OnNext(mobileNo, countryCode)) }
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
                            onAction(RegisterScreenAction.OnSignIn)
                        }
                    )
                }
            }
        }
    }
}


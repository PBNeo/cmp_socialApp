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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.CountrySelector.CountryWheelPicker
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot (
    viewModel: RegisterViewModel = koinViewModel(),
    OnContinueClicked: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is RegisterScreenAction.OnNext -> OnContinueClicked()
                else -> Unit
            }
            viewModel.onAction(action)
        })


}


@Composable
fun LoginScreen(
    state: RegisterScreenState,
    onAction: (RegisterScreenAction) -> Unit
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
            IconButton(onClick = { onAction(RegisterScreenAction.onBackPressed) }) {
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
                text = state.countryCode,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.width(12.dp))

            // Phone Input
            TextField(
                value = state.mobileNo,
                onValueChange = {

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
            selectedIndex = state.selectedIndex,
            onSelectedIndexChange = { i ->
              //  onAction(LoginScreenAction.OnCountryChange(i))
            },
            visibleItemsCount = 5
        )

        Spacer(Modifier.height(32.dp))


        // --- Next Button ---
        AppPrimaryButton(
            text = "Next",
            onClick = { onAction(RegisterScreenAction.OnNext) }
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


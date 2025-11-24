package com.neosoft.auth.presentation.accountUsersetup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.AppTextField
import neosoft.accountUserSetup.AccountUserSetupScreenState
import neosoft.accountUserSetup.AccountUserSetupViewModel
import neosoft.changePassword.ChangePasswordScreenAction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountUserSetupScreenRoot(
    viewModel: AccountUserSetupViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    AccountUserSetupScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is AccountUserSetupScreenAction.OnNext -> {
                    router.go(Route.WelcomeScreen)
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun AccountUserSetupScreen(state: AccountUserSetupScreenState, onAction: (AccountUserSetupScreenAction) -> Unit) {
    var userName by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var conFirmNewPass by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Select a Username",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Help you secure your account",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = newPass,
            onValueChange = { newPass = it },
            label = "User Name"
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = newPass,
            onValueChange = { newPass = it },
            label = "New Password"
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = conFirmNewPass,
            onValueChange = { conFirmNewPass = it },
            label = "Confirm new Password"
        )

        Spacer(Modifier.height(24.dp))

        AppPrimaryButton(
            text = "Done",
            onClick = { onAction(AccountUserSetupScreenAction.OnNext) }
        )
        Spacer(Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Already have an account? ")
            Text(
                "Sign In",
                color = Color(0xFF0077B6),
                fontWeight = FontWeight.Bold
            )
        }

    }
}

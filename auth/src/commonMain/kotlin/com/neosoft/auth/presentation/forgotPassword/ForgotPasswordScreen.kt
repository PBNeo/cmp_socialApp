package com.neosoft.auth.presentation.forgotPassword
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
import neosoft.forgotPassword.ForgotPasswordScreenAction
import neosoft.forgotPassword.ForgotPasswordScreenState
import neosoft.forgotPassword.ForgotPasswordViewModel
import neosoft.login.LoginScreenAction
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ForgotPasswordScreenRoot(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgotPasswordScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ForgotPasswordScreenAction.OnNext -> {
                         router.go(Route.ChangePassword)
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun ForgotPasswordScreen(state: ForgotPasswordScreenState, onAction: (ForgotPasswordScreenAction) -> Unit) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            "Forgot Password",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Let's help you recover account",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = userName,
            onValueChange = { userName = it },
            label = "User Name"
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone"
        )

        Spacer(Modifier.height(24.dp))

        AppPrimaryButton(
            text = "Done",
            onClick = { onAction(ForgotPasswordScreenAction.OnNext) }
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

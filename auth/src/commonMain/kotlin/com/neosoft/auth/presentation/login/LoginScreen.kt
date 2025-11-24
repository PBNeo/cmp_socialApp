package com.neosoft.auth.presentation.login

import androidx.compose.foundation.clickable
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
import com.neosoft.auth.presentation.register.RegisterScreenAction
import com.neosoft.coremodules.navigation.LocalRouter
import com.neosoft.coremodules.navigation.Route
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.AppTextField
import neosoft.login.LoginScreenAction
import neosoft.login.LoginScreenState
import neosoft.login.LoginViewModel
import neosoft.profileSetup.ProfileSetupScreenAction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is LoginScreenAction.OnNext -> {
                    // TODO: navigate
                }
                  is LoginScreenAction.OnForgotPassword -> {
                    router.go(Route.ForgotPassword)
                }
                is LoginScreenAction.OnSignUpClicked -> {
                    router.go(Route.Register)
                }

                else -> Unit
            }
        }
    )
}

@Composable
fun LoginScreen(state: LoginScreenState, onAction: (LoginScreenAction) -> Unit) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            "Sign In",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Enter your credentials",
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
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

            Text(
                "Forgot Password",
                modifier = Modifier.clickable {
                    onAction(LoginScreenAction.OnForgotPassword)

                },
                color = Color(0xFF0077B6),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(24.dp))

        AppPrimaryButton(
            text = "Done",
            onClick = { onAction(LoginScreenAction.OnNext) }
        )
        Spacer(Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Already have an account? ")
            Text(
                "Sign Up",
                modifier = Modifier.clickable {
                    onAction(LoginScreenAction.OnSignUpClicked)

                },
                color = Color(0xFF0077B6),
                fontWeight = FontWeight.Bold
            )
        }


    }
}

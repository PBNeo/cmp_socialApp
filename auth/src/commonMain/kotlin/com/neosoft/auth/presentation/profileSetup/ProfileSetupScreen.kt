package com.neosoft.auth.presentation.profileSetup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.neosoft.designsystem.components.AppDropdown
import com.neosoft.designsystem.components.AppPrimaryButton
import com.neosoft.designsystem.components.AppTextField
import com.neosoft.designsystem.components.DropDownItem
import com.neosoft.designsystem.components.GenderDropdown
import neosoft.profileSetup.ProfileSetupScreenAction
import neosoft.profileSetup.ProfileSetupScreenState
import neosoft.profileSetup.ProfileSetupViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProfileSetupScreenRoot(
    viewModel: ProfileSetupViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileSetupScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ProfileSetupScreenAction.OnNext -> {
                    router.go(Route.AccountSetup)      // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun ProfileSetupScreen(state: ProfileSetupScreenState, onAction: (ProfileSetupScreenAction) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<DropDownItem?>(null) }
    val genders = listOf(
        DropDownItem("Male", "male"),
        DropDownItem("Female", "female"),
        DropDownItem("Other", "other")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            "Personal Information",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Please fill the following",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(Modifier.height(24.dp))

        AppTextField(
            value = name,
            onValueChange = { name = it },
            label = "Full name"
        )

        Spacer(Modifier.height(18.dp))

        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address"
        )

        Spacer(Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppTextField(
                value = dob,
                onValueChange = { dob = it },
                label = "Date of birth",
                modifier = Modifier.weight(1f)
            )

            AppDropdown(
                label = "Gender",
                value = selectedGender,
                items = genders,
                onSelect = { selectedGender = it },
                modifier = Modifier.weight(1f)   // ← FIXED
            )
        }


        Spacer(Modifier.height(18.dp))

        AppTextField(
            value = about,
            onValueChange = { about = it },
            label = "About",
            singleLine = false,
            modifier = Modifier.height(120.dp)
        )

        Spacer(Modifier.height(24.dp))
        AppPrimaryButton(
            text = "Next",
            onClick = { onAction(ProfileSetupScreenAction.OnNext) }
        )
        Spacer(Modifier.height(18.dp))

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

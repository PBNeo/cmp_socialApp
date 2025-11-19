param(
    [Parameter(Mandatory = $true)]
    [string]$FeatureName,

    [Parameter(Mandatory = $true)]
    [string]$DomainName,

    [Parameter(Mandatory = $true)]
    [string]$OutputPath
)

# Normalize naming
$featureLower = $FeatureName.Substring(0,1).ToLower() + $FeatureName.Substring(1)
$featureCapital = $FeatureName

# Folder
$featureFolder = Join-Path $OutputPath $FeatureName
if (-not (Test-Path $featureFolder)) {
    New-Item -ItemType Directory -Path $featureFolder | Out-Null
}

# ---------- FILE PATHS ----------
$screenFile = Join-Path $featureFolder "$FeatureName`Screen.kt"
$actionFile = Join-Path $featureFolder "$FeatureName`ScreenAction.kt"
$stateFile = Join-Path $featureFolder "$FeatureName`ScreenState.kt"
$viewModelFile = Join-Path $featureFolder "$FeatureName`ViewModel.kt"


# ---------- FILE CONTENTS ----------

# RegisterScreen.kt
$screenContent = @"
package $DomainName.$featureLower

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ${FeatureName}ScreenRoot(
    viewModel: ${FeatureName}ViewModel = koinViewModel(),
) {
    val router = LocalRouter.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ${FeatureName}Screen(
        state = state,
        onAction = { action ->
            when(action) {
                is ${FeatureName}ScreenAction.OnNext -> {
                    // TODO: navigate
                }
                else -> Unit
            }
        }
    )
}
"@

# RegisterScreenAction.kt
$actionContent = @"
package $DomainName.$featureLower

interface ${FeatureName}ScreenAction {
    object OnBackPressed : ${FeatureName}ScreenAction
    object OnNext : ${FeatureName}ScreenAction
}
"@

# RegisterScreenState.kt
$stateContent = @"
package $DomainName.$featureLower

class ${FeatureName}ScreenState {
    val isLoading: Boolean = true
}
"@

# RegisterViewModel.kt
$viewModelContent = @"
package $DomainName.$featureLower

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ${FeatureName}ViewModel : ViewModel() {
    private val _state = MutableStateFlow(${FeatureName}ScreenState())
    val state = _state
}
"@

# ---------- WRITE FILES ----------
$files = @{
    $screenFile    = $screenContent
    $actionFile    = $actionContent
    $stateFile     = $stateContent
    $viewModelFile = $viewModelContent
}

foreach ($file in $files.Keys) {
    Set-Content -Path $file -Value $files[$file] -Encoding UTF8
}

Write-Host "Feature '$FeatureName' generated successfully at: $featureFolder" -ForegroundColor Green

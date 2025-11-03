import androidx.lifecycle.ViewModel
import com.neosoft.socialapp.splash.presentation.SplashState
import kotlinx.coroutines.flow.MutableStateFlow

class  SplashViewModel () : ViewModel(){
    private val _state = MutableStateFlow(SplashState())
    val state = _state
}
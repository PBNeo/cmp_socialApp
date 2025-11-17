import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class  VerifyOTPViewModel() : ViewModel(){

    private val _timeLeft = MutableStateFlow(60)
    private val _state = MutableStateFlow(VerifyOTPScreenState())
    val state = _state

    val timeLeft = _timeLeft


    init {
        startTimer()
    }

    fun onAction(action: VerifyOTPAction) {
        when (action) {
            is VerifyOTPAction.onResendOtpClicked -> {
startTimer()
            }

        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            for (i in 60 downTo 0) {
                _timeLeft.value = i
                delay(1000)
            }
        }
    }
}


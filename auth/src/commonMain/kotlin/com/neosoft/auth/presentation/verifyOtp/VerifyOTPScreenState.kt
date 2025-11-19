

data class VerifyOTPScreenState(
    val mobileNo: String = "",
    val otpText: String = "",
    val countryCode: String = "",
    val isLoading: Boolean = true,
    val isTimerRunning: Boolean = false
)


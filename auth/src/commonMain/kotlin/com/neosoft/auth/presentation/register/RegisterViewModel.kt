package com.neosoft.auth.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class  RegisterViewModel () : ViewModel(){
    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state


}
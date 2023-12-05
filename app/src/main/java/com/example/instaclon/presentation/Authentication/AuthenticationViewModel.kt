package com.example.instaclon.presentation.Authentication


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclon.domain.use_cases.AuthenticationUseCases
import com.example.instaclon.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases
) : ViewModel(){

    val isUserAuthenticated get()= authUseCases.isUserAuthenticated()

    private val _signInState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInstate : State<Response<Boolean>> =_signInState

    private val _signUpState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpstate : State<Response<Boolean>> =_signUpState

    private val _signOutState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutstate : State<Response<Boolean>> =_signOutState

    private val _firebaseAuthState= mutableStateOf<Boolean>(false)
    val firebaseAuthState:State<Boolean> = _firebaseAuthState

    fun signOut(){
        viewModelScope.launch {
            authUseCases.firebaseSignOut().collect{
                _signOutState.value= it
                if( it==Response.Success(true)){
                    _signInState.value =Response.Success(false)
                }
            }
        }
    }


    fun signIn(email:String, password:String){
        viewModelScope.launch{
            authUseCases.firebaseSignIn(email= email, password= password).collect {
                _signInState.value= it
            }
        }
    }
    fun signUp(email:String, password:String, username:String){
        viewModelScope.launch {
            authUseCases.firebaseSignUp(email= email, password = password,userName=username)
                .collect{
                    _signUpState.value= it
                }
            }
        }

    fun getFirebaseAuthState(){
        viewModelScope.launch {
            authUseCases.firebaseAuthState().collect{
                _firebaseAuthState.value= it
            }
        }
    }
}




















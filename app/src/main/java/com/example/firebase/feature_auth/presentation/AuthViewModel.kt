package com.example.firebase.feature_auth.presentation

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthViewModel: ViewModel() {

    val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    val user = MutableLiveData<FirebaseUser>()

    val formErrors = ObservableArrayList<FormErrors>()

    private lateinit var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }

    fun checkCurrState(){
        user.value = auth.currentUser
    }

    fun isFormValid(): Boolean{
        formErrors.clear()
        if (email.value.isNullOrEmpty()){
            formErrors.add(FormErrors.MISSING_EMAIL)
        }
        if (pass.value.isNullOrEmpty()){
            formErrors.add(FormErrors.INVALID_PASSWORD)
        }

        return formErrors.isEmpty()
    }

    fun signUp() {
//        auth.createUser(email.value!!, password.value!!)
//        auth = Firebase.auth
        Log.d("AuthViewModel", email.value + " " + pass.value)
        auth.signInWithEmailAndPassword(email.value!!, pass.value!!)
            .addOnCompleteListener { task ->
            if (task.isSuccessful){
                user.value = auth.currentUser
            } else {
                error.value = "Authentication failed."
            }
        }

    }

    fun signOut(){
        auth.signOut()
    }

    enum class FormErrors {
        MISSING_EMAIL,
        INVALID_EMAIL,
        INVALID_PASSWORD,
        PASSWORDS_NOT_MATCHING,
    }
}
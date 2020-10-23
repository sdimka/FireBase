package com.example.firebase.feature_users.presentations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_users.data.User
import com.example.firebase.feature_users.domain.UsersFBService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserEditorViewModel: ViewModel() {

    private val someData = MutableLiveData<String>()
    private val moreData = MutableLiveData<String>()

    private var auth: FirebaseAuth = Firebase.auth

    fun users(): LiveData<String> {
        someData.value = UsersFBService.getInstance("Arg1").createUser("bla", "bla")
        return someData
    }

    fun putUserToDB(){
        val user = User(name = auth.currentUser?.email, uid = auth.currentUser?.uid)
        UsersFBService.getInstance("").putCurrentUser(user)
    }


}
package com.example.firebase.feature_auth.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.BR
import com.example.firebase.MainActivity
import com.example.firebase.R
import com.example.firebase.databinding.ActivityAuthBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity: AppCompatActivity() {

    var viewModel: AuthViewModel? = null
    var binding: ActivityAuthBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


        binding!!.setVariable(BR.viewModel, viewModel)
//        binding!!.executePendingBindings()

//        setContentView(R.layout.activity_auth)

        emailSignInButton.setOnClickListener {
            onFormSubmit()
        }

        logOutButton.setOnClickListener {
            viewModel!!.signOut()
            binding!!.invalidateAll()
        }

        skipButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {}
            startActivity(intent)
        }

        viewModel!!.error.observe(this){
            error_message.text = it
        }
        viewModel!!.user.observe(this){
            if ( it != null) {
                user_name.text = it.uid
                user_mail.text = it.email
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel!!.checkCurrState()

    }

    private fun onFormSubmit() {
        progressBar.visibility = View.VISIBLE
        if (viewModel!!.isFormValid()) {
            viewModel!!.signUp()
            // the rest of your logic to proceed to next screen etc.
        }
        // no need for else block if form invalid, as ViewModel, Observables
        // and databinding will take care of the UI
        binding!!.invalidateAll()
        progressBar.visibility = View.INVISIBLE
    }

}

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String) {
    view.error = errorMessage
}
package com.example.firebase.feature_users.presentations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.firebase.R
import kotlinx.android.synthetic.main.fragment_user_editor.*

class UserEditorFragment: Fragment() {

    lateinit var viewModel: UserEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(UserEditorViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.users().observe(viewLifecycleOwner){
            some_text.text = it
        }

        put_user.setOnClickListener {
            viewModel.putUserToDB()
        }

    }


}
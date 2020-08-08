package com.example.firebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import kotlinx.android.synthetic.main.fridge_editor.*

class FridgeFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fridge_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fridgeEditorAdd.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .addToBackStack("FridgeInfo")
                .replace(R.id.fridgeEditorFridgeInfo, FridgeInfo(), "FridgeInfo" )
                .commit()
        }
    }
}
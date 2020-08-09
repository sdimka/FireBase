package com.example.firebase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.fragments.slotComponents.SlotViewAdapter
import com.example.firebase.models.Slot
import kotlinx.android.synthetic.main.slots.*

class SlotsFragment(val slotsList: ArrayList<Slot>, val horizontalSize: Int ) : Fragment() {

    val TAG = "SlotsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.slots, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        slotsList.forEach{ a -> Log.d(TAG, a.key.toString())}

        slotsRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, horizontalSize)
            adapter = SlotViewAdapter(slotsList)
        }

        slotsRecyclerView.adapter?.notifyDataSetChanged()

    }
}
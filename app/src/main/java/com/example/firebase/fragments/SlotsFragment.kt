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
import com.example.firebase.models.Fridge
import com.example.firebase.models.Slot
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.slots.*

class SlotsFragment(val fridge: Fridge, val fridgeRef: String? ) : Fragment() {

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

        slotsRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, fridge.sizeX!!)
            adapter = SlotViewAdapter(fridge, 1, fridgeRef)
        }

        slotsRecyclerView.adapter?.notifyDataSetChanged()

    }
}
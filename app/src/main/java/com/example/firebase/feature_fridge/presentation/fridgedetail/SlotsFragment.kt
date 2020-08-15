package com.example.firebase.feature_fridge.presentation.fridgedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_fridge.presentation.slotComponents.SlotViewAdapter
import com.example.firebase.feature_fridge.data.Fridge
import com.example.firebase.feature_fridge.domain.FridgeFBService
import kotlinx.android.synthetic.main.slots.*

class SlotsFragment(val fridge: Fridge, val fridgeRef: String?, var selectedBottle: String? ) : Fragment() {

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
        slotsName.setText(fridge.name)

        FridgeFBService.instance.getBusySlotCount(fridgeRef.toString(), slotsBusy)


        upDateView()

        slotsRecyclerView.adapter?.notifyDataSetChanged()

    }

    fun upDateView() {
        if (selectedBottle != null) {
            slotsRecyclerView.apply {
                layoutManager = GridLayoutManager(activity, fridge.sizeX!!)
                adapter = SlotViewAdapter(fridge, fridgeRef, selectedBottle)
            }
        } else {
            slotsRecyclerView.apply {
                layoutManager = GridLayoutManager(activity, fridge.sizeX!!)
                adapter = SlotViewAdapter(fridge, fridgeRef, null)
            }
        }

        slotsRecyclerView.adapter?.notifyDataSetChanged()
    }
}
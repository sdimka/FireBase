package com.example.firebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.models.Fridge
import com.example.firebase.models.Slot
import com.example.firebase.services.FridgeFBService
import kotlinx.android.synthetic.main.fridge_info.*

class FridgeInfo: Fragment() {

    var newFridge: Fridge? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fridge_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fridgeInfoCancel.setOnClickListener {
            val fragment = fragmentManager!!.findFragmentByTag("FridgeInfo");
            if(fragment != null){
            fragmentManager!!.beginTransaction()
                .remove(fragment)
                .commit();}
        }

        fridgeInfoGen.setOnClickListener {
            // toDo : remake to checkable properties
            // toDo : get last ID
            val id = fridgeInfoID.text.toString().toInt()
            val name = fridgeInfoName.text.toString()
            var sizeX : Int? = null
            var sizeY : Int? = null

            if (fridgeInfoSizeX.text.isNotEmpty() &&
                fridgeInfoSizeY.text.isNotEmpty()
            ) {
                sizeX = fridgeInfoSizeX.text.toString().toInt()
                sizeY = fridgeInfoSizeY.text.toString().toInt()

                if (sizeX in 1..21 && sizeY in 1..21){
                    val slotsList = generator(sizeX!!, sizeY!!)
                    newFridge = Fridge(id, name, sizeX, sizeY, slotsList)
                    fragmentManager!!
                        .beginTransaction()
                        .addToBackStack("SlotsFragment")
                        .replace(R.id.fridgeEditorSlots, SlotsFragment(newFridge!!, null), "SlotsFragment" )
                        .commit()
                } else Toast.makeText(this.context, "Размеры должны быть в рамках 1-21", Toast.LENGTH_SHORT ).show()

            } else Toast.makeText(this.context, "Укажите размеры!!!", Toast.LENGTH_SHORT ).show()

            fridgeInfoSave.setOnClickListener {
                if (newFridge != null){
                    FridgeFBService.instance.addFridge(newFridge!!)
                } else {
                    Toast.makeText(this.context, "Please generate correct fridge first!",
                        Toast.LENGTH_SHORT).show()}
            }



        }

        fridgeInfoSave.setOnClickListener {

        }
    }

    fun generator(sX: Int,sY: Int): ArrayList<Slot> {
        val list = arrayListOf<Slot>()
        for (i in 1..sY){
            for (k in 1..sX){
                val ind: String = i.toString() + "." + k.toString()
                list.add(Slot(ind, null))
            }
        }

        return list
    }
}
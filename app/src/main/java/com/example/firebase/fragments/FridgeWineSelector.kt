package com.example.firebase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.R
import com.example.firebase.fragments.bottleComponents.BottleViewFBAdapter
import com.example.firebase.models.Bottle
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.wine_selector.*
import java.lang.ClassCastException

class FridgeWineSelector: Fragment() {

    var selectedWineChanged: SelectedWineChanged? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        try {
            selectedWineChanged = fragmentManager!!.findFragmentByTag("FridgeFragment") as SelectedWineChanged
        } catch (e: ClassCastException){
            throw ClassCastException(
                activity.toString() + " must implement onSomeEventListener"
            )
        }

        return inflater.inflate(R.layout.wine_selector, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = FirebaseDatabase.getInstance()
            .reference
            .child("Bottles")


        val options: FirebaseRecyclerOptions<Bottle> =
            FirebaseRecyclerOptions.Builder<Bottle>()
                .setQuery(query, Bottle::class.java)
                .setLifecycleOwner(this)
                .build()

        val bAdapter = BottleViewFBAdapter(options, this) { item ->

            selectedWineChanged?.changed(item.id)
//            activity!!.supportFragmentManager
//                .beginTransaction()
//                .replace(
//                    R.id.bottleEditorFrameContainer,
//                    BottleItemEditor(item, item.id),
//                    "BottleItemEditor"
//                )
//                .commit()
        }

        recyclerBottleSelector.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bAdapter
        }

        wineSelectorClose.setOnClickListener {
            val fragment = fragmentManager!!.findFragmentByTag("WineSelector");
            if(fragment != null){
                fragmentManager!!.beginTransaction()
                    .remove(fragment)
                    .commit();}
        }
    }
}
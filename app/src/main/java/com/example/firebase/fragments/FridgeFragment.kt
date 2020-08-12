package com.example.firebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.R
import com.example.firebase.fragments.fridgeComponents.FridgeViewFBAdapter
import com.example.firebase.models.Fridge
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fridge_editor.*

class FridgeFragment : Fragment(), SelectedWineChanged{

    var selectedBottle: String? = null

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

        val query = FirebaseDatabase.getInstance()
            .reference
            .child("Fridge")


        val options: FirebaseRecyclerOptions<Fridge> =
            FirebaseRecyclerOptions.Builder<Fridge>()
                .setQuery(query, Fridge::class.java)
                .setLifecycleOwner(this)
                .build()


        val fridgeFBAdapter = FridgeViewFBAdapter(options, this, selectedBottle)

        recyclerFrige.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fridgeFBAdapter
        }

        fridgeEditorAdd.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .addToBackStack("FridgeInfo")
                .replace(R.id.fridgeEditorFridgeInfo, FridgeInfo(), "FridgeInfo" )
                .commit()
        }

        fridgeEditorWineSelect.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .addToBackStack("WineSelector")
                .replace(R.id.fridgeEditorFridgeInfo, FridgeWineSelector(), "WineSelector" )
                .commit()
        }


    }

    override fun changed(newWine: String?) {

        var frag = (fragmentManager!!.findFragmentByTag("SlotsFragment") as SlotsFragment)
        frag.selectedBottle = newWine
        frag.upDateView()

    }
}
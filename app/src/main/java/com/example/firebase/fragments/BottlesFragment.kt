package com.example.firebase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.BottleItemEditor
import com.example.firebase.fragments.bottleComponents.BottleViewHolder
import com.example.firebase.R
import com.example.firebase.fragments.bottleComponents.BottleViewFBAdapter
import com.example.firebase.models.Bottle
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.bottle_editor.*


class BottlesFragment: Fragment() {

    val bottleList = arrayListOf<Bottle>()

//    val bs = BottleFBService.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.bottle_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val suppFM = activity?.supportFragmentManager
//        recyclerBottle.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = BottleViewAdapter(bottleList)
//        }
//
//        BottleFBService.instance.basicReadWrite(bottleList, recyclerBottle)

//        val fbRecAdapter = FirebaseRecyclerAdapter

        val query = FirebaseDatabase.getInstance()
            .reference
            .child("Bottles")


        val options: FirebaseRecyclerOptions<Bottle> =
            FirebaseRecyclerOptions.Builder<Bottle>()
                .setQuery(query, Bottle::class.java)
                .setLifecycleOwner(this)
                .build()

        val bAdapter = BottleViewFBAdapter(options, this) { item ->

            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.bottleEditorFrameContainer,
                    BottleItemEditor(item, item.id),
                    "BottleItemEditor"
                )
                .commit()
        }

        recyclerBottle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bAdapter
        }

    }
}
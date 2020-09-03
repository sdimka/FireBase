package com.example.firebase.feature_bottles.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.feature_bottles.presentation.bottledetail.BottleItemEditor
import com.example.firebase.R
import com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview.BottleViewFBAdapter
import com.example.firebase.feature_bottles.data.model.Bottle
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.bottle_editor.*


class BottlesFragment: Fragment() {

    private val bottleList = arrayListOf<Bottle>()
    private val TAG = "BottlesFragment"

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

        val query = FirebaseDatabase.getInstance()
            .reference
            .child("Bottles")

        val options: FirebaseRecyclerOptions<Bottle> =
            FirebaseRecyclerOptions.Builder<Bottle>()
                .setQuery(query, Bottle::class.java)
                .setLifecycleOwner(this)
                .build()

        val bAdapter =
            BottleViewFBAdapter(
                options,
                this
            ) { item ->

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.bottleEditorFrameContainer,
                        BottleItemEditor(
                            item,
                            item.id
                        ),
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
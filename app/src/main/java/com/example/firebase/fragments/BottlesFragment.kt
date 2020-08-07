package com.example.firebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.BottleViewAdapter
import com.example.firebase.R
import com.example.firebase.models.Bottle
import com.example.firebase.services.BottleFBService
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

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


        val suppFM = activity?.supportFragmentManager
        recyclerBottle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BottleViewAdapter(bottleList)
        }

        BottleFBService.instance.basicReadWrite(bottleList, recyclerBottle)

//        val fbRecAdapter = FirebaseRecyclerAdapter

    }
}
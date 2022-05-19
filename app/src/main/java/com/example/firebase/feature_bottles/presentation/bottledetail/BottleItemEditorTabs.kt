package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import kotlinx.android.synthetic.main.fragment_bottle_tabs.*

class BottleItemEditorTabs: Fragment() {

    private lateinit var adapter: TabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottle_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = TabAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(Tab1Fragment(), "Основные")
        adapter.addFragment(Tab2Fragment(), "Описание")
        adapter.addFragment(Tab3Fragment(), "Картинки")
        adapter.addFragment(Tab4Fragment(), "К Еде")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
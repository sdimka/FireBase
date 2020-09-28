package com.example.firebase.feature_bottles.presentation.bottledetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listFragment = arrayListOf<Fragment>()
    private val fragmentTitle = arrayListOf<String>()

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment.get(position)
    }

    fun addFragment(fragment: Fragment, title: String){
        listFragment.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle.get(position)
    }
}
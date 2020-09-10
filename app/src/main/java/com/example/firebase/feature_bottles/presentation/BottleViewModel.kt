package com.example.firebase.feature_bottles.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBLiveData

class BottleViewModel: ViewModel() {

    private lateinit var bottleList: MutableLiveData<List<Bottle>>

    fun getBottles(): LiveData<List<Bottle>>{
        return BottleFBLiveData()
    }
}
package com.example.firebase.feature_bottles.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBLiveData
import com.example.firebase.feature_bottles.domain.BottleFBService

class BottleViewModel: ViewModel() {

    private lateinit var bottleList: MutableLiveData<List<Bottle>>
    val currBottle = MutableLiveData<Bottle>()

    fun getBottlesList(): LiveData<List<Bottle>>{
        return BottleFBLiveData()
    }

    fun selectBottle(bottle: Bottle){
        currBottle.value = bottle
    }

    fun getBottle(): LiveData<Bottle>{
        return currBottle
    }

    fun saveBottle(){
        BottleFBService.instance.upDate(currBottle.value!!.refID!!, currBottle.value!!)
    }

}
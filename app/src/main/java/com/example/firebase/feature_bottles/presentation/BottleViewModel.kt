package com.example.firebase.feature_bottles.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBLiveData
import com.example.firebase.feature_bottles.domain.BottleFBService
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.domain.FoodCardFBLiveData

class BottleViewModel: ViewModel() {

    private lateinit var bottleList: MutableLiveData<List<Bottle>>
    val currBottle = MutableLiveData<Bottle>()

    private val foodCardList = FoodCardFBLiveData()

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

    fun foodCardListData(): LiveData<List<FoodCard>>{
        return foodCardList
    }

    fun onFoodCardSelect(foodCard: FoodCard) {
        if (currBottle.value?.foodCombines != null) {
            if (currBottle.value?.foodCombines!!.contains(foodCard.refId!!)) {
                 currBottle.value?.foodCombines?.remove(foodCard.refId!!)
            } else {
                currBottle.value?.foodCombines?.add(foodCard.refId!!)
            }
        } else {
            currBottle.value?.foodCombines = arrayListOf<String>()
            foodCard.refId?.let { currBottle.value?.foodCombines?.add(it) }
        }
        currBottle.value?.foodCombines!!.forEach { Log.d("BottleViewModel", it) }

    }

}
package com.example.firebase.feature_food.presentation

import android.net.Uri
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.domain.FoodCardFBLiveData
import com.example.firebase.feature_food.domain.FoodCardFBService

class FoodCardViewModel: ViewModel(){

    private val cardList = FoodCardFBLiveData()
    private val fcFBService = FoodCardFBService.instance

    private val currentCard = MutableLiveData<FoodCard>()

    val changesList = MutableLiveData<List<Changes>>()

    init {
        changesList.value = listOf()
    }


    fun getFoodCards(): LiveData<List<FoodCard>> {
        return cardList
    }

    fun currentCard(): LiveData<FoodCard>{
        return currentCard
    }

    fun saveCard(){
        currentCard.value?.let {
            if (it.refId != null){
                fcFBService.upDate(it.refId!!, it)
            } else fcFBService.add(it) }
    }

    fun newFoodCard() {
        currentCard.value = FoodCard()
    }

    fun changesList(): LiveData<List<Changes>>{
        return changesList
    }

    fun upDateCurrCard(name: String?){
        currentCard.value!!.type = name
    }

    fun onCardSelect(foodCard: FoodCard) {
        currentCard.value = foodCard
    }

    fun gotChanges(changes: Changes, newVal: String) {
        val newList = arrayListOf<Changes>()
        changesList.value?.let { newList.addAll(it) }
        newList.add(changes)
        changesList.value = newList

        when(changes){
            Changes.BIG_IMG -> currentCard.value!!.pict = newVal
            Changes.ICON_IMG -> currentCard.value!!.icon = newVal
            Changes.NAME -> currentCard.value!!.type = newVal
        }
    }

    fun clearChanges(){
        changesList.value = listOf()
    }

    enum class Changes {
        BIG_IMG,
        ICON_IMG,
        NAME
    }

}
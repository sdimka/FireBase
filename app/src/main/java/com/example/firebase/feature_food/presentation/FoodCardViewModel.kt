package com.example.firebase.feature_food.presentation

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

    fun upDateCurrCard(name: String?){
        currentCard.value!!.type = name
    }

    fun onCardSelect(foodCard: FoodCard) {
        currentCard.value = foodCard
    }


}
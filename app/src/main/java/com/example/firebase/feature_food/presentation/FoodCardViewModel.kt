package com.example.firebase.feature_food.presentation

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.domain.FoodCardFBLiveData
import com.example.firebase.feature_food.domain.FoodCardFBService
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask

class FoodCardViewModel(application: Application): AndroidViewModel(application){

    private val cardList = FoodCardFBLiveData()
    private val fcFBService = FoodCardFBService.instance

    private val currentCard = MutableLiveData<FoodCard>()

    val changesList = MutableLiveData<List<Changes>>()

    private lateinit var storageRef: StorageReference
    private lateinit var uploadTask: StorageTask<UploadTask.TaskSnapshot>

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

        if (changesList.value?.contains(Changes.BIG_IMG)!!){

        }

        val taskCollection = arrayListOf<StorageTask<UploadTask.TaskSnapshot>>()
        taskCollection.add(uploadTask)
        val tasks = Tasks.whenAll(taskCollection)

    }

//    private fun uploadFile() {
//        if (::imgUri.isInitialized) {
//            val fileRef = storageRef.child(
//                System.currentTimeMillis().toString() +
//                        "." + getFileExtension(imgUri)
//            )
//
//            uploadTask = fileRef.putFile(imgUri)
//                .addOnSuccessListener {
//                    progress_bar_upload.progress = 0
//                    Toast.makeText(getApplication(), "Загружено!", Toast.LENGTH_SHORT).show()
//
//                    fileRef.downloadUrl.addOnSuccessListener {
//                        mBottle.bottleImage = it.toString()
//                        viewModel.saveBottle()
//                    }
//
//                }
//                .addOnFailureListener {
//                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
//                        .show()
//                }
//                .addOnProgressListener {
//                    val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
//                    progress_bar_upload.progress = progress.toInt()
//                    progress_bar_upload.elevation = 2F
//                }
//        } else {
//            Toast.makeText(requireContext(), "Выбрать файл!", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun getFileExtension(imgUri: Uri): String? {
        val cr: ContentResolver = getApplication<Application>().contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(imgUri))
    }

    fun newFoodCard() {
        currentCard.value = FoodCard()
    }

    fun changesList(): LiveData<List<Changes>>{
        return changesList
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
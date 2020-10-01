package com.example.firebase.feature_food.presentation

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.domain.FoodCardFBLiveData
import com.example.firebase.feature_food.domain.FoodCardFBService
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.*

class FoodCardViewModel(application: Application): AndroidViewModel(application){

    private val cardList = FoodCardFBLiveData()
    private val fcFBService = FoodCardFBService.instance

    private val currentCard = MutableLiveData<FoodCard>()

    val changesList = MutableLiveData<List<Changes>>()

    private lateinit var storageRef: StorageReference
    private lateinit var uploadTask: StorageTask<UploadTask.TaskSnapshot>

    init {
        changesList.value = listOf()
        storageRef = FirebaseStorage.getInstance().getReference("images")
    }

    fun getFoodCards(): LiveData<List<FoodCard>> {
        return cardList
    }

    fun currentCard(): LiveData<FoodCard>{
        return currentCard
    }

    fun saveCard(){

        val taskList = arrayListOf<Task<String>>()

        if (changesList.value?.contains(Changes.BIG_IMG)!!){

            val source = TaskCompletionSource<String>()
            val task1 = source.task
            uploadFile(currentCard.value!!.pict!!, source)
            taskList.add(task1)
            task1.addOnSuccessListener {
                currentCard.value!!.pict = it
            }
        }

        if (changesList.value?.contains(Changes.ICON_IMG)!!){
            val source = TaskCompletionSource<String>()
            val task2 = source.task
            uploadFile(currentCard.value!!.icon!!, source)
            taskList.add(task2)
            task2.addOnSuccessListener {
                currentCard.value!!.icon = it
            }
        }

        val tasks = Tasks.whenAll(taskList)
        tasks
            .addOnSuccessListener {
                currentCard.value?.let {
                    if (it.refId != null){
                        fcFBService.upDate(it.refId!!, it)
                    } else fcFBService.add(it) }
            }
            .addOnFailureListener {
                Toast.makeText(getApplication(), "Что-то пошло не так!", Toast.LENGTH_SHORT).show()
            }

    }

    private fun uploadFile(uri: String, source: TaskCompletionSource<String>): StorageReference {

        val uploadUri = Uri.parse(uri)

        val fileRef = storageRef.child(
            System.currentTimeMillis().toString() +
                    "." + getFileExtension(uploadUri)
        )

        fileRef.putFile(uploadUri)
            .addOnSuccessListener {
//                progress_bar_upload.progress = 0
                fileRef.downloadUrl.addOnSuccessListener {
                source.setResult(it.toString())
                Toast.makeText(getApplication(), "Загружено!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(getApplication(), it.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
//                progress_bar_upload.progress = progress.toInt()
//                progress_bar_upload.elevation = 2F
            }

        return fileRef
    }

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
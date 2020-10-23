package com.example.firebase.feature_bottles.presentation

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.databinding.InverseMethod
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBLiveData
import com.example.firebase.feature_bottles.domain.BottleFBService
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.domain.FoodCardFBLiveData
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BottleViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var bottleList: MutableLiveData<List<Bottle>>
    val currBottle = MutableLiveData<Bottle>()

    private val foodCardList = FoodCardFBLiveData()

    private val changesList = MutableLiveData<List<ChangesTypes>>()

    private val errors = MutableLiveData<String>()

    private var storageRef: StorageReference

    init {
        changesList.value = listOf()
        storageRef = FirebaseStorage.getInstance().getReference("images")
    }

    fun getBottlesList(): LiveData<List<Bottle>>{
        return BottleFBLiveData()
    }

    fun selectBottle(bottle: Bottle){
        currBottle.value = bottle
    }

    fun getBottle(): LiveData<Bottle>{
        return currBottle
    }

    fun saveBottle() {

        val taskList = arrayListOf<Task<String>>()

        changesList.value?.forEach { changesTypes ->
            val source = TaskCompletionSource<String>()
            val mTask = source.task
            when (changesTypes) {
                ChangesTypes.BIG_IMG -> {
                    uploadFile(currBottle.value!!.bigImg, source)
                    mTask.addOnSuccessListener {
                        currBottle.value!!.bigImg = it
                    }
                }
                ChangesTypes.SMALL_IMG -> {
                    uploadFile(currBottle.value!!.smallImg, source)
                    mTask.addOnSuccessListener {
                        currBottle.value!!.smallImg = it
                    }
                }
                ChangesTypes.BOTTLE_IMG -> {
                    uploadFile(currBottle.value!!.bottleImage, source)
                    mTask.addOnSuccessListener {
                        currBottle.value!!.bottleImage = it
                    }
                }
            }
            taskList.add(mTask)
        }

        val tasks = Tasks.whenAll(taskList)
        tasks
            .addOnSuccessListener {
                BottleFBService.instance.upDate(currBottle.value!!.refID!!, currBottle.value!!)
                changesList.value = listOf()
            }
            .addOnFailureListener {
                errors.value = it.message.toString()
                Toast.makeText(getApplication(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }


    }

    private fun uploadFile(uri: String?, source: TaskCompletionSource<String>) {

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

    }

    private fun getFileExtension(imgUri: Uri): String? {
        val cr: ContentResolver = getApplication<Application>().contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(imgUri))
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

    fun gotChanges(changesTypes: ChangesTypes, newVal: String) {
        val newList = arrayListOf<ChangesTypes>()
        newList.addAll(changesList.value!!)
        newList.add(changesTypes)
        changesList.value = newList

        when(changesTypes){
            ChangesTypes.BIG_IMG -> currBottle.value!!.bigImg = newVal
            ChangesTypes.SMALL_IMG -> currBottle.value!!.smallImg = newVal
            ChangesTypes.BOTTLE_IMG -> currBottle.value!!.bottleImage = newVal
        }
    }

    @InverseMethod("toBlended")
    fun isBlended(blended: Boolean): Int {
        return if (blended) R.id.radio_button_blend
        else R.id.radio_button_sorted
    }

    fun toBlended(type: Int): Boolean{
        return type == R.id.radio_button_blend

    }

    enum class ChangesTypes {
        BIG_IMG,
        SMALL_IMG,
        BOTTLE_IMG
    }

}
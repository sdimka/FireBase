package com.example.firebase.feature_bottles.presentation.bottledetail

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBService
import com.example.firebase.feature_bottles.presentation.BottleViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottle_item_editor.*
import okhttp3.internal.wait

class BottleItemEditor: Fragment() {

    private var img_req = 1
    private lateinit var imgUri: Uri
    private lateinit var storageRef: StorageReference
    private lateinit var uploadTask: StorageTask<UploadTask.TaskSnapshot>
    private lateinit var mBottle: Bottle

    private lateinit var viewModel: BottleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(BottleViewModel::class.java)
        storageRef = FirebaseStorage.getInstance().getReference("images")
        return inflater.inflate(R.layout.bottle_item_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        bottleItemName.setText(bottle.name)
//        bottleItemYear.setText(bottle.year.toString())
//        bottleItemFBKey.text = key

        viewModel.getBottle().observe(viewLifecycleOwner){
            mBottle = it
            bottleItemName.setText(it.name)
            bottleItemYear.setText(it.year.toString())
            bottleItemFBKey.setText(it.refID)

            if (it.bottleImage != null) {
                Picasso.get()
                    .load(it.bottleImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(image_view)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(image_view)

            }

        }

        bottleItemSave.setOnClickListener {
            mBottle.apply {
                refID = bottleItemFBKey.text.toString()
                name = bottleItemName.text.toString()
                year = bottleItemYear.text.toString().toInt()
            }
            viewModel.saveBottle()
        }


        buttn_sel_file.setOnClickListener {
            fileSelector()
        }

        buttn_upload.setOnClickListener {

            if (::uploadTask.isInitialized && uploadTask.isInProgress){
                Toast.makeText(requireContext(), "Уже загружаем!", Toast.LENGTH_SHORT).show()
            } else{
                uploadFile()
            }
        }

        buttn_show_content.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.imagesListFragment, null))

    }

    private fun openImagesList() {

    }

    private fun uploadFile() {
        if (::imgUri.isInitialized) {
            val fileRef = storageRef.child(
                System.currentTimeMillis().toString() +
                        "." + getFileExtension(imgUri)
            )

            uploadTask = fileRef.putFile(imgUri)
                .addOnSuccessListener {
                    progress_bar_upload.progress = 0
                    Toast.makeText(requireContext(), "Загружено!", Toast.LENGTH_SHORT).show()

                    fileRef.downloadUrl.addOnSuccessListener {
                        mBottle.bottleImage = it.toString()
                        viewModel.saveBottle()
//                        BottleFBService.instance.upDate(key!!, bottle)
                    }

                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener {
                    val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
                    progress_bar_upload.progress = progress.toInt()
                    progress_bar_upload.elevation = 2F
                }
        } else {
            Toast.makeText(requireContext(), "Выбрать файл!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileExtension(imgUri: Uri): String? {
        val cr: ContentResolver = requireContext().contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(imgUri))
    }


    private fun fileSelector() {
        val intent = Intent()
        intent.type = "image/*"
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, img_req)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == img_req && resultCode == -1 && data != null && data.data != null){
            imgUri = data.data!!
            Picasso.get().load(imgUri).into(image_view)
        }
    }

    fun save(fbKey: String){

    }

}
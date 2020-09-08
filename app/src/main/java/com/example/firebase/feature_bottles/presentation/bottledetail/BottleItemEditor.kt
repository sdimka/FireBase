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
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBService
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottle_item_editor.*

class BottleItemEditor(val bottle: Bottle, val key: String?): Fragment() {

    private var img_req = 1
    private lateinit var imgUri: Uri
    private lateinit var storageRef: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        storageRef = FirebaseStorage.getInstance().getReference("images")
        return inflater.inflate(R.layout.bottle_item_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottleItemName.setText(bottle.name)
        bottleItemYear.setText(bottle.year.toString())
        bottleItemFBKey.text = key

        bottleItemSave.setOnClickListener {
            bottle.name = bottleItemName.text.toString()
            BottleFBService.instance.upDate(key!!, bottle)
        }


        butt_sel_file.setOnClickListener {
            fileSelector()
        }

        butt_upload.setOnClickListener {
            uploadFile()
        }

        butt_show_content.setOnClickListener {

        }
    }

    private fun uploadFile() {
        if (imgUri != null) {
            var fileRef = storageRef.child(
                System.currentTimeMillis().toString() +
                        "." + getFileExtension(imgUri)
            )

            fileRef.putFile(imgUri)
                .addOnSuccessListener {
                    progress_bar_upload.progress = 0
                    Toast.makeText(requireContext(), "Загружено!", Toast.LENGTH_SHORT).show()

                    fileRef.downloadUrl.addOnSuccessListener {
                        bottle.bottleImage = it.toString()
                        BottleFBService.instance.upDate(key!!, bottle)
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
            Picasso.get().load(imgUri).into(image_holder)
        }
    }

    fun save(fbKey: String){

    }

}
package com.example.firebase.feature_bottles.presentation.images_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.ImageInfo
import com.example.firebase.feature_bottles.presentation.images_list.recyclerview.ImageListAdapter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.frament_images_list.*

class ImagesListFragment: Fragment() {

    private lateinit var storageRef: StorageReference
    private var iList = arrayListOf<ImageInfo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        storageRef = FirebaseStorage.getInstance().getReference("images")

        return inflater.inflate(R.layout.frament_images_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        storageRef.listAll().addOnSuccessListener{ it ->
            for (k in it.items){
                var ii = ImageInfo(k.name, k.path)
                k.downloadUrl.addOnSuccessListener { uri ->
                    ii.uri = uri
                    images_list_recycler.adapter!!.notifyDataSetChanged()
                }
                iList.add(ii)
            }
        }

        val adap = ImageListAdapter(iList)
        val manager = GridLayoutManager(requireContext(), 2)

        images_list_recycler.apply {
            adapter = adap
            layoutManager = manager
        }


    }
}
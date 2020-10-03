package com.example.firebase.feature_bottles.presentation.bottledetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.presentation.BottleViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tab3.*



class Tab3Fragment: Fragment() {

    private lateinit var viewModel: BottleViewModel
    private val bigImgReq = 1
    private val smallImgReq = 2
    private val bottleReq = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(BottleViewModel::class.java)

        return inflater.inflate(R.layout.fragment_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getBottle().observe(viewLifecycleOwner) {
            if (it != null) {
                setCurrentBottleImage(it.bigImg, bottle_big_card)
                setCurrentBottleImage(it.smallImg, bottle_small_card)
                setCurrentBottleImage(it.bottleImage, bottle_bottle_image)
            }
        }

        bottle_big_card.setOnClickListener {
            fileSelector(bigImgReq)
        }

        bottle_small_card.setOnClickListener {
            fileSelector(smallImgReq)
        }

        bottle_bottle_image.setOnClickListener {
            fileSelector(bottleReq)
        }

        buttonSave.setOnClickListener {
            viewModel.saveBottle()
        }
    }

    private fun setCurrentBottleImage(imgUrl: String?, view: ImageView) {
        if (imgUrl != null) {
            Picasso.get()
                .load(imgUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_error_outline_24)
                .fit().centerCrop()
                .into(view)
        } else {
            Picasso.get()
                .load(R.drawable.ic_no_image1)
                .placeholder(R.drawable.ic_no_image1)
                .into(view)

        }
    }

    private fun fileSelector(type: Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, type)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == bigImgReq && resultCode == -1 && data != null && data.data != null) {
            viewModel.gotChanges(BottleViewModel.ChangesTypes.BIG_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_big_card)
        } else if (requestCode == smallImgReq && resultCode == -1 && data != null && data.data != null) {
            viewModel.gotChanges(BottleViewModel.ChangesTypes.SMALL_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_small_card)
        } else if (requestCode == bottleReq && resultCode == -1 && data != null && data.data != null) {
            viewModel.gotChanges(BottleViewModel.ChangesTypes.BOTTLE_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_bottle_image)
        }
    }
}
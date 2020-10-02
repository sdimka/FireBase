package com.example.firebase.feature_bottles.presentation.bottledetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.presentation.BottleViewModel
import com.example.firebase.feature_food.presentation.FoodCardViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tab3.*
import kotlinx.android.synthetic.main.fragmet_food_card.*


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
            setCurrentBottle(it)
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
    }

    private fun setCurrentBottle(bottle: Bottle?) {
        if (bottle != null) {
            if (bottle.bigImg != null) {
                Picasso.get()
                    .load(bottle.bigImg)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(bottle_big_card)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(bottle_big_card)

            }
            if (bottle.smallImg != null) {
                Picasso.get()
                    .load(bottle.smallImg)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(bottle_small_card)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(bottle_small_card)

            }
            if (bottle.bottleImage != null) {
                Picasso.get()
                    .load(bottle.bottleImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(bottle_bottle_image)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(bottle_bottle_image)

            }
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
//            viewModel.gotChanges(FoodCardViewModel.Changes.BIG_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_big_card)
        } else if (requestCode == smallImgReq && resultCode == -1 && data != null && data.data != null) {
//            viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_small_card)
        } else if (requestCode == bottleReq && resultCode == -1 && data != null && data.data != null) {
//            viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(bottle_bottle_image)
        }
    }
}
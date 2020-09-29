package com.example.firebase.feature_food.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_food.presentation.recyclerview.FoodCardListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottle_item_editor.*
import kotlinx.android.synthetic.main.fragmet_food_card.*

class FoodCardFragment: Fragment() {

    private lateinit var viewModel: FoodCardViewModel

    private var img_big = 1
    private var img_icon = 2
    private lateinit var bigImgURI: Uri
    private lateinit var iconImgURI: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(FoodCardViewModel::class.java)
        return inflater.inflate(R.layout.fragmet_food_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lAdapter = FoodCardListAdapter()
        lAdapter.setClickListener {
            viewModel.onCardSelect(it)
        }
        val manager = GridLayoutManager(requireContext(), 4)

        food_card_recycler.apply {
            adapter = lAdapter
            layoutManager = manager
        }

        viewModel.getFoodCards().observe(viewLifecycleOwner){
            lAdapter.setList(it)
            lAdapter.notifyDataSetChanged()
        }

        viewModel.currentCard().observe(viewLifecycleOwner){
            text_card_name.setText(it.type)
        }

        button_add.setOnClickListener {
            viewModel.newFoodCard()
        }

        button_save.setOnClickListener {
            viewModel.upDateCurrCard(text_card_name.text.toString())
            viewModel.saveCard()
        }

        big_fc_image.setOnClickListener {
            fileSelector(img_big)
        }

        icon_fc_image.setOnClickListener {
            fileSelector(img_icon)
        }
    }

    private fun fileSelector(type: Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, type)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == img_big && resultCode == -1 && data != null && data.data != null){
            bigImgURI = data.data!!
            Picasso.get().load(bigImgURI).into(big_fc_image)
        } else if (requestCode == img_icon && resultCode == -1 && data != null && data.data != null){
            iconImgURI = data.data!!
            Picasso.get().load(iconImgURI).into(icon_fc_image)
        }
    }
}
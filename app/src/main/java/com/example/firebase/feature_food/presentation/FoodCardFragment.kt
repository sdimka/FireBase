package com.example.firebase.feature_food.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.presentation.recyclerview.FoodCardListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragmet_food_card.*

class FoodCardFragment: Fragment() {

    private lateinit var viewModel: FoodCardViewModel

    private var img_big = 1
    private var img_icon = 2
//    private lateinit var mFoodCard: FoodCard
//    private lateinit var bigImgURI: Uri
//    private lateinit var iconImgURI: Uri

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
            viewModel.clearChanges()
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
            setCurrentCard(it)
        }

        button_save.isEnabled = false

        viewModel.changesList().observe(viewLifecycleOwner){
            button_save.isEnabled = it.isNotEmpty()
        }

        text_card_name.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.gotChanges(FoodCardViewModel.Changes.NAME, s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        button_add.setOnClickListener {
            viewModel.newFoodCard()
        }

        button_save.setOnClickListener {
//            viewModel.upDateCurrCard(text_card_name.text.toString())
            viewModel.saveCard()
        }


        big_fc_image.setOnClickListener {
            fileSelector(img_big)
        }

        icon_fc_image.setOnClickListener {
            fileSelector(img_icon)
        }
    }

    private fun setCurrentCard(foodCard: FoodCard?) {
        if (foodCard != null) {
            text_card_name.setText(foodCard.type)

            if (foodCard.pict != null) {
                Picasso.get()
                    .load(foodCard.pict)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(big_fc_image)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(big_fc_image)

            }

            if (foodCard.icon != null) {
                Picasso.get()
                    .load(foodCard.icon)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(icon_fc_image)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(icon_fc_image)

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
        if (requestCode == img_big && resultCode == -1 && data != null && data.data != null){
            viewModel.gotChanges(FoodCardViewModel.Changes.BIG_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(big_fc_image)
        } else if (requestCode == img_icon && resultCode == -1 && data != null && data.data != null){
            viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG, data.data.toString())
            Picasso.get().load(data.data.toString()).into(icon_fc_image)
        }
    }
}

package com.example.firebase.feature_food.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_food.data.FoodCard
import com.example.firebase.feature_food.presentation.recyclerview.FoodCardListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragmet_food_card.*

class FoodCardFragment: Fragment() {

    private lateinit var viewModel: FoodCardViewModel

    private var imgBig = 1
    private var imgIcon = 2

    private val getContent = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()) {
        MySecondActivityContract()) { parseFileSelectResult(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[FoodCardViewModel::class.java]

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
            fileSelector(ActivitySourceValue.ImgBig)
        }

        icon_fc_image.setOnClickListener {
            fileSelector(ActivitySourceValue.ImgIcon)
        }

        icon_black_fc_image.setOnClickListener {
            fileSelector(ActivitySourceValue.ImgIconBlack)
        }

        icon_beige_fc_image.setOnClickListener {
            fileSelector(ActivitySourceValue.ImgIconBeige)
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
                    .fit().centerCrop()
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

            if (foodCard.iconBlack != null) {
                Picasso.get()
                    .load(foodCard.iconBlack)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(icon_black_fc_image)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(icon_black_fc_image)

            }

            if (foodCard.iconBeige != null) {
                Picasso.get()
                    .load(foodCard.iconBeige)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(icon_beige_fc_image)
            } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(icon_beige_fc_image)

            }
        }
    }

    private fun fileSelector(type: ActivitySourceValue) {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        intent.putExtra("input_key", "Some text")
//        startActivityForResult(intent, type)

        getContent.launch(type)
    }

    private fun parseFileSelectResult(data: Intent?) {
//        requestCode: Int, resultCode: Int,
//        println(it?.data.toString())
//        when (it?.extras?.get("stag")) {
//            ActivitySourceValue.ImgBig -> println("GotBig")
//            ActivitySourceValue.ImgIcon -> println("GotIcon")
//        }

        if (data != null && data.getIntExtra("resultCode", 0) == -1){
            when (data.extras?.get("stag")) {
                ActivitySourceValue.ImgBig -> {
                    viewModel.gotChanges(FoodCardViewModel.Changes.BIG_IMG, data.data.toString())
                    Picasso.get().load(data.data.toString()).into(big_fc_image)
                }
                ActivitySourceValue.ImgIcon -> {
                    viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG, data.data.toString())
                    Picasso.get().load(data.data.toString()).into(icon_fc_image)
                }
                ActivitySourceValue.ImgIconBlack -> {
                    viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG_BLACK, data.data.toString())
                    Picasso.get().load(data.data.toString()).into(icon_black_fc_image)
                }
                ActivitySourceValue.ImgIconBeige -> {
                    viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG_BEIGE, data.data.toString())
                    Picasso.get().load(data.data.toString()).into(icon_beige_fc_image)
                }
            }

        }

//        if (requestCode == imgBig && resultCode == -1 && data != null && data.data != null){
//            viewModel.gotChanges(FoodCardViewModel.Changes.BIG_IMG, data.data.toString())
//            Picasso.get().load(data.data.toString()).into(big_fc_image)
//        } else if (requestCode == imgIcon && resultCode == -1 && data != null && data.data != null){
//            viewModel.gotChanges(FoodCardViewModel.Changes.ICON_IMG, data.data.toString())
//            Picasso.get().load(data.data.toString()).into(icon_fc_image)
//        }
    }

}

enum class ActivitySourceValue{
    ImgBig,
    ImgIcon,
    ImgIconBlack,
    ImgIconBeige
}

class MySecondActivityContract : ActivityResultContract<ActivitySourceValue, Intent?>() {

    var cuurentVal: ActivitySourceValue? = null

    override fun createIntent(context: Context, input: ActivitySourceValue): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        cuurentVal = input

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? = when {
        resultCode != -1 -> intent?.putExtra("resultCode", resultCode)
        else -> {
            intent?.putExtra("stag", cuurentVal)
            intent?.putExtra("resultCode", resultCode)
        }
//            intent?.getIntExtra("my_result_key", 42)
    }

//    override fun getSynchronousResult(context: Context, input: String?): SynchronousResult<Intent?>? {
//        return if (input.isNullOrEmpty()) SynchronousResult(Intent()) else null
//    }
}

class MyLifecycleObserver(private val registry : ActivityResultRegistry)
    : DefaultLifecycleObserver {

    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
            // Handle the returned Uri
        }
    }

    fun selectImage() {
        getContent.launch("image/*")
    }
}

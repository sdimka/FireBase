package com.example.firebase.feature_bottles.data

import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle


object BottleRepo {
    var items = arrayListOf<Bottle>()

    init {

        items.add(
            Bottle(
            "-ME454GXYBOwGVo66qUX",
            "Рислинг",
            2019,
            13.5,
            1,
            sparkling = false,
            blend = false,
            smallImg = "R.drawable.bottle",
            bigImg = "R.drawable.bottle",
            bottleImage = "R.drawable.bottle",
            bottleCard = "R.drawable.bottle",
            backImage = "R.drawable.bottle",
            foodCombines = arrayListOf("4", "2", "3")
            )
        )

        items.add(
            Bottle(
                "-ME454GmQQ8HX2gx5FyV",
                "Вионье",
                2019,
                13.7,
                1,
                sparkling = false,
                blend = false,
                smallImg = "R.drawable.bottle",
                bigImg = "R.drawable.bottle",
                bottleImage = "R.drawable.bottle",
                bottleCard = "R.drawable.bottle",
                backImage = "R.drawable.bottle",
                foodCombines = arrayListOf("3", "11", "9")
            )
        )
        items.add(
            Bottle(
                "-ME454GnPOI5OhtOyDuT",
                "Мускат",
                2019,
                14.0,
                1,
                sparkling = false,
                blend = false,
                smallImg = "R.drawable.bottle",
                bigImg = "R.drawable.bottle",
                bottleImage = "R.drawable.bottle",
                bottleCard = "R.drawable.bottle",
                backImage = "R.drawable.bottle",
                foodCombines = arrayListOf("11", "8", "13", "14")
            )
        )
        items.add(
            Bottle(
                "-ME454GnPOI5OhtOyDuU",
                "Бленд", 2019, 13.7, 0,
                sparkling = false,
                blend = true,
                smallImg = "R.drawable.bottle",
                bigImg = "R.drawable.bottle",
                bottleImage = "R.drawable.bottle",
                bottleCard = "R.drawable.bottle",
                backImage = "R.drawable.bottle",
                foodCombines = arrayListOf("7", "5", "1")
            )
        )
        items.add(
            Bottle(
                "-ME45GaXoLs8RAP9_0aM",
                "Пти Мансен",
                2019,
                13.5,
                1,
                sparkling = false,
                blend = false,
                smallImg = "R.drawable.bottle",
                bigImg = "R.drawable.bottle",
                bottleImage = "R.drawable.bottle",
                bottleCard = "R.drawable.bottle",
                backImage = "R.drawable.bottle",
                foodCombines = arrayListOf("10", "6", "12")
            )
        )
    }
}
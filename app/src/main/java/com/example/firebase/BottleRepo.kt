package com.example.firebase

import com.example.firebase.models.Bottle


object BottleRepo {
    var items = arrayListOf<Bottle>()

    init {

//        items.add(Bottle("Рислинг", 2019, 13.5,1, false,
////            false, 1, 2, listOf<Int>(4,2,3)))
////        items.add(Bottle("Вионье", 2019, 13.7,1, false,
////            false, 1, 2, listOf<Int>(3,11,9)))
////        items.add(Bottle("Мускат", 2019, 14.0,1, false,
////            false, 1, 2, listOf<Int>(11,8,13,14)))
////        items.add(Bottle("Бленд", 2019, 13.7,0, false,
////            true, 1, 2, listOf<Int>(7,5,1)))

        items.add(
            Bottle(
                "1",
                "Рислинг",
                "13.5,1",
                "URL"
            )
        )
        items.add(
            Bottle(
                "2",
                "Вионье",
                "13.5,1",
                "URL"
            )
        )
        items.add(
            Bottle(
                "3",
                "Мускат",
                "13.5,1",
                "URL"
            )
        )
        items.add(
            Bottle(
                "4",
                "Бленд",
                "13.5,1",
                "URL"
            )
        )


    }
}
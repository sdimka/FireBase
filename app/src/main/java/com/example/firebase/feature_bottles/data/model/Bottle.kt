package com.example.firebase.feature_bottles.data.model

import com.example.firebase.BottleJson
import com.example.firebase.R

//class Bottle( var id: String? = null,
//    var name: String? = null, var descripton: String? = null, var img: String? = null)

class Bottle(var refID: String? = null,
             var name: String? = null, var year: Int? = null,
             var alko: Float? = null, var color: Int? = null,
             var place: String? = null, var grapeSort: String? = null,
             var servingTemp: String? = null,
             var ageYear: Int? = null,
             var degustation: String? = null,
             var vineyard: String? = null,
             var production: String? = null,
             var sparkling: Boolean? = null, var blend: Boolean? = null,
             var smallImg: String? = null, var bigImg: String? = null,
             var bottleImage: String? = null, var bottleCard: String? = null,
             var backImage: String? = null,
             var foodCombines: ArrayList<String>? = null,
             var foodCombText: String? = null,
             var origin: Int? = null) {




// Null default values create a no-argument default constructor, which is needed
// for deserialization from a DataSnapshot.


//             val alko: Number, val color: Int,
//             val sparkling: Boolean, val blend: Boolean,
//             val smallImg: Int, val bigImg: Int,
//             val foodCombines: List<Int> )

//                 init {
//                     img = "SomeURL"
//                 }

    constructor(bottleJson: BottleJson?):
//            this(bottleJson!!.id, bottleJson.name, bottleJson.description, bottleJson.img)
    this("-ME454GXYBOwGVo66qUX",
    "Рислинг",
    2019,
    13.5F,
    1,
    sparkling = false,
    blend = false,
    smallImg = "R.drawable.bottle",
    bigImg = "R.drawable.bottle",
    bottleImage = "R.drawable.bottle",
    bottleCard = "R.drawable.bottle",
    backImage = "R.drawable.bottle",
    foodCombines = arrayListOf<String>("4", "2", "3")
    )
    {
    }
}
package com.example.firebase

class Bottle( var id: Int,
    var name: String, var descripton: String)
//             val alko: Number, val color: Int,
//             val sparkling: Boolean, val blend: Boolean,
//             val smallImg: Int, val bigImg: Int,
//             val foodCombines: List<Int> )
             {
    constructor(bottleJson: BottleJson?):
            this(bottleJson!!.id, bottleJson.name, bottleJson.description) {

    }
}
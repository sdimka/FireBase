package com.example.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.fragments.BottlesFragment
import com.example.firebase.fragments.FridgeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    val a = R.string.APP_KEY_2
    val b = BuildConfig.FOO

    var bottleList = BottleRepo.items

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bottle.setOnClickListener(){
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainFrameContainer, BottlesFragment(), "BottlesFragment" )
                .commit()
        }

        main_fridge.setOnClickListener(){
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainFrameContainer, FridgeFragment(), "FridgeFragment")
                .commit()
        }
    }
}

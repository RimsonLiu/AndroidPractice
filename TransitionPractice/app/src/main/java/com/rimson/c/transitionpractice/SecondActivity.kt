package com.rimson.c.transitionpractice

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window

class SecondActivity : AppCompatActivity() {
    var flag: Int? = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        flag = intent.extras?.getInt("flag")
        when (flag) {
            1 -> window.enterTransition = Explode()
            2 -> window.enterTransition = Slide(Gravity.BOTTOM)
            3 -> window.enterTransition = Fade()
        }
        setContentView(R.layout.activity_second)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDestroy() {
        super.onDestroy()
        flag = intent.extras?.getInt("flag")
        when (flag) {
            1 -> window.enterTransition = Explode()
            2 -> window.enterTransition = Slide(Gravity.BOTTOM)
            3 -> window.enterTransition = Fade()
        }
    }
}

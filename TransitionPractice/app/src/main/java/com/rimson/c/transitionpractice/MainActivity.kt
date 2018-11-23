package com.rimson.c.transitionpractice

import android.annotation.TargetApi
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mIntent: Intent = Intent()

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.slideBtn -> {
                mIntent.putExtra("flag", 2)
                startActivity(mIntent,
                        ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle())
            }
            R.id.fadeBtn -> {
                mIntent.putExtra("flag", 3)
                startActivity(mIntent,
                        ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mIntent.setClass(this@MainActivity, SecondActivity::class.java)

        toolbar.setLogo(R.mipmap.ic_launcher_round)
        toolbar.title = "title"
        toolbar.subtitle = "subtitle"
        setSupportActionBar(toolbar)

        normalBtn.setOnClickListener {
            mIntent.putExtra("flag", 0)
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity)
                    .toBundle())
        }

        explodeBtn.setOnClickListener {
            mIntent.putExtra("flag", 1)
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity)
                    .toBundle())
        }

        shareBtn.setOnClickListener {
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity
                    , Pair.create(red_button as View, "red")
                    , Pair.create(green_button as View, "green")).toBundle())
        }
        slideBtn.setOnClickListener(this)
        fadeBtn.setOnClickListener(this)
    }
}
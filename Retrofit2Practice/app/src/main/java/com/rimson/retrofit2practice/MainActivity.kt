package com.rimson.retrofit2practice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rimson.retrofit2practice.retrofit.GetRequestActivity
import com.rimson.retrofit2practice.retrofit.PostRequestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        get_request.setOnClickListener {
            val intent = Intent(this, GetRequestActivity::class.java)
            startActivity(intent)
        }
        post_request.setOnClickListener {
            val intent = Intent(this, PostRequestActivity::class.java)
            startActivity(intent)
        }
    }
}

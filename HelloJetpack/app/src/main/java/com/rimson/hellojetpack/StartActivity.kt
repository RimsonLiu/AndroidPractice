package com.rimson.hellojetpack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.start).navigateUp()

}

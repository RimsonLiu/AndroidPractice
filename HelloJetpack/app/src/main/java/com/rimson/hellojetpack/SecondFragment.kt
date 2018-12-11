package com.rimson.hellojetpack

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("SecondFragment: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondFragment: ", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("SecondFragment: ", "onCreateView")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("SecondFragment: ", "onActivityCreated")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("SecondFragment: ", "onViewCreated")
        val message = arguments?.getString("message")
        textview_second_message.text = message
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondFragment: ", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondFragment: ", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondFragment: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondFragment: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SecondFragment: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondFragment: ", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("SecondFragment: ", "onDetach")
    }


}
package com.rimson.retrofit2practice.translation

import android.util.Log

class Translation {

    private var status = 0
    private lateinit var content: Content

    companion object {
        private class Content {
            lateinit var from: String
            lateinit var to: String
            lateinit var vendor: String
            lateinit var out: String
            var errNo = 0
        }
    }

    // 定义输出返回数据的方法
    fun show() {
        Log.d("hehe", "Translation By Get : " + "\n" +
                content.from + "\n" +
                content.to + "\n" +
                content.vendor + "\n" +
                content.out + "\n" +
                content.errNo)
    }
}
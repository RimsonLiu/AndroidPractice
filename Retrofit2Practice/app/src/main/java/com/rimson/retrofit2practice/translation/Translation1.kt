package com.rimson.retrofit2practice.translation

class Translation1 {

    private lateinit var type: String
    private var errorCode = 0
    private var elapsedTime = 0
    lateinit var translateResult: List<TranslateResultBean>

    companion object {
        class TranslateResultBean {
            lateinit var src: String
            lateinit var tgt: String
        }
    }
}
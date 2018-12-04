package com.rimson.mvpsample.contract

interface BaseView<Any> {
    fun setPresenter(presenter: Any)
}
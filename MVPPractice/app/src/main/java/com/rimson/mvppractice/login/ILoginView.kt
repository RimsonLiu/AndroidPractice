package com.rimson.mvppractice.login

interface ILoginView {
    fun showSuccessMessage(msg: String)

    fun showFailMessage(msg: String)

    fun showProgressBar()
}
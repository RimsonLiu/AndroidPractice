package com.rimson.mvppractice.login

interface ILoginPresenter {
    fun login(username: String, password: String): Boolean

    fun loginSuccess(msg: String)

    fun loginFail(msg: String)

    fun showProgressDialog()
}
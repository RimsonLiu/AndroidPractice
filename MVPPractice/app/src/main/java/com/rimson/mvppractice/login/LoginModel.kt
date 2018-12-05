package com.rimson.mvppractice.login

class LoginModel(private var loginPresenter: ILoginPresenter) : ILoginModel {

    override fun login(username: String, password: String): Boolean {
        return username == "liu" && password == "123"
    }

}
package com.rimson.mvppractice.login

class LoginPresenter(private val loginView: ILoginView) : ILoginPresenter {

    private val loginModel: ILoginModel = LoginModel(this)

    override fun login(username: String, password: String): Boolean {
        return loginModel.login(username, password)
    }

    override fun loginSuccess(msg: String) {
        loginView.showSuccessMessage(msg)
    }

    override fun loginFail(msg: String) {
        loginView.showFailMessage(msg)
    }

    override fun showProgressDialog() {
        loginView.showProgressBar()
    }
}
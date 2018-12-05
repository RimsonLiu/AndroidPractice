package com.rimson.mvppractice.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rimson.mvppractice.R
import com.rimson.mvppractice.next.NextActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {

    private lateinit var loginPresenter: ILoginPresenter
    private lateinit var loginModel: ILoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        loginPresenter = LoginPresenter(this)
        loginModel = LoginModel(loginPresenter)

        login_button.setOnClickListener {
            showProgressBar()
            loginPresenter.showProgressDialog()
            if (loginModel.login(et_username.text.toString(), et_password.text.toString())) {
                loginPresenter.loginSuccess("登录成功")
                val intent = Intent(this, NextActivity::class.java)
                startActivity(intent)
            } else {
                loginPresenter.loginFail("账号或密码错误")
            }
        }
    }

    override fun showProgressBar() {

    }

    override fun showSuccessMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showFailMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}

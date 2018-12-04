package com.rimson.mvpsample.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rimson.mvpsample.R
import com.rimson.mvpsample.contract.UserInfoContract
import com.rimson.mvpsample.model.UserInfoModel
import com.rimson.mvpsample.presenter.UserInfoPresenter
import kotlinx.android.synthetic.main.activity_userinfo.*

class UserInfoActivity : AppCompatActivity(), UserInfoContract.View {
    private lateinit var presenter: UserInfoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userinfo)
        UserInfoPresenter(this)
        presenter.start()
    }

    override fun showLoading() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show()
    }

    override fun showUserInfo(userInfoModel: UserInfoModel) {
        tv_name.text = userInfoModel.name
        tv_age.text = userInfoModel.age.toString()
        tv_address.text = userInfoModel.address
    }

    override fun loadUserId(): String {
        return "10086"
    }

    override fun setPresenter(presenter: UserInfoContract.Presenter) {
        this.presenter = presenter
    }


}

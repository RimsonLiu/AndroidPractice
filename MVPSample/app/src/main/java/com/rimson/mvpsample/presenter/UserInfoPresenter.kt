package com.rimson.mvpsample.presenter

import android.os.Handler
import com.rimson.mvpsample.contract.UserInfoContract
import com.rimson.mvpsample.model.UserInfoModel

class UserInfoPresenter(private var view: UserInfoContract.View) : UserInfoContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadUserInfo()
    }

    override fun loadUserInfo() {
        val userId = view.loadUserId()
        view.showLoading()
        Handler().postDelayed({
            run {
                val userInfoModel = UserInfoModel()
                view.showUserInfo(userInfoModel)
                view.dismissLoading()
            }
        }, 3000)
    }

}
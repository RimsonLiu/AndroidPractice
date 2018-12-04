package com.rimson.mvpsample.contract

import com.rimson.mvpsample.presenter.BasePresenter
import com.rimson.mvpsample.model.*

interface UserInfoContract {
    interface Presenter : BasePresenter {
        fun loadUserInfo()
    }

    interface View : BaseView<Presenter> {
        fun showLoading()
        fun dismissLoading()
        fun showUserInfo(userInfoModel: UserInfoModel)
        fun loadUserId(): String
    }
}
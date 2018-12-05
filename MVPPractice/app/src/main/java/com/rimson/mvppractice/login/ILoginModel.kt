package com.rimson.mvppractice.login

interface ILoginModel {
    fun login(username: String, password: String): Boolean
}
package com.mazouri.mvp.presenter

/**
 * Created by wangdongdong on 17-5-23.
 */
interface IUserPresenter {
    fun saveUser(id: Int, username: String, age: Int)
    fun loadUser(id: Int)
}
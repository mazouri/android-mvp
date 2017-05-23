package com.mazouri.mvp.view

/**
 * Created by wangdongdong on 17-5-23.
 */
interface IUserView {

    fun getID(): Int

    fun getUsername(): String

    fun getAge(): Int

    fun setUsername(username: String)

    fun setAge(age: Int)

    fun onSaveSuccess()
}
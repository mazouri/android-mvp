package com.mazouri.mvp.model

import com.mazouri.mvp.data.User

/**
 * 创建一个接口
 *
 * Kotlin知识点(interfaces):http://kotlinlang.org/docs/reference/interfaces.html
 *
 * Created by wangdongdong on 17-5-23.
 */
interface IUserModel {
    fun setId(id: Int)
    fun setUsername(username: String)
    fun setAge(age: Int)
    fun save()
    fun load(id: Int): User
}
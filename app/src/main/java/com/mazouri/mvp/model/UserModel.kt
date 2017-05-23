package com.mazouri.mvp.model

import com.mazouri.mvp.data.User
import android.util.SparseArray



/**
 * IUserModel的实现类
 *
 * Created by wangdongdong on 17-5-23.
 */
class UserModel: IUserModel {

    private var mId: Int = 0
    private var mUsername: String = ""
    private var mAge: Int = 0
        private val mUserArray = SparseArray<User>()

    override fun setId(id: Int) {
        mId = id
    }

    override fun setUsername(username: String) {
        mUsername = username
    }

    override fun setAge(age: Int) {
        mAge = age
    }

    override fun save() {
        mUserArray.append(mId, User(mUsername, mAge))
    }

    override fun load(id: Int): User {
        mId = id
        return mUserArray.get(mId, User("not found", 0))
    }
}
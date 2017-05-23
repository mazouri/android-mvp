package com.mazouri.mvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mazouri.mvp.presenter.UserPresenter
import com.mazouri.mvp.view.IUserView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IUserView, View.OnClickListener {

    private var mUserPresenter: UserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserPresenter = UserPresenter(this)
        saveButton.setOnClickListener(this)
        loadButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.saveButton -> mUserPresenter?.saveUser(getID(), getUsername(), getAge())
            R.id.loadButton -> mUserPresenter?.loadUser(getID())
        }
    }

    override fun onSaveSuccess() {
        edt_id.setText("")
        edt_username.setText("")
        edt_age.setText("")
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show()
    }

    override fun getID(): Int {
        val id = edt_id.text.toString().trim()
        if (id.isNotEmpty())
            return id.toInt()
        else
            return 0
    }

    override fun getUsername(): String = edt_username.text.toString()

    override fun getAge(): Int {
        val age = edt_age.text.toString().trim()
        if (age.isNotEmpty())
            return age.toInt()
        else
            return 0
    }

    override fun setUsername(username: String) {
//        edt_username.text = Editable.Factory().newEditable(username)
        Log.d("test_log", "setUsername:$username")
        edt_username.text = toEditable(username)
    }

    override fun setAge(age: Int) {
        Log.d("test_log", "setUsername:$age")
        edt_age.text = toEditable(age.toString())
    }
}

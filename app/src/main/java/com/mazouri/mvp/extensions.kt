package com.mazouri.mvp

import android.text.Editable

/**
 * Created by wangdongdong on 17-5-23.
 */
fun toEditable(str: String): Editable {
    return Editable.Factory().newEditable(str)
}

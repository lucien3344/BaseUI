package com.example.teachtv.util

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.WindowManager


/**
 * Created by Administrator on 2017/12/15.
 */
class MyDailog(context: Context, themeResId: Int ) : AlertDialog(context, themeResId) {

    override fun show() {
        super.show()
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.BOTTOM
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        window!!.decorView.setPadding(0, 0, 0, 0)
        window!!.attributes = layoutParams
    }

}
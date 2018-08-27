package com.example.teachtv.util

import android.app.Activity


/**
 * Created by Administrator on 2018/1/29.
 */
open class ActivitysUtils constructor() {

    companion object {
        fun get(): ActivitysUtils = Instance.activitysUtils
    }

    object Instance {
        val activitysUtils = ActivitysUtils()
    }

    var oList: ArrayList<Activity> = ArrayList()
    open fun getActivityList(): ArrayList<Activity> = oList

    //销毁当个Activity方法
    open fun removeActivity(baseActivity: Activity) {
        //判断当前集合中存在该Activity

        if (null != oList) {
            if (oList.contains(baseActivity)) {
                oList.remove(baseActivity)//从集合中移除
                baseActivity.finish()//销毁当前Activity
            }
        }

    }

    //销毁当个Activity方法
    open fun removeAll() {
        //判断当前集合中存在该Activity
        if (oList.size > 0) {
            for (activity in oList) {
                activity.finish()
            }
        }

    }

    /**
     * 添加Activity
     */
    open fun addActivity_(activity: Activity) {
        // 判断当前集合中不存在该Activity
        if (null != oList) {
            if (!oList.contains(activity)) {
                oList.add(activity)//把当前Activity添加到集合中
            }
        }

    }
}
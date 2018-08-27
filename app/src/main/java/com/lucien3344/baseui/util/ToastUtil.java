package com.lucien3344.baseui.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * description: Toast简便版
 */
public class ToastUtil {

    public static void makeText(Context context, int s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void makeText(Context context, String s) {
        if (!TextUtils.isEmpty(s)) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeTextLong(Context context, int s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    public static void makeTextLong(Context context, String s) {
        if (!TextUtils.isEmpty(s)) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }
    }
}

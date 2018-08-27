package com.lucien3344.baseui.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:
 * Author: winter110
 * Date: 2017/9/6 0006 14:57
 */

public class StringUtil {

    /**
     * 替换英文中的中文符号
     *
     * @param str
     * @return
     */
    public static String filterChinese(String str) {
        str = str.replace("’", "'")
                .replace("。", ".")
                .replace("，", ",")
                .replace("\\n", "\n")
                .replace("：", ":")
                .replace("？", "?");
        return str;
    }


    /**
     * 从asset路径下读取对应文件转String输出
     *
     * @param mContext
     * @return
     */
    public static String getAssetsJson(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }
}

package com.slut.simplepass.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.slut.simplepass.App;

import static java.lang.String.valueOf;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public class ToastUtils {

    /**
     * toast显示文本信息
     *
     * @param text
     */
    public static void showShort(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(int id) {
        String text = ResUtils.getString(id);
        showShort(text);
    }

    /**
     * toast显示对象信息
     *
     * @param obj
     */
    public static void showShort(Object obj) {
        if (obj == null) {
            return;
        }
        String text = String.valueOf(obj);
        if (TextUtils.isEmpty(text)) {
            String toStr = obj.toString();
            showShort(toStr);
        } else {
            showShort(text);
        }
    }

}

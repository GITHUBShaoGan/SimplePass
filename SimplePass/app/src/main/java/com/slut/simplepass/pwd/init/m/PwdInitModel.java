package com.slut.simplepass.pwd.init.m;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public interface PwdInitModel {

    interface OnPwdInitCheckListener {
        void onCheckEmpty();

        void onCheckNotEmpty();
    }

    void check(OnPwdInitCheckListener onPwdInitCheckListener);

    interface OnPwdInitListener {

        void onSuccess();

        void onError(String msg);

    }

    void initPwd(String pwd, String confirm, String reservation, OnPwdInitListener onPwdInitListener);

}

package com.slut.simplepass.pwd.init.m;

import android.text.TextUtils;

import com.slut.simplepass.App;
import com.slut.simplepass.AuthInfo;
import com.slut.simplepass.R;
import com.slut.simplepass.utils.ResUtils;
import com.slut.simplepass.utils.StringUtils;

import java.util.List;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public class PwdInitModelImpl implements PwdInitModel {

    @Override
    public void check(OnPwdInitCheckListener onPwdInitCheckListener) {
        List list = App.getDaoSession().getAuthInfoDao().loadAll();
        if (list == null || list.isEmpty()) {
            //不存在密码，删除所有数据
            onPwdInitCheckListener.onCheckEmpty();
        } else {
            onPwdInitCheckListener.onCheckNotEmpty();
        }
    }

    @Override
    public void initPwd(String pwd, String confirm, String reservation, OnPwdInitListener onPwdInitListener) {
        //校验是否为空
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirm) || TextUtils.isEmpty(reservation)) {
            onPwdInitListener.onError(ResUtils.getString(R.string.error_pwd_init_null));
            return;
        }
        //校验密码长度
        if (pwd.length() < 4 || pwd.length() > 128) {
            onPwdInitListener.onError(ResUtils.getString(R.string.error_pwd_init_illegallen));
            return;
        }
        if (reservation.length() == 0 || reservation.length() > 20) {
            onPwdInitListener.onError(ResUtils.getString(R.string.error_pwd_init_illegallen_reser));
            return;
        }
        //校验两次输入是否相等
        if (!TextUtils.equals(pwd, confirm)) {
            onPwdInitListener.onError(ResUtils.getString(R.string.error_pwd_init_notequal));
            return;
        }
        List list = App.getDaoSession().getAuthInfoDao().loadAll();
        if (list == null || list.isEmpty()) {
            //代表数据库中没有记录，可以执行插入操作
            AuthInfo authInfo = new AuthInfo(null, StringUtils.MD5(pwd), StringUtils.MD5(reservation), System.currentTimeMillis(), System.currentTimeMillis());
            App.getDaoSession().getAuthInfoDao().insert(authInfo);
            onPwdInitListener.onSuccess();
        } else {
            //代表数据库中已经有密码了
            onPwdInitListener.onError(ResUtils.getString(R.string.error_pwd_init_exists));
            return;
        }
    }

}

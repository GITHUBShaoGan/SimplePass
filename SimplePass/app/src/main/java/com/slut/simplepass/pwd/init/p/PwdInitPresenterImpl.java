package com.slut.simplepass.pwd.init.p;

import com.slut.simplepass.pwd.init.m.PwdInitModel;
import com.slut.simplepass.pwd.init.m.PwdInitModelImpl;
import com.slut.simplepass.pwd.init.v.PwdInitView;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public class PwdInitPresenterImpl implements PwdInitPresenter, PwdInitModel.OnPwdInitListener, PwdInitModel.OnPwdInitCheckListener {

    private PwdInitModel pwdInitModel;
    private PwdInitView pwdInitView;

    public PwdInitPresenterImpl(PwdInitView pwdInitView) {
        this.pwdInitView = pwdInitView;
        this.pwdInitModel = new PwdInitModelImpl();
    }

    @Override
    public void onSuccess() {
        pwdInitView.onPwdInitSuccess();
    }

    @Override
    public void onError(String msg) {
        pwdInitView.onPwdInitError(msg);
    }

    @Override
    public void initPwd(String pwd, String confirm, String reservation) {
        pwdInitModel.initPwd(pwd, confirm, reservation, this);
    }

    @Override
    public void check() {
        pwdInitModel.check(this);
    }

    @Override
    public void onCheckEmpty() {
        pwdInitView.onCheckEmpty();
    }

    @Override
    public void onCheckNotEmpty() {
        pwdInitView.onCheckNotEmpty();
    }
}

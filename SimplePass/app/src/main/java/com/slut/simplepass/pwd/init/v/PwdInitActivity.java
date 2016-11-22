package com.slut.simplepass.pwd.init.v;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.slut.simplepass.App;
import com.slut.simplepass.R;
import com.slut.simplepass.main.v.MainActivity;
import com.slut.simplepass.pwd.init.p.PwdInitPresenter;
import com.slut.simplepass.pwd.init.p.PwdInitPresenterImpl;
import com.slut.simplepass.pwd.validate.v.PwdCheckActivity;
import com.slut.simplepass.utils.ResUtils;
import com.slut.simplepass.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdInitActivity extends AppCompatActivity implements PwdInitView {

    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.til_confirm)
    TextInputLayout tilConfirm;
    @BindView(R.id.til_reservation)
    TextInputLayout tilReservaion;

    private PwdInitPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new PwdInitPresenterImpl(this);
        presenter.check();

        setContentView(R.layout.activity_pwd_init);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit)
    void onSubmitClick() {
        String pwd = tilPwd.getEditText().getText().toString().trim();
        String confirm = tilConfirm.getEditText().getText().toString().trim();
        String reservation = tilReservaion.getEditText().getText().toString().trim();
        presenter.initPwd(pwd, confirm, reservation);
    }

    @Override
    public void onPwdInitSuccess() {
        ToastUtils.showShort(R.string.success_pwd_init);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onPwdInitError(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onCheckEmpty() {
        //密码不存在
    }

    @Override
    public void onCheckNotEmpty() {
        //密码存在
        startActivity(new Intent(this, PwdCheckActivity.class));
        finish();
    }
}

package com.example.rxjavaretrofitmvp.module.register;

import android.support.design.widget.TextInputLayout;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.rxjavaretrofitmvp.R;
import com.example.rxjavaretrofitmvp.base.BaseActivity;
import com.example.rxjavaretrofitmvp.base.BaseBean;
import com.example.rxjavaretrofitmvp.bean.User;
import com.example.rxjavaretrofitmvp.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegiSterActivity extends BaseActivity<RegisterPresenter> implements IRegiSterView {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.et_repassword)
    EditText mEtRepassword;
    @BindView(R.id.til_repassword)
    TextInputLayout mTilRepassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        TextWatcher textWatcher = new RegisterTextWatcher(mTilUsername, mTilPassword, mTilRepassword);
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
        mEtRepassword.addTextChangedListener(textWatcher);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void showRegisterSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showRegisterFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void doSuccess(BaseBean<User> user) {

    }


    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String rePassword = mEtRepassword.getText().toString().trim();
        int tilUsernameCounterMaxLength = mTilUsername.getCounterMaxLength();
        int tilPasswordCounterMaxLength = mTilPassword.getCounterMaxLength();
        int tilRePasswordCounterMaxLength = mTilRepassword.getCounterMaxLength();
        presenter.register(username, password, rePassword, tilUsernameCounterMaxLength, tilPasswordCounterMaxLength, tilRePasswordCounterMaxLength);

    }
}

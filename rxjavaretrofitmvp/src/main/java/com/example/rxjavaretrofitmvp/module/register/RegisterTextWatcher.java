package com.example.rxjavaretrofitmvp.module.register;


import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;


/**
 * created by xucanyou666
 * on 2020/2/8 14:07
 * email：913710642@qq.com
 */
class RegisterTextWatcher implements TextWatcher {
    TextInputLayout mTilUsername;
    TextInputLayout mTilPassword;
    TextInputLayout mTilRepassword;

    RegisterTextWatcher(TextInputLayout username, TextInputLayout password, TextInputLayout rePassword) {
        mTilUsername = username;
        mTilPassword = password;
        mTilRepassword = rePassword;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}

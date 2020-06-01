package com.example.androidmvpdemo.Login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmvpdemo.Login.contract.ILoginContract;
import com.example.androidmvpdemo.Login.presenter.LoginPresenter;
import com.example.androidmvpdemo.R;
// 参考链接 https://www.jianshu.com/p/ae0b21d3238a
public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {
    private EditText username;
    private EditText password;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(v -> validateCredentials());
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    private void validateCredentials(){
        mLoginPresenter.doLogin(username.getText().toString(), password.getText().toString());
    }
}

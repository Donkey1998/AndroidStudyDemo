package com.example.androidmvpdemo.Login.contract;

public interface ILoginContract {
    interface ILoginView {

        //用户名错误提示
        void setUsernameError();
        //密码错误提示
        void setPasswordError();
        //跳转到主页面
        void navigateToHome();
    }

    interface ILoginPresenter {
        void doLogin(String username, String password);
        void onDestroy();
    }

}

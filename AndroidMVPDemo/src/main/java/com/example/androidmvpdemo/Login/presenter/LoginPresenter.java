package com.example.androidmvpdemo.Login.presenter;

import android.os.Handler;
import com.example.androidmvpdemo.Login.contract.ILoginContract;
import com.example.androidmvpdemo.Login.model.LoginModel;




public class LoginPresenter implements ILoginContract.ILoginPresenter {
    private ILoginContract.ILoginView ILoginView;

    public LoginPresenter(ILoginContract.ILoginView ILoginView){
        this.ILoginView = ILoginView;
    }


    @Override
    public void doLogin(String username, String password) {
        LoginModel model = new LoginModel();
        int code = model.login(username,password);
        if (code == -2){
            ILoginView.setUsernameError();
        }else if(code == -1){
            ILoginView.setPasswordError();
        }else {
            ILoginView.navigateToHome();
        }
    }

    @Override
    public void onDestroy() {
        ILoginView = null;
    }

}

package com.example.rxjavaretrofitmvp.module.register;

import com.example.rxjavaretrofitmvp.base.BaseBean;
import com.example.rxjavaretrofitmvp.base.BaseView;
import com.example.rxjavaretrofitmvp.bean.User;

public interface IRegiSterView extends BaseView {
    /**
     * 显示注册成功
     *
     * @param successMessage
     */
    void showRegisterSuccess(String successMessage);

    /**
     * 显示注册失败
     *
     * @param errorMessage
     */
    void showRegisterFailed(String errorMessage);

    /**
     * 注册成功
     *
     * @param user
     */
    void doSuccess(BaseBean<User> user);
}

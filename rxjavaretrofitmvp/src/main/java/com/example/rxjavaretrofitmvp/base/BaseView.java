package com.example.rxjavaretrofitmvp.base;


public interface BaseView {
    void showLoading();

    void hideLoading();

    void onErrorCode(BaseBean bean);
}

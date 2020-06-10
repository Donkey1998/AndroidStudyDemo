package com.example.rxjavaretrofitmvp.base;

import com.example.rxjavaretrofitmvp.http.API;
import com.example.rxjavaretrofitmvp.http.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter <V extends BaseView> {
    public V baseView;
    private CompositeDisposable compositeDisposable;

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 返回 view
     */
    public V getBaseView() {
        return baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    private void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable
                .add(observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));
    }

}

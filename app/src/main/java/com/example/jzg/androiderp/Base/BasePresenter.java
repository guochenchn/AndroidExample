package com.example.jzg.androiderp.Base;

import com.example.jzg.androiderp.app.AppContext;
import com.example.jzg.androiderp.utils.SubmitValueVerification;

import java.util.Map;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }


    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    /**
     * 用于网络提交参数之前检查view绑定、网络、提交参数
     * （排除筛选相关或者传递的多个参数其中组合型的和null的参数，否则校验不会通过）
     *
     * @param params 提交参数map
     * @return
     */
    public boolean checkSubmitInfo(Map<String, String> params) {
        checkViewAttached();
        if (!AppContext.isNetWork) {
            mMvpView.dismissDialog();
            mMvpView.showError("连接网络失败,请检查网络.");
            return false;
        }
        if (SubmitValueVerification.isEmpty(params)) {
            mMvpView.dismissDialog();
            mMvpView.showError("参数传递错误,请检查参数.");
            return false;
        }
        return true;
    }

    public boolean checkSubmitInfoWithNoDialog(Map<String, String> params) {
        checkViewAttached();
        if (!AppContext.isNetWork) {
            mMvpView.dismissDialog();
            mMvpView.showError("连接网络失败,请检查网络.");
            return false;
        }
        if (SubmitValueVerification.isEmpty(params)) {
            mMvpView.dismissDialog();
            mMvpView.showError("参数传递错误,请检查参数.");
            return false;
        }
        return true;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}


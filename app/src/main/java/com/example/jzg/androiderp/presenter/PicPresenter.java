package com.example.jzg.androiderp.presenter;/**
 * author: gcc
 * date: 2016/6/13 15:55
 * email:
 */

import com.example.jzg.androiderp.Base.BasePresenter;
import com.example.jzg.androiderp.Interface.PicInterface;
import com.example.jzg.androiderp.vo.Data;
import com.example.jzg.androiderp.app.AppContext;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author: guochen
 * date: 2016/6/13 15:55
 * email: 
 */
public class PicPresenter extends BasePresenter<PicInterface> {
    private DataManager dataManager;
    private PicInterface mView;
    private Subscription mSubscription;

    public PicPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(PicInterface mvpView) {
        super.attachView(mvpView);
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadPic() {
        if (!AppContext.isNetWork) return;
        mSubscription = dataManager.loadPic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<List<Data>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e!=null&& mView!=null) {
                    mView.showError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<Data> datas) {
                //mView.showDatas(taskViewData);
                mView.showDatas(datas);
            }
        });
    }
    public void loadMorePic() {
        if (!AppContext.isNetWork) return;
        mSubscription = dataManager.loadPic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<List<Data>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e!=null&& mView!=null) {
                    mView.showError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<Data> datas) {
                //mView.showDatas(taskViewData);
                mView.showMoreDatas(datas);
            }
        });
    }
    public void loadrefreshPic() {
        if (!AppContext.isNetWork) return;
        mSubscription = dataManager.loadPic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<List<Data>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e!=null&& mView!=null) {
                    mView.showError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<Data> datas) {
                //mView.showDatas(taskViewData);
                mView.showRefreshDatas(datas);
            }
        });
    }
}

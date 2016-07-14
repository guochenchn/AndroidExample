package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * author: guochen
 * date: 2016/6/21 11:20
 * email: 
 */
public class RxJavaActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublishSubject();
    }

    private void PublishSubject() {
      final PublishSubject<Boolean> publishSubject =   PublishSubject.create();
        publishSubject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });

        rx.Observable.create(new rx.Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Logger.e(subscriber.isUnsubscribed() + "onNext前"); //没有绑定就返回true,绑定就返回false
                subscriber.onNext("onNext执行了");
                 Logger.e(subscriber.isUnsubscribed() + "onNext后");
                subscriber.onCompleted();  //只有执行了这个方法后,doOnCompleted()方法才会执行
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                publishSubject.onNext(true);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.e(s);
            }
        });

    }
}

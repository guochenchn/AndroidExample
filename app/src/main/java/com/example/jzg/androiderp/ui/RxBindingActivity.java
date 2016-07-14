package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * author: guochen
 * date: 2016/7/8 16:06
 * email:
 */
public class RxBindingActivity extends BaseActivity {

    final String[] mManyWords = {"你好", "我", "是", "RxBinding"};
    final List<String> mManyWordList = Arrays.asList(mManyWords);
    @BindView(R.id.tv_text)
    TextView tvText;
    private Action1<String> mToastAction;
    private Func1<List<String>, Observable<String>> mFlatMap;
    private Action1<String> mTextAction;
    private Func2<String, String, String> mMergeStringFunc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbinding);
        ButterKnife.bind(this);
        initView();
        initDat();
        TextMap();
        TestReduce();
    }

    private void TestReduce() {
        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mFlatMap)
                .reduce(mMergeStringFunc)
                .subscribe(mTextAction);
    }

    private void TextMap() {
        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mFlatMap).subscribe(mTextAction);
    }

    private void initDat() {
        Observable<String> mObservable = Observable.just(getTextStr());
        mObservable.subscribe(mToastAction);
    }

    private void initView() {
        mToastAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(RxBindingActivity.this, s, Toast.LENGTH_SHORT).show();//吐司是abcdefg
            }
        };


        mTextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.e(s);//这里会一次打印 你好 我  是  RxBinding
                tvText.setText(s);
            }
        };



        mFlatMap = new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {

                return Observable.from(strings); // 映射字符串
            }
        };


        // 空格连接字符串
        mMergeStringFunc = new Func2<String, String, String>() {
            @Override public String call(String s, String s2) {
                Logger.e("第一个str"+s+"--"+"第二个str"+s2);//会依次把发射出的单个字符串拼接起来.第一次s=你好.s2=我,第二次,s=你好 我,s2=是
                return String.format("%s %s", s, s2); // 空格连接字符串
            }
        };
    }

    private String getTextStr() {

        return "abcdefg";
    }
}

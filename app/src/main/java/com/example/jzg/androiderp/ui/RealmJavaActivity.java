package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.Person;
import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * author: guochen
 * date: 2016/7/25 15:37
 * email:
 */
public class RealmJavaActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;
    private TreeMap<String, String> testPersons = new TreeMap<>();
    private Realm realm;
    private Subscription subscribe;
    private Realm realm1;
    private Realm realm2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
//        initData();
//        initView();
        initData1();
//        initData2();
    }

    private void initData1() {

        realm2 = Realm.getDefaultInstance();
        Person person = new Person();
//        Person person = realm2.createObject(Person.class);


        person.setName("姚明");
        person.setUserName("yaoming");
        person.setAge(1);
        realm2.beginTransaction();
        realm2.commitTransaction();
        // 查找Dog数据库中字段age小于2的所有数据
        RealmResults<Person> puppies = realm2.where(Person.class).lessThan("age", 2).findAll();
        puppies.size(); // => 此时还没有插入存储，因此为0

// 保存这个数据

//        realm2.commitTransaction();

// 查询是自动同步更新的

        Person person1 = realm2.copyToRealm(person);
        realm2.commitTransaction();
        RealmResults<Person> puppies1 = realm2.where(Person.class).lessThan("age", 2).findAll();
        puppies1.size(); // => 1
    }

    private void initData2() {
        //异步查询和更新数据
        realm2.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 开始查询和修改数据
                Person person = realm.where(Person.class).equalTo("name", "科比").findFirst();
                person.setAge(42);
                //子线程
                Logger.e("executeTransactionAsync"+person.toString());

            }
        }, new Realm.Transaction.OnSuccess() {
            // 原始的查询数据会自动更新
            @Override
            public void onSuccess() {
                //如果你之前得到Person了这里就不用在查询得到了
              Person person =   realm1.where(Person.class).equalTo("name","科比").findFirst();
                RealmResults<Person> persons = realm2.where(Person.class).equalTo("name", "科比").findAll();
                //主线程
                Logger.e( "onSuccess"+person.toString());
            }
        } );
    }

    private void initView() {
        //因为initData()的时候关闭了realm,所以这里要重新得到
        realm1 = Realm.getDefaultInstance();
        subscribe = realm1.where(Person.class).findAllAsync().asObservable()
                .flatMap(new Func1<RealmResults<Person>, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(RealmResults<Person> persons) {
                        return Observable.from(persons);
                    }
                }).zipWith(Observable.interval(5, TimeUnit.SECONDS), new Func2<Person, Long, Person>() {
                    @Override
                    public Person call(Person person, Long aLong) {
                        return person;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("错误+"+e);

                    }

                    @Override
                    public void onNext(Person person) {
                        tv.setText(person.getName());
                    }
                });

    }

    private void initData() {
        testPersons.put("科比", "科比");
        testPersons.put("詹姆斯", "詹姆斯");
        testPersons.put("杜兰特", "杜兰特");
        testPersons.put("库里", "库里");
        testPersons.put("韦德", "韦德");
        testPersons.put("诺维茨基", "诺维茨基");
        testPersons.put("欧文", "欧文");
        testPersons.put("保罗", "保罗");

        final Random r = new Random(42);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //在主线程
                for (Map.Entry<String, String> entry : testPersons.entrySet()) {
                    Person p = realm.createObject(Person.class);
                    p.setName(entry.getKey());
                    p.setUserName(entry.getValue());
                    p.setAge(r.nextInt(100));
                    Logger.e(p.toString());
                }
            }
        });
        realm.close();


    }

    @Override
    protected void onPause() {
        super.onPause();
        subscribe.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm1.close();
    }
}

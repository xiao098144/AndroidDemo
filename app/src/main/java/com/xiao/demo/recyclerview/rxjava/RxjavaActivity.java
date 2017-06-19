package com.xiao.demo.recyclerview.rxjava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";

    @BindView(R.id.rxjava_tv_result)
    TextView tv_result;

    @BindView(R.id.rxjava_btn_firstdemo)
    Button btn_firstdemo;

    @BindView(R.id.rxjava_btn_second)
    Button btn_second;

    @BindView(R.id.rxjava_btn_third)
    Button btn_multithread;

    public static void start(Context context) {
        Intent starter = new Intent(context, RxjavaActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
        btn_firstdemo.setOnClickListener(v -> firstRXDemo());

    }

    @OnClick(R.id.rxjava_btn_third)
    protected void multiThread(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.e(TAG, "call: "+Thread.currentThread().getName());
                for (int i = 0; i < 10000; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Integer o) {
                log("multiThread onNext ",String.valueOf(o));
            }
        });
    }

    /**
     * Observable - Oparator -Subscrible
     */
    @OnClick(R.id.rxjava_btn_second)
    protected void secondDemo() {
        Subscription subcription = Observable.just("添加Operator").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.e(TAG, "call: s = "+s);
                return s+" operator call";
            }
        }).subscribe(s ->log("secondDemo ",s));
        log("secondDemo: "," subcription.isUnsubscribed() = "+subcription.isUnsubscribed());
    }

    /**
     * 从Observable到Subcrible
     * 从观察者到消费者  没有消费者时观察者不生成作用
     */
    protected void firstRXDemo() {
        //  不关心消费者是否结束或者异常时 使用just.subcribe  Action1 直接执行操作
        Observable.just("Hello World RXJAVA just ").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                log("Observable.just call: ", s);
            }
        });
        // 上述对应lambda写法
        Observable.just("RXJAVA just Action1 lambda").subscribe(s -> log("firstRXDemo: ", s));

        // just 方法从1个参数到最多10个参数   1个参数时直接生成ScalarSynchronousObservable
        // 多参数时组成数组 之后循环遍历生成Observable
        Observable.just("first Item", "second Item", "third Item").subscribe(s -> log("call: just(t1,t2) ", s));

        //  正常模式  Observable  观察者 Subcribe消费者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World RXJAVA ");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log("RXJAVA onCompleted: ", null);
            }

            @Override
            public void onError(Throwable e) {
                log("RXJAVA onError: ", null);
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                log("RXJAVA onNext: ", s);
            }
        };
        observable.subscribe(subscriber);
    }

    private void log(String methodname, String value) {
        Log.e(TAG, "log: " + methodname + " " + value);
        tv_result.append("log: " + methodname + " " + value + "\n");
    }

}

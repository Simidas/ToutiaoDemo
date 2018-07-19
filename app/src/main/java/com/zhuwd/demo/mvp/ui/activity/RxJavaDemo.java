package com.zhuwd.demo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhuwd.demo.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Lenovo on 2018/7/18.
 */

public class RxJavaDemo extends AppCompatActivity{

    private static final String TAG = "RxJavaDemo";

    private Button btNetwork;
    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava_test);

        initView();
    }

    private void initView() {
        btNetwork = (Button) findViewById(R.id.bt_network);
        tvContent = (TextView) findViewById(R.id.tv_content);

        btNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                doSomething();

//                doSomethingZip();
//                doSomethingMap();
//                doSomethingFlatMap();
                doSomethingFilter();


            }
        });

    }

    /**
     * filter 过滤操作符
     *
     *
     */
    private void doSomethingFilter() {
        Observable.just(1,39,20,5,8,33)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer>10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,integer+"  ");
            }
        });
    }

    /**
     * flatMap ,一对多的关系
     *
     * 例如，从student学生中，获取每个学生的参与课程分别是什么（一对多关系）
     * FlatMap将一个发送事件的上游Observable变换成多个发送事件的Observables，
     * 然后将它们发射的时间合并后放进一个单独的Observable里
     * <p>
     * [需要注意]flatMap并不保证事件的顺序
     *
     * concatMap作用和flatMap几乎一模一样，唯一的区别是它能保证事件的顺序
     *
     *
     */
    private void doSomethingFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<String>();
                for (int i=0;i<3;i++){
                    list.add("---ObservableSource:"+integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG,s);
                    }
                });
    }

    /**
     * map 操作用法
     * 基本用法，一一对应关系，例如在 student对象中，找出每个学生的名字（名字具有唯一性）
     *
     */
    private void doSomethingMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()){
                    e.onNext(1);
                    e.onNext(2);
                    e.onNext(3);
                    e.onNext(4);
                }
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return integer+" apply ";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

                Log.i(TAG,"map accept "+s);
            }
        });

    }

    /**
     * Rxjava  Zip用法
     * 合并事件专用
     * <p>
     * 分别从两个上游事件中各取出一个组合
     * 一个事件只能被使用一次，顺序严格按照事件发送的顺序
     * 最终下游事件收到的是和上游事件最少的数目相同（必须两两配对，多余的舍弃)
     */
    private void doSomethingZip() {
        Observable.zip(getStringObservable(), getIntergerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s,@NonNull Integer integer) throws Exception {
                return s+integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG,"zip accept "+s);
            }
        });
    }

    private Observable<Integer> getIntergerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()){
                    e.onNext(1);
                    Log.i(TAG,"Interger Observable 1");
                    e.onNext(2);
                    Log.i(TAG,"Interger Observable 2");
                    e.onNext(3);
                    Log.i(TAG,"Interger Observable 3");
                }
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()){
                    e.onNext("A");
                    Log.i(TAG,"StringObservable A");
                    e.onNext("B");
                    Log.i(TAG,"StringObservable B");
                    e.onNext("C");
                    Log.i(TAG,"StringObservable C");
                    e.onNext("D");
                    Log.i(TAG,"StringObservable D");
                    e.onNext("E");
                    Log.i(TAG,"StringObservable E");
                }
            }
        });
    }

    /**
     * create用法
     */
    private void doSomething() {
//        Toast.makeText(this,"1111",Toast.LENGTH_SHORT).show();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG,"Observable   1");
                e.onNext(1);
                Log.i(TAG,"Observable   2");
                e.onNext(2);
                Log.i(TAG,"Observable   3");
                e.onNext(3);
//                e.onError(new Throwable("错误了" +
//                        "兑付" +
//                        "啊"));
                Log.i(TAG,"Observable   4");
                e.onNext(4);

            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"subscirbe  onSubscirbe "+d.isDisposed());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,"subscirbe  onNext "+integer);
                // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                i++;
                if (i == 2 ){
                    mDisposable.dispose();
                    Log.i(TAG,"subscirbe  onNext mDisposable "+integer);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"subscirbe  onError " +
                        e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"subscirbe  onComplete ");
            }
        });
    }


}

package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.os.SystemClock;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class RxLiveData extends LiveData<String> {
    public RxLiveData(final int taskNumber) {
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                SystemClock.sleep(2000);
                return Observable.just("Task #" + taskNumber + " completed!");
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        throw new RuntimeException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

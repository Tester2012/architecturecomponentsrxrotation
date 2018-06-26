package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.os.SystemClock;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class RxLiveData extends LiveData<String> {
    public RxLiveData(String args) {
        Observable<String> observable = Observable.defer(() -> {
            SystemClock.sleep(2000);
            return Observable.just("Task " + args + " completed!");
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

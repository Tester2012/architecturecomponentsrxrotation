package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.os.SystemClock;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class RxLiveData extends LiveData<User> {
    public RxLiveData() {
        setValue(new User());
    }

    public RxLiveData(String args) {
        Observable<User> observable = Observable.defer(() -> {
            SystemClock.sleep(10000);
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Smith");
            return Observable.just(user);
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<User>() {
                    @Override
                    public void onNext(User value) {
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

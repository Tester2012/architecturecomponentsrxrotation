package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class RxViewModel extends ViewModel {
    private RxLiveData liveData;

    public void observeRxLiveDate(LifecycleOwner owner,
                                  Observer<User> observer) {
        if (liveData != null) {
            liveData.observe(owner, observer);
        }
    }

    public boolean hasLiveData() {
        return liveData != null;
    }

    public void initLiveData(String... someArgs) {
        liveData = new RxLiveData(someArgs);
    }

    public static class RxViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private String[] someArgs;

        public RxViewModelFactory(String... someArgs) {
            this.someArgs = someArgs;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RxViewModel();
        }
    }
}
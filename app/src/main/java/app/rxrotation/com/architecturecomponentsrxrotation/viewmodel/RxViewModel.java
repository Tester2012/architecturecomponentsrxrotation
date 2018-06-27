package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class RxViewModel extends ViewModel {
    private final LiveData<User> liveData;

    public RxViewModel(String... someArgs) {
        liveData = new RxLiveData(someArgs);
    }

    public LiveData<User> getLiveData() {
        return liveData;
    }

    public static class RxViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private String[] someArgs;

        public RxViewModelFactory(String... someArgs) {
            this.someArgs = someArgs;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RxViewModel(someArgs);
        }
    }
}
package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class RxViewModel extends ViewModel {
    private final LiveData<User> liveData;

    public RxViewModel() {
        liveData = new RxLiveData("");
    }

    public LiveData<User> getLiveData() {
        return liveData;
    }

    public static class RxViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RxViewModel();
        }
    }
}
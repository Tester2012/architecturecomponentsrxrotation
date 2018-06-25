package app.rxrotation.com.architecturecomponentsrxrotation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RxViewModel extends ViewModel {
    private final List<LiveData<String>> liveDataList = new ArrayList<>();

    public RxViewModel() {
        for (int i = 0; i < 10; i++) {
            RxLiveData rxLiveData = new RxLiveData(i);
            liveDataList.add(rxLiveData);
        }
    }

    public List<LiveData<String>> getLiveDataList() {
        return liveDataList;
    }

    public static class RxViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RxViewModel();
        }
    }
}

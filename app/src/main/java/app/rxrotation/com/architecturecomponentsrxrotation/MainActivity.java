package app.rxrotation.com.architecturecomponentsrxrotation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.Callable;

import app.rxrotation.com.architecturecomponentsrxrotation.viewmodel.RxViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);

        RxViewModel.RxViewModelFactory factory = new RxViewModel.RxViewModelFactory();
        final RxViewModel model = ViewModelProviders.of(this, factory)
                .get(RxViewModel.class);
        for (LiveData<String> liveData : model.getLiveDataList()) {
            liveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    String previousText = textView.getText().toString();
                    textView.setText(previousText + " " + s);
                }
            });
        }
    }
}

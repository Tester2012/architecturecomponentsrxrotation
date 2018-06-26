package app.rxrotation.com.architecturecomponentsrxrotation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.rxrotation.com.architecturecomponentsrxrotation.viewmodel.RxViewModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);

        RxViewModel.RxViewModelFactory factory = new RxViewModel.RxViewModelFactory();
        final RxViewModel model = ViewModelProviders.of(this, factory)
                .get(RxViewModel.class);
        LiveData<String> liveData = model.getLiveData();
        liveData.observe(this, s -> {
            textView.setText(s);
        });
    }
}

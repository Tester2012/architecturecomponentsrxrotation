package app.rxrotation.com.architecturecomponentsrxrotation;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.rxrotation.com.architecturecomponentsrxrotation.databinding.ActivityMainBinding;
import app.rxrotation.com.architecturecomponentsrxrotation.viewmodel.RxViewModel;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private RxViewModel viewModel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Loading...");

        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);

        String[] someRags = new String[] {"arg1", "arg2"};
        RxViewModel.RxViewModelFactory factory = new RxViewModel.RxViewModelFactory(someRags);
        viewModel = ViewModelProviders.of(this, factory)
                .get(RxViewModel.class);

        observeLiveData();
        findViewById(R.id.load_button).setOnClickListener(v -> {
            viewModel.initLiveData();
            observeLiveData();
        });
    }

    private void observeLiveData() {
        if (viewModel.hasLiveData()) {
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            viewModel.observeRxLiveDate(this, user -> {
                progressDialog.dismiss();
                mainBinding.setUser(user);
            });
        }
    }
}

package app.rxrotation.com.architecturecomponentsrxrotation;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.rxrotation.com.architecturecomponentsrxrotation.databinding.ActivityMainBinding;
import app.rxrotation.com.architecturecomponentsrxrotation.viewmodel.RxViewModel;
import app.rxrotation.com.architecturecomponentsrxrotation.viewmodel.User;

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

        RxViewModel.RxViewModelFactory factory = new RxViewModel.RxViewModelFactory("");
        viewModel = ViewModelProviders.of(this, factory)
                .get(RxViewModel.class);

        observeLiveData();
        findViewById(R.id.load_button).setOnClickListener(v -> {
            viewModel.initLiveData("");
            observeLiveData();
        });
    }

    private void observeLiveData() {
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        viewModel.observeRxLiveDate(this, user -> {
            progressDialog.dismiss();
            mainBinding.setUser(user);
        });
    }
}

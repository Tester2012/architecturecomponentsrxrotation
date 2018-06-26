package app.rxrotation.com.architecturecomponentsrxrotation;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Loading...");

        final ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);

        RxViewModel.RxViewModelFactory factory = new RxViewModel.RxViewModelFactory();
        final RxViewModel model = ViewModelProviders.of(this, factory)
                .get(RxViewModel.class);
        LiveData<User> liveData = model.getLiveData();
        progressDialog.show();
        liveData.observe(this, s -> {
            progressDialog.dismiss();
            mainBinding.setUser(s);
        });
    }
}

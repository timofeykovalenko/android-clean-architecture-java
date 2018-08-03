package com.kiparo.presentation.screeens.home;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.kiparo.cleanarchitecture.R;
import com.kiparo.cleanarchitecture.databinding.ActivityHomeBinding;
import com.kiparo.presentation.base.BaseMvvmActivity;
import com.kiparo.presentation.utils.CustomViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomeActivity extends BaseMvvmActivity<HomeViewModel,
        ActivityHomeBinding, HomeRouter> {

    @Inject
    public CustomViewModelFactory<HomeViewModel> viewModelFactory;

    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        return intent;
    }

    @Override
    protected HomeViewModel provideViewModel() {
        return ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected HomeRouter provideRouter() {
        return new HomeRouter(this);
    }
}

package com.kiparo.presentation.base;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kiparo.cleanarchitecture.BR;
import com.kiparo.presentation.screeens.home.HomeViewModel;

import dagger.android.AndroidInjection;


public abstract class BaseMvvmActivity<
        VM extends BaseViewModel,
        B extends ViewDataBinding,
        R extends BaseRouter>
        extends BaseActivity {

    protected VM viewModel;
    protected B binding;

    //Please note: it is basic concept of Router, you should use more complex version in the real project.
    protected R router;

    /**
     * Use ViewModelProviders.of(this).get(ViewModel.class); or CussomFactory.
     * Also you can use dagger for that.
     */
    protected abstract VM provideViewModel();

    protected abstract int provideLayoutId();

    protected abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        viewModel = provideViewModel();

        binding = DataBindingUtil.setContentView(this, provideLayoutId());

        //we use the same variable name in xml - BR.viewModel, to keep it clean
        //and it is not allowed to use several variable in the xml
        binding.setVariable(BR.viewModel, viewModel);

        router = provideRouter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }
}

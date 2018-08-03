package com.kiparo.presentation.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiparo.cleanarchitecture.BR;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;


public abstract class BaseMvvmFragment<
        VM extends BaseViewModel,
        B extends ViewDataBinding,
        R extends BaseRouter>
        extends BaseFragment {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);

        viewModel = provideViewModel();
        router = provideRouter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }
}

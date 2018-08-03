package com.kiparo.presentation.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.Lazy;

public class CustomViewModelFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

    private Lazy<VM> viewModel;

    @Inject
    public CustomViewModelFactory(Lazy<VM> viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public <VM extends ViewModel> VM create(Class<VM> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.get().getClass())) {
            return (VM) viewModel.get();
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}

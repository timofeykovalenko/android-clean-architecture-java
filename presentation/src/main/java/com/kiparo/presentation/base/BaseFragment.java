package com.kiparo.presentation.base;

import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseFragment extends Fragment {

    private CompositeDisposable compositeDisposable;

    /**
     * Returns a global CompositeDisposable that will be disposed in onDestroyView()
     */
    protected CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    protected void addToCompositeDisposable(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}

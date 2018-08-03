package com.kiparo.presentation.base;

import android.util.Log;
import android.widget.Toast;

import com.kiparo.cleanarchitecture.BuildConfig;
import com.kiparo.cleanarchitecture.R;
import com.kiparo.domain.entity.AppError;

import io.reactivex.exceptions.CompositeException;

//Please note: it is basic concept of Router, you should use more complex version in the real project.
public class BaseRouter<A extends BaseActivity> {

    public static final String TAG = BaseRouter.class.getSimpleName();

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void goBack() {
        activity.onBackPressed();
    }

    public void showError(Throwable throwable) {

        if (throwable instanceof CompositeException) {
            throwable = ((CompositeException)throwable).getExceptions().get(0);
        }

        if(throwable instanceof AppError) {

            AppError error = (AppError)throwable;
            switch (error.getType()) {
                case SERVER_IS_NOT_AVAILABLE: {
                    showToast(R.string.error_server_not_available);
                    return;
                }
                case SERVER_NORMAL_ERROR: {
                    if(!error.getMessageForUser().isEmpty()) {
                        showToast(error.getMessageForUser());
                        return;
                    } else {
                        showToast(R.string.error_server);
                    }
                    return;
                }
            }
        }

        //unexpected error
        //TODO do not forget to send error with logs to developers
        Log.e(TAG, "critical problem " + throwable.toString());

        if (BuildConfig.DEBUG) {
            showToast(throwable.toString());
        } else {
            showToast(R.string.error_unexpected);
        }
    }

    public void hideKeyboard() {
        activity.hideKeyboard();
    }

    public void showToast(int messageResId) {
        showToast(activity.getString(messageResId));
    }

    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}

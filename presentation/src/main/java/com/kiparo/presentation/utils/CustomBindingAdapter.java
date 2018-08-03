package com.kiparo.presentation.utils;

import android.databinding.BindingAdapter;
import android.view.View;

import com.jakewharton.rxrelay2.Relay;

public class CustomBindingAdapter {

    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean visibility) {

        if(visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("onClick")
    public static void onClick(View view, final Relay<Boolean> listener) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.accept(true);
            }
        });
    }
}

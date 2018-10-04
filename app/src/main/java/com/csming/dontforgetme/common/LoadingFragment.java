package com.csming.dontforgetme.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csming.dontforgetme.R;

import androidx.annotation.Nullable;
import timber.log.Timber;

/**
 * @author Created by csming on 2018/4/11.
 */

public class LoadingFragment extends DialogFragment {

    public static final String TAG = "LoadingFragment.fragment";

    private static LoadingFragment loadingFragment;

    public static LoadingFragment init() {
        LoadingFragment loadingFragment = new LoadingFragment();
        loadingFragment.setCancelable(false);
        return loadingFragment;
    }

    public static void show(FragmentManager fragmentManager) {
        if (loadingFragment == null) {
            loadingFragment = init();
        }

        if (loadingFragment.getDialog() == null || !loadingFragment.isAdded() && !loadingFragment.getDialog().isShowing()) {
            loadingFragment.show(fragmentManager, TAG);
        }

//        HttpResponseCache.install()
    }

    public static void hidden() {
        try {
            if (loadingFragment != null && loadingFragment.getDialog() != null) {
                loadingFragment.getDialog().dismiss();
            }
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

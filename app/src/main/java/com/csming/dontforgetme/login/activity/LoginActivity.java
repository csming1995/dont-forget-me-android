package com.csming.dontforgetme.login.activity;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.csming.dontforgetme.R;
import com.csming.dontforgetme.login.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends DaggerAppCompatActivity {

    private FrameLayout mFlLogin;
    private FrameLayout mFlRegister;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initData();
        initView();
    }

    private void initData() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    private void initView() {
        mFlLogin = findViewById(R.id.fl_login);
        mFlRegister = findViewById(R.id.fl_register);

        mFlLogin.setOnClickListener(v -> {
        });

        mFlRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}

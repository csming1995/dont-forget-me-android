package com.csming.dontforgetme.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.Contacts;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.model.NetModelKt;
import com.csming.dontforgetme.login.viewmodel.LoginViewModel;
import com.csming.dontforgetme.main.MainActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    private FrameLayout mFlLogin;
    private FrameLayout mFlRegister;

    private EditText etUserId;
    private EditText etPassword;

    private String mUserId = "";
    private String mPasswd = "";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 全屏
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Contacts.REGISTER_REQUEST_CODE && data != null) {
                mUserId = data.getStringExtra(RegisterActivity.RESULT_KEY_USER_ID);
                mPasswd = data.getStringExtra(RegisterActivity.RESULT_KEY_PASSWD);
                login();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        loginViewModel.getLoginResultLiveData().observe(this, netModel -> {
            if (netModel != null) {
                if (netModel.getStatus() == NetModelKt.NET_ERROR) {
                    Toast.makeText(this, "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
                } else {
                    String result = netModel.getData();
                    if (!TextUtils.isEmpty(result)) {
                        ApplicationConfig.setToken(this, result);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void initView() {
        etUserId = findViewById(R.id.et_user_id);
        etPassword = findViewById(R.id.et_passwd);

        mFlLogin = findViewById(R.id.fl_login);
        mFlRegister = findViewById(R.id.fl_register);

        etUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mUserId = s.toString();
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPasswd = s.toString();
            }
        });

        mFlLogin.setOnClickListener(v -> {
            login();
        });

        mFlRegister.setOnClickListener(v -> {
            startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), Contacts.REGISTER_REQUEST_CODE);
        });
    }

    private void login() {
        loginViewModel.login(mUserId, mPasswd);
    }
}

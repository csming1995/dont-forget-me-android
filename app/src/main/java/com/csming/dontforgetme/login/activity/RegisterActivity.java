package com.csming.dontforgetme.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.LoadingFragment;
import com.csming.dontforgetme.common.model.NetModelKt;
import com.csming.dontforgetme.login.viewmodel.RegisterViewModel;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class RegisterActivity extends DaggerAppCompatActivity {

    public static final String RESULT_KEY_USER_ID = "user_id";
    public static final String RESULT_KEY_PASSWD = "passwd";

    private TextInputLayout mTilPhone;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etVertifyCode;

    private String mUserId = "";
    private String mPasswd = "";

    private FrameLayout mFlRegister;

    private boolean mIsInputLegal = false;

    @Inject
    ViewModelProvider.Factory factory;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();
        initView();
    }


    private void initData() {
        registerViewModel = ViewModelProviders.of(this, factory).get(RegisterViewModel.class);

        registerViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                LoadingFragment.show(getFragmentManager());
            } else {
                LoadingFragment.hidden();
            }
        });

        registerViewModel.getRegisterResultLiveData().observe(this, netModel -> {
            if (netModel != null) {
                if (netModel.getStatus() == NetModelKt.NET_ERROR) {
                    Toast.makeText(this, "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
                } else {
                    String result = netModel.getData();
                    if ("success".equals(result)) {
                        Toast.makeText(this, "恭喜你，成为了「不忘」的一员", Toast.LENGTH_SHORT).show();
                        setResult();
                        finish();
                    }
                }
            }
        });
    }

    private void initView() {
        mTilPhone = findViewById(R.id.til_phone);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_passwd);
        etVertifyCode = findViewById(R.id.et_verification_code);

        mFlRegister = findViewById(R.id.fl_register);

        // 检测手机号码格式
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mUserId = s.toString();
                if (!registerViewModel.isPhoneNumLegal(mUserId)) {
                    mTilPhone.setError(getText(R.string.register_phone_error));
                } else {
                    mTilPhone.setErrorEnabled(false);
                    mIsInputLegal = true;
                }
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

        mFlRegister.setOnClickListener( v -> {
            if (mIsInputLegal) {
                registerViewModel.register(mUserId, mPasswd);
            }
        });
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY_USER_ID, mUserId);
        intent.putExtra(RESULT_KEY_PASSWD, mPasswd);
        setResult(Activity.RESULT_OK, intent);
    }
}

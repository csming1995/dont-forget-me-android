package com.csming.dontforgetme.login.activity;

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
import com.csming.dontforgetme.common.model.ApiResultModel;
import com.csming.dontforgetme.common.model.ApiResultModelKt;
import com.csming.dontforgetme.common.model.RegisterResultModel;
import com.csming.dontforgetme.login.viewmodel.RegisterViewModel;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class RegisterActivity extends DaggerAppCompatActivity {

    private TextInputLayout mTilPhone;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etVertifyCode;

    private FrameLayout mFlRegister;

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


        registerViewModel.getRegisterResultLiveData().observe(this, apiResultModel -> {
            if (apiResultModel.getState() == ApiResultModelKt.NET_ERROR){
                Toast.makeText(this, "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
            } else {
                RegisterResultModel registerResultModel = apiResultModel.getData();
                if ("success" .equals(registerResultModel.getResult())) {
                    Toast.makeText(this, "恭喜你，成为了「不忘」的一员", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, registerResultModel.getError_body(), Toast.LENGTH_SHORT).show();
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
                if (!registerViewModel.isPhoneNumLegal(etPhone.getText().toString())) {
                    mTilPhone.setError(getText(R.string.register_phone_error));
                } else {
                    mTilPhone.setErrorEnabled(false);
                }
            }
        });

        mFlRegister.setOnClickListener(v->{
            registerViewModel.register(etPhone.getText().toString(), etPassword.getText().toString());
        });
    }
}

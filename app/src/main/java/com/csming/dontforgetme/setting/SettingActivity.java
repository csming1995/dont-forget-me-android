package com.csming.dontforgetme.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.csming.dontforgetme.R;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Created by csming on 2019/1/19.
 */
public class SettingActivity extends DaggerAppCompatActivity {


    public static Intent getIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}

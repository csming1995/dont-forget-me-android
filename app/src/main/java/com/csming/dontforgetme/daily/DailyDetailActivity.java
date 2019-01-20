package com.csming.dontforgetme.daily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.csming.dontforgetme.R;
import com.csming.dontforgetme.daily.viewmodel.DailyDetailViewModel;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class DailyDetailActivity extends DaggerAppCompatActivity {

    private Toolbar toolbar;

//    // Header Glide Options
//    private RequestOptions mOptionsHeader = new RequestOptions()
//            .circleCrop()
//            .error(R.drawable.default_icon);

    @Inject
    ViewModelProvider.Factory factory;

    private DailyDetailViewModel dailyDetailViewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, DailyDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_detail);

        initToolBar();
        initViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.daily_detail_title);
        }
    }

    /**
     * 初始化数据
     */
    private void initViewModel() {
        dailyDetailViewModel = ViewModelProviders.of(this, factory).get(DailyDetailViewModel.class);
    }
}

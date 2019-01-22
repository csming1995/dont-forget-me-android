package com.csming.dontforgetme.daily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.widget.collapsing.CollapsingToolbarLayoutState;
import com.csming.dontforgetme.daily.viewmodel.DailyDetailViewModel;
import com.google.android.material.appbar.AppBarLayout;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.support.DaggerAppCompatActivity;

public class DailyDetailActivity extends DaggerAppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private LinearLayout mLlToolbarTitle;

    private SwipeRefreshLayout mSrlContent;

    private CollapsingToolbarLayoutState state;

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

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_daily_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {

        appBarLayout = findViewById(R.id.app_bar_layout);
        mLlToolbarTitle = findViewById(R.id.ll_toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(null);
        }

        // 处理CollapsingToolbarLayout的展开与关闭的动作
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    mLlToolbarTitle.setVisibility(View.GONE);//设置title为EXPANDED
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    mLlToolbarTitle.setVisibility(View.VISIBLE);//设置title不显示
                    state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    mLlToolbarTitle.setVisibility(View.GONE);//设置title为INTERNEDIATE
                    state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                }
            }
        });

    }

    private void initView() {
        mSrlContent = findViewById(R.id.srl_content);
        
        mSrlContent.setOnRefreshListener(() -> {

            // FIXME delete me
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                mSrlContent.setRefreshing(false);
            }, 2000);
        });
    }

    /**
     * 初始化数据
     */
    private void initViewModel() {
        dailyDetailViewModel = ViewModelProviders.of(this, factory).get(DailyDetailViewModel.class);
    }
}

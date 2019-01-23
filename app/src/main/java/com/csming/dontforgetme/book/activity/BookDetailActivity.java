package com.csming.dontforgetme.book.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.book.adapter.BookDetailAdapter;
import com.csming.dontforgetme.book.viewmodel.BookDetailViewModel;
import com.csming.dontforgetme.common.model.NetModelKt;
import com.csming.dontforgetme.common.model.RecordingModel;
import com.csming.dontforgetme.common.widget.collapsing.CollapsingToolbarLayoutState;
import com.csming.dontforgetme.daily.DailyDetailActivity;
import com.csming.dontforgetme.timeline.activity.PostActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Created by csming on 2019/01/23.
 */
public class BookDetailActivity extends DaggerAppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private LinearLayout mLlToolbarTitle;

    private SwipeRefreshLayout mSrlContent;
    private RecyclerView mRvBookContent;
    private RecyclerView.LayoutManager mLayoutManagerBookContent;
    private BookDetailAdapter mAdapterBookContent;

    private CollapsingToolbarLayoutState state;

    private FloatingActionButton mFabPost;

    @Inject
    ViewModelProvider.Factory factory;

    private BookDetailViewModel bookDetailViewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, BookDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initToolBar();


        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewModel();
        refreshDailies();
        mSrlContent.setOnRefreshListener(this::refreshDailies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_details, menu);
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

        mRvBookContent = findViewById(R.id.rv_book_content);
        mLayoutManagerBookContent = new LinearLayoutManager(this);
        mRvBookContent.setLayoutManager(mLayoutManagerBookContent);
        mAdapterBookContent = new BookDetailAdapter();
        mRvBookContent.setAdapter(mAdapterBookContent);

        mAdapterBookContent.setOnItemClickListener((v, recordingModel, position) -> {
            Intent intent = DailyDetailActivity.getIntent(this);
            startActivity(intent);
        });

        mFabPost = findViewById(R.id.fab_post);
        mFabPost.setOnClickListener(v -> startActivity(new Intent(BookDetailActivity.this, PostActivity.class)));
    }

    /**
     * 初始化数据
     */
    private void initViewModel() {
        bookDetailViewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel.class);
        bookDetailViewModel.setToken(ApplicationConfig.getToken(this));

        bookDetailViewModel.getDailyLiveData().observe(this, netModel -> {
            if (mSrlContent != null) {
                mSrlContent.setRefreshing(false);
            }
            if (netModel != null) {
                if (netModel.getStatus() == NetModelKt.NET_ERROR) {
                    Toast.makeText(this, "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
                } else if (netModel.getData() != null) {
                    List<RecordingModel> recordingModels = netModel.getData();
                    if (recordingModels != null && recordingModels.size() > 0) {
                        mAdapterBookContent.setData(recordingModels);
                    }
                }
            }
        });
    }

    private void refreshDailies() {
        bookDetailViewModel.getDailies();
    }
}

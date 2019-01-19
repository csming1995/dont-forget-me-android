package com.csming.dontforgetme.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.adapter.ViewPagerAdapter;
import com.csming.dontforgetme.common.model.UserModel;
import com.csming.dontforgetme.main.fragment.BooksFragment;
import com.csming.dontforgetme.main.fragment.DailyFragment;
import com.csming.dontforgetme.main.viewmodel.MainViewModel;
import com.csming.dontforgetme.timeline.activity.PostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import dagger.android.support.DaggerAppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends DaggerAppCompatActivity {

    private Toolbar toolbar;

    private DrawerLayout mDrawer;
    private NavigationView mNavMenu;

    private FloatingActionButton mFabPost;

    private CircleImageView mIvToolbarHeader;
    private TextView mTvToolbarUserName;

    private CircleImageView mIvDrawerHeader;
    private TextView mTvDrawerUserName;

    private TabLayout mTlTitle;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private List<Fragment> mFragments;
    private List<String> mFragmentTitles;

    // Header Glide Options
    private RequestOptions mOptionsHeader = new RequestOptions()
            .circleCrop()
            .error(R.drawable.default_icon);

    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initViewModel();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            getSupportActionBar().setTitle(null);
        }
    }

    /**
     * 初始化数据
     */
    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        mainViewModel.setToken(ApplicationConfig.getToken(this));
    }

    private void initData() {
        mainViewModel.getMe();

        mainViewModel.getUserLiveData().observe(this, netModel -> {
            if (netModel != null) {
                if (netModel.getData() != null) {
                    UserModel userModel = netModel.getData();
                    if (userModel != null) {
                        Glide.with(this)
                                .load(userModel.getAvatar())
                                .apply(mOptionsHeader)
                                .into(mIvDrawerHeader);
                        Glide.with(this)
                                .load(userModel.getAvatar())
                                .apply(mOptionsHeader)
                                .into(mIvToolbarHeader);
                        mTvToolbarUserName.setText(userModel.getNickname());
                        mTvDrawerUserName.setText(userModel.getNickname());
                    }
                }
            }
        });
    }

    private void initView() {
        mDrawer = findViewById(R.id.drawer_layout);

        mFabPost = findViewById(R.id.fab_post);

        mIvToolbarHeader = toolbar.findViewById(R.id.iv_toolbar_user_header);
        mTvToolbarUserName = toolbar.findViewById(R.id.tv_toolbar_user_name);

        mNavMenu = findViewById(R.id.nav_main_menu);

        mIvDrawerHeader = mNavMenu.getHeaderView(0).findViewById(R.id.iv_drawer_header);
        mTvDrawerUserName = mNavMenu.getHeaderView(0).findViewById(R.id.tv_drawer_user_name);

        mTlTitle = findViewById(R.id.tl_titles);
        mViewPager = findViewById(R.id.viewpager_main);

        // 初始化viewpager
        mFragments = new ArrayList<>(2);
        mFragments.add(BooksFragment.getInstance());
        mFragments.add(DailyFragment.getInstance());
        mFragmentTitles = new ArrayList<>(2);
        mFragmentTitles.add(getString(R.string.main_tag_books));
        mFragmentTitles.add(getString(R.string.main_tag_timeline));

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mFragmentTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTlTitle.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(mFragments.size() - 1);

        mIvToolbarHeader.setOnClickListener(v -> {
            mDrawer.openDrawer(GravityCompat.START);
        });

        // 侧边栏的菜单
        mNavMenu.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.nav_camera) {

            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            }

            mDrawer.closeDrawer(GravityCompat.START);
            return true;
        });

        // 点击了发送按钮呀
        mFabPost.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        });

        mIvDrawerHeader.setOnClickListener(v -> {
            mDrawer.openDrawer(GravityCompat.START);
        });
    }
}

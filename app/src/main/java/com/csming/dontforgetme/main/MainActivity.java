package com.csming.dontforgetme.main;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.book.AddBookActivity;
import com.csming.dontforgetme.common.model.ApiResultModelKt;
import com.csming.dontforgetme.common.model.BooksModel;
import com.csming.dontforgetme.common.widget.AutofitRecyclerView;
import com.csming.dontforgetme.main.adapter.BooksListAdapter;
import com.csming.dontforgetme.main.viewmodel.MainViewModel;
import com.csming.dontforgetme.timeline.activity.PostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private Toolbar toolbar;

    private DrawerLayout mDrawer;
    private NavigationView mNavMenu;

    private FloatingActionButton mFabPost;

    private ImageView mIvToolbarHeader;
    private TextView mTvToolbarUserName;

    private ImageView mIvDrawerHeader;
    private TextView mTvDrawerUserName;

    private AutofitRecyclerView mRvBooks;
    private BooksListAdapter mAdapterBooks;

    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initData();
        initView();

        freshBooks();
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
    private void initData() {
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        mainViewModel.setToken(ApplicationConfig.getToken(this));

        mainViewModel.getBookLiveData().observe(this, apiResultModel -> {
            if (apiResultModel.getState() == ApiResultModelKt.NET_ERROR){
                Toast.makeText(this, "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
            } else if (apiResultModel.getData() != null){
                BooksModel booksModel = apiResultModel.getData();
                if (booksModel.getBooks() != null && booksModel.getBooks().length > 0) {
                    mAdapterBooks.setData(Arrays.asList(booksModel.getBooks()));
                } else {
                    Toast.makeText(this, booksModel.getError_body(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        mDrawer = findViewById(R.id.drawer_layout);

        mFabPost = findViewById(R.id.fab_post);

        mNavMenu = findViewById(R.id.nav_main_menu);

        mIvToolbarHeader = toolbar.findViewById(R.id.iv_toolbar_user_header);
        mTvToolbarUserName = toolbar.findViewById(R.id.tv_toolbar_user_name);

        mIvDrawerHeader = findViewById(R.id.iv_drawer_header);
        mTvDrawerUserName = findViewById(R.id.tv_drawer_user_name);

        mRvBooks = findViewById(R.id.rv_books);

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

        mAdapterBooks = new BooksListAdapter();
        mRvBooks.setAdapter(mAdapterBooks);
        mRvBooks.setColumnWidth((int) (Resources.getSystem().getDisplayMetrics().density * 100));

        mAdapterBooks.setOnAddClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        mAdapterBooks.setOnItemClickListener((view, position) -> {
//            BookModel bookModel =
        });

        mFabPost.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        });

    }

    private void freshBooks() {
        mainViewModel.getBooks();
    }
}

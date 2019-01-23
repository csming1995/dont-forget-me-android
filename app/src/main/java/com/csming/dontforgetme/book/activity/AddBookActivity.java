package com.csming.dontforgetme.book.activity;

import android.os.Bundle;

import com.csming.dontforgetme.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Created by csming on 2018/10/14.
 */
public class AddBookActivity extends DaggerAppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        setToolBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.add_book);
//            actionBar.setDisplayUseLogoEnabled();
        }

        mToolbar.setOnMenuItemClickListener(item -> false);
    }
}

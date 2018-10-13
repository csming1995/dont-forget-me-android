package com.csming.dontforgetme.book;

import android.os.Bundle;

import com.csming.dontforgetme.R;

import androidx.annotation.Nullable;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Created by csming on 2018/10/14.
 */
public class AddBookActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }
}

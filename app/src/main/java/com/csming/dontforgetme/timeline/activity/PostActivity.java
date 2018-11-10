package com.csming.dontforgetme.timeline.activity;

import android.os.Bundle;

import com.csming.dontforgetme.R;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Created by csming on 2018/11/11.
 */
public class PostActivity extends DaggerAppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

    }
}

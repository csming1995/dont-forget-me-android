package com.csming.dontforgetme.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.model.ApiResultModelKt;
import com.csming.dontforgetme.common.model.RecordingsModel;
import com.csming.dontforgetme.main.adapter.DailiesListAdapter;
import com.csming.dontforgetme.main.viewmodel.MainViewModel;

import java.util.Arrays;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;
import timber.log.Timber;

/**
 * @author Created by csming on 2018/11/21.
 */
public class MainTimelineFragment extends DaggerFragment {


    private RecyclerView mRvDailies;
    private DailiesListAdapter mDailiesListAdapter;


    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel mainViewModel;

    public static MainTimelineFragment getInstance() {
        return new MainTimelineFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mainViewModel = ViewModelProviders.of(getActivity(), factory).get(MainViewModel.class);
        mainViewModel.setToken(ApplicationConfig.getToken(getActivity()));

        mainViewModel.getDailyLiveData().observe(getActivity(), apiResultModel -> {
            if (apiResultModel.getState() == ApiResultModelKt.NET_ERROR) {
                Toast.makeText(getActivity(), "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
            } else if (apiResultModel.getData() != null) {
                RecordingsModel recordingsModel = apiResultModel.getData();
                if (recordingsModel.getRecordings() != null && recordingsModel.getRecordings().length > 0) {
                    mDailiesListAdapter.setData(Arrays.asList(recordingsModel.getRecordings()));
                } else {
                    Toast.makeText(getActivity(), recordingsModel.getError_body(), Toast.LENGTH_SHORT).show();
                }
            }
            Timber.d(apiResultModel.toString());
        });
    }

    private void initView(View view) {
        mRvDailies = view.findViewById(R.id.rv_books);
        mDailiesListAdapter = new DailiesListAdapter();
        mRvDailies.setAdapter(mDailiesListAdapter);

        mDailiesListAdapter.setOnItemClickListener((v, position) -> {
//            BookModel bookModel =
        });
    }
}

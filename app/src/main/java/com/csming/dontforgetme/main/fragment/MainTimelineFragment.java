package com.csming.dontforgetme.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.model.NetModelKt;
import com.csming.dontforgetme.common.model.RecordingModel;
import com.csming.dontforgetme.main.adapter.DailiesListAdapter;
import com.csming.dontforgetme.main.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.support.DaggerFragment;

/**
 * @author Created by csming on 2018/11/21.
 */
public class MainTimelineFragment extends DaggerFragment {

    private SwipeRefreshLayout mSrlDailies;
    private RecyclerView.LayoutManager mLayoutManagerDailies;
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
    public void onResume() {
        super.onResume();
        initData();
        mSrlDailies.setOnRefreshListener(() -> mainViewModel.getDailies());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mainViewModel = ViewModelProviders.of(getActivity(), factory).get(MainViewModel.class);
        mainViewModel.setToken(ApplicationConfig.getToken(getActivity()));

        mainViewModel.getDailyLiveData().observe(getActivity(), netModel -> {
            if (mSrlDailies != null) {
                mSrlDailies.setRefreshing(false);
            }
            if (netModel != null) {
                if (netModel.getStatus() == NetModelKt.NET_ERROR) {
                    Toast.makeText(getActivity(), "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
                } else if (netModel.getData() != null) {
                    List<RecordingModel> recordingModels = netModel.getData();
                    if (recordingModels != null && recordingModels.size() > 0) {
                        mDailiesListAdapter.setData(recordingModels);
                    }
                }
            }
        });
    }

    private void initView(View view) {

        mSrlDailies = view.findViewById(R.id.srl_books);
        mRvDailies = view.findViewById(R.id.rv_books);
        mLayoutManagerDailies = new LinearLayoutManager(getActivity());
        mRvDailies.setLayoutManager(mLayoutManagerDailies);
        mDailiesListAdapter = new DailiesListAdapter();
        mRvDailies.setAdapter(mDailiesListAdapter);

        mDailiesListAdapter.setOnItemClickListener((v, position) -> {
//            BookModel bookModel =
        });
    }
}

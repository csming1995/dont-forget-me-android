package com.csming.dontforgetme.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.csming.dontforgetme.ApplicationConfig;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.book.AddBookActivity;
import com.csming.dontforgetme.common.model.BookModel;
import com.csming.dontforgetme.common.model.NetModelKt;
import com.csming.dontforgetme.common.widget.AutofitRecyclerView;
import com.csming.dontforgetme.main.adapter.BooksListAdapter;
import com.csming.dontforgetme.main.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

/**
 * @author Created by csming on 2018/11/20.
 */
public class BooksFragment extends DaggerFragment {

    private AutofitRecyclerView mRvBooks;
    private BooksListAdapter mAdapterBooks;

    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel mainViewModel;

    public static Fragment getInstance() {
        return new BooksFragment();
    }

    public BooksFragment() {
        super();
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

        mainViewModel.getBookLiveData().observe(this, netModel -> {
            if (netModel != null) {
                if (netModel.getStatus() == NetModelKt.NET_ERROR) {
                    Toast.makeText(getActivity(), "网络异常, 请检查网络状态", Toast.LENGTH_SHORT).show();
                } else if (netModel.getData() != null) {
                    List<BookModel> bookModels = netModel.getData();
                    if (bookModels != null && bookModels.size() > 0) {
                        mAdapterBooks.setData(bookModels);
                    }
                }
            }
        });
    }

    private void initView(View view) {
        mRvBooks = view.findViewById(R.id.rv_books);
        mAdapterBooks = new BooksListAdapter();
        mRvBooks.setAdapter(mAdapterBooks);
        mRvBooks.setColumnWidth((int) (Resources.getSystem().getDisplayMetrics().density * 100));

        mAdapterBooks.setOnAddClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddBookActivity.class);
            startActivity(intent);
        });

        mAdapterBooks.setOnItemClickListener((v, position) -> {
//            BookModel bookModel =
        });
    }
}

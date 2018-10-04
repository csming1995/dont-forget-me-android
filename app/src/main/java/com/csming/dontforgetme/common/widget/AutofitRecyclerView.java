package com.csming.dontforgetme.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Created by csming on 2018/3/21.
 */

public class AutofitRecyclerView extends RecyclerView {
    private GridLayoutManager manager;
    // 默认为-1
    private int mColumnWidth = -1;
    private int spanCount = 1;

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        manager = new GridLayoutManager(context, 1, VERTICAL, false);
        setLayoutManager(manager);
    }

    public void setColumnWidth(int columnWidth) {
        mColumnWidth = columnWidth;
    }

    public int getCount() {
        return spanCount;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mColumnWidth > 0) {
            spanCount = Math.max(1, getMeasuredWidth() / mColumnWidth);
            manager.setSpanCount(spanCount);
        }
    }
}

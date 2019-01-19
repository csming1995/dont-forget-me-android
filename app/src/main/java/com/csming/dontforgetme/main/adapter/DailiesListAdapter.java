package com.csming.dontforgetme.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.model.RecordingModel;
import com.csming.dontforgetme.common.utils.DateformatUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Created by csming on 2018/10/4.
 */
public class DailiesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecordingModel> mRecordings;

    private OnItemClickListener onItemClickListener;

    public DailiesListAdapter() {
        super();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<RecordingModel> books) {
        if (this.mRecordings == null) {
            this.mRecordings = new ArrayList<>();
        }
        this.mRecordings.clear();
        this.mRecordings.addAll(books);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_daily, parent, false);
        return new TimelinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (position < mRecordings.size()) {
            RecordingModel recording = mRecordings.get(position);
            if (recording != null) {
                if (recording.getAuther() != null) {
                    ((TimelinesViewHolder) holder).setHeader(recording.getAuther().getAvatar());
                    ((TimelinesViewHolder) holder).setAuthorName(recording.getAuther().getNickname());
                }
                if (recording.getBook() != null) {
                    ((TimelinesViewHolder) holder).setBookName(recording.getBook().getName());
                }
                Glide.with(((TimelinesViewHolder) holder).mIvImages.getContext())
                        .load(position % 2 == 1 ? R.drawable.cat_test : R.drawable.cat_test2)
                        .into(((TimelinesViewHolder) holder).mIvImages);
                ((TimelinesViewHolder) holder).setDescription(recording.getText());
                ((TimelinesViewHolder) holder).setCreateTime(DateformatUtils.formatDate(recording.getCreateTime()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mRecordings == null ? 0 : this.mRecordings.size();
    }

    private static class TimelinesViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvHeader;
        private TextView mTvAuthorName;
        private TextView mTvBookName;
        private TextView mTvText;
        private ImageView mIvImages;
        private TextView mTvCreateTime;

        TimelinesViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeader = itemView.findViewById(R.id.iv_header);
            mTvAuthorName = itemView.findViewById(R.id.tv_author_name);
            mTvBookName = itemView.findViewById(R.id.tv_book_title);
            mTvText = itemView.findViewById(R.id.tv_text);
            mIvImages = itemView.findViewById(R.id.iv_images);
            mTvCreateTime = itemView.findViewById(R.id.tv_create_time);
        }

        void setHeader(String src) {
            if (!TextUtils.isEmpty(src)) {
                Glide.with(mIvHeader).load(src).into(mIvHeader);
            }
        }

        void setAuthorName(String authorName) {
            if (!TextUtils.isEmpty(authorName)) {
                mTvAuthorName.setText(authorName);
            }
        }

        void setBookName(String bookName) {
            if (!TextUtils.isEmpty(bookName)) {
                String name = itemView.getContext().getString(R.string.daliy_book_name_format, bookName);
                mTvBookName.setText(name);
            }
        }

        void setDescription(String description) {
            if (!TextUtils.isEmpty(description)) {
                mTvText.setText(description);
            }
        }

        void setCreateTime(String createTime) {
            if (!TextUtils.isEmpty(createTime)) {
                mTvCreateTime.setText(createTime);
            }
        }
    }
}

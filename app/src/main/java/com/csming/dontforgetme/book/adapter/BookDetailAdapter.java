package com.csming.dontforgetme.book.adapter;

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
 * @author Created by csming on 2019/1/23.
 */
public class BookDetailAdapter extends RecyclerView.Adapter {

    private int ITEM_TYPE_HEADER = 1;
    private int ITEM_TYPE_NORMAL = 2;

    private OnItemClickListener onItemClickListener;

    private List<RecordingModel> mRecordings;

    public BookDetailAdapter() {
        super();
    }

    public interface OnItemClickListener {
        void onClick(View view, RecordingModel recordingModel, int position);
    }

    public interface OnAddClickListener {
        void onClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<RecordingModel> recordings) {
        if (this.mRecordings == null) {
            this.mRecordings = new ArrayList<>();
        }
        this.mRecordings.clear();
        this.mRecordings.addAll(recordings);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == ITEM_TYPE_NORMAL) {
            View view = layoutInflater.inflate(R.layout.item_daily, parent, false);
            return new TimelinesViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.item_book_detail_header, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL && position > 0 && position <= mRecordings.size()) {
            RecordingModel recording = mRecordings.get(position - 1);
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

                holder.itemView.setOnClickListener(v -> {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(v, recording, position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mRecordings == null ? 1 : mRecordings.size() + 1;
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

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

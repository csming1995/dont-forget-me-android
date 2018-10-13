package com.csming.dontforgetme.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.model.BookModel;
import com.csming.dontforgetme.common.widget.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.csming.dontforgetme.common.config.ApiConfigKt.BASE_URL;

/**
 * @author Created by csming on 2018/10/4.
 */
public class BooksListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TYPE_NORMAL = 1;
    private int ITEM_TYPE_ADD = 2;

    private List<BookModel> books;

    public BooksListAdapter() {
        super();
    }

    public void setData(List<BookModel> books) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == ITEM_TYPE_NORMAL) {
            View view = layoutInflater.inflate(R.layout.item_list_books, parent, false);
            return new BooksNormalViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.item_list_books_add, parent, false);
            return new AddViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            BookModel book = books.get(position);
            if (book != null) {
                RequestOptions options = new RequestOptions()
                        .circleCrop()
                        .transform(new GlideRoundTransform(((BooksNormalViewHolder) holder).mIvCover.getContext()));
                Glide.with(((BooksNormalViewHolder) holder).mIvCover.getContext())
                        .load(BASE_URL + book.getFrontCover())
                        .apply(options)
                        .into(((BooksNormalViewHolder) holder).mIvCover);
                ((BooksNormalViewHolder) holder).mTvTitle.setText(book.getBookName());
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return this.books == null ? 1 : this.books.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount() - 1) {
            return ITEM_TYPE_NORMAL;
        } else {
            return ITEM_TYPE_ADD;
        }
    }

    static class BooksNormalViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvCover;
        TextView mTvTitle;

        BooksNormalViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_book_cover);
            mTvTitle = itemView.findViewById(R.id.tv_book_title);
        }
    }

    static class AddViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvCover;
        TextView mTvTitle;

        AddViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_book_cover);
            mTvTitle = itemView.findViewById(R.id.tv_book_title);
        }
    }
}

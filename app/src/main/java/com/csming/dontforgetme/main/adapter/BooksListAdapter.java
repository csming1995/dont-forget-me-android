package com.csming.dontforgetme.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csming.dontforgetme.R;
import com.csming.dontforgetme.common.config.ApiConfig;
import com.csming.dontforgetme.common.model.BookModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.csming.dontforgetme.common.config.ApiConfigKt.BASE_URL;

/**
 * @author Created by csming on 2018/10/4.
 */
public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.BooksViewHolder> {

    private List<BookModel> books;

    public BooksListAdapter(){
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
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list_books, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        BookModel book = books.get(position);
        if (book != null) {
            Glide.with(holder.mIvCover.getContext())
                    .load(BASE_URL + book.getFrontCover())
                    .into(holder.mIvCover);
            holder.mTvTitle.setText(book.getBookName());
        }
    }

    @Override
    public int getItemCount() {
        return this.books == null? 0: this.books.size();
    }

    static class BooksViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvCover;
        TextView mTvTitle;

        BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_book_cover);
            mTvTitle = itemView.findViewById(R.id.tv_book_title);
        }
    }
}

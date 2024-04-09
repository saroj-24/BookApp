package com.example.codingbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryAdapter.myviewholder> {

    Context context;
    ArrayList<BookCategoryModel> bookCategoryModels;

    public BookCategoryAdapter(Context context, ArrayList<BookCategoryModel> bookCategoryModels) {
        this.context = context;
        this.bookCategoryModels = bookCategoryModels;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_categories,null);
        return new myviewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
       BookCategoryModel model = bookCategoryModels.get(position);
       holder.catgbook_name.setText(model.getBook_name());
        Glide.with(context).load(model.getBook_img())
                .placeholder(R.drawable.book)
                .error(R.drawable.java) // Error image to show if loading fails
                .centerCrop()
                .into(holder.catgbook_image);

    }


    @Override
    public int getItemCount() {
        return bookCategoryModels.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        private ImageView catgbook_image;
        private TextView catgbook_name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            catgbook_name = itemView.findViewById(R.id.categorytext);
            catgbook_image= itemView.findViewById(R.id.categoryimage);
        }
    }

}

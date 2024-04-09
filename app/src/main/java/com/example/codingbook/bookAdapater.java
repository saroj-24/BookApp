package com.example.codingbook;

import android.content.Context;
import android.content.Intent;
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

public class bookAdapater  extends RecyclerView.Adapter<bookAdapater.myviewholder>{

  // Context context;
   Context context;
   ArrayList<PopularBookModel> bookTestClasses;

    public bookAdapater(Context context, ArrayList<PopularBookModel> bookTestClasses) {
        this.context = context;
        this.bookTestClasses = bookTestClasses;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.popular_book,null);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
      PopularBookModel pop_model = bookTestClasses.get(position);
      holder.book_name.setText(pop_model.getPbook());
        Glide.with(context).load(pop_model.getPimage())
                .placeholder(R.drawable.book)
                .error(R.drawable.java) // Error image to show if loading fails
                .centerCrop()
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), PopularBookActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookTestClasses.size() ;
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView book_name;
        private  RecyclerView popbook;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.popular_categoryimage);
            book_name = itemView.findViewById(R.id.popular_categorytext);
           // popbook = itemView.findViewById(R.id.popular_recycleview);
        }
    }

}

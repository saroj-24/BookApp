package com.example.codingbook.popularbookfunction;

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
import com.example.codingbook.R;

import java.util.ArrayList;

public class bookAccessAdapter extends RecyclerView.Adapter<bookAccessAdapter.myviewholder> {

    Context context;
    ArrayList<popularBookAccessModel> popularBookAccessModels;

    public bookAccessAdapter(Context context, ArrayList<popularBookAccessModel> popularBookAccessModels) {
        this.context = context;
        this.popularBookAccessModels = popularBookAccessModels;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popularbook_container,null);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        popularBookAccessModel popbookmodel = popularBookAccessModels.get(position);
        holder.pbook_name.setText(popbookmodel.getBook_name());
        holder.pbook_author.setText(popbookmodel.getBook_author());
        holder.pbook_publisher.setText(popbookmodel.getBook_publisher());

        Glide.with(context).load(popbookmodel.getBook_image())//book image
                .placeholder(R.drawable.book)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.Pbook_image);

        Glide.with(context).load(popbookmodel.getBook_image())//book like image
                .placeholder(R.drawable.book)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.Plike_image);


    }


    @Override
    public int getItemCount() {
        return popularBookAccessModels.size();
    }

    public class myviewholder extends  RecyclerView.ViewHolder{

        ImageView Pbook_image,Plike_image;
        TextView pbook_name,pbook_author,pbook_publisher;



        public myviewholder(@NonNull View itemView) {
            super(itemView);

            Pbook_image = itemView.findViewById(R.id.book_imageview);
            Plike_image = itemView.findViewById(R.id.favorite_imageview);
            pbook_name= itemView.findViewById(R.id.pop_book_name);
            pbook_author = itemView.findViewById(R.id.pop_author_name);
            pbook_publisher=itemView.findViewById(R.id.pop_publisher_name);
        }
    }
}

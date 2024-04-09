package com.example.codingbook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.codingbook.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {

    public Home_Fragment() {

    }

    FragmentHomeBinding binding;
    FirebaseFirestore firestoredb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        firestoredb = FirebaseFirestore.getInstance();
        ArrayList<PopularBookModel> pop_category = new ArrayList<>();
        bookAdapater popadapters = new bookAdapater(getContext(),pop_category);

        ArrayList<BookCategoryModel> categoryAdapters =  new ArrayList<>();
        BookCategoryAdapter bookAdapters  =  new BookCategoryAdapter(getContext(),categoryAdapters);
        firestoredb.collection("PopularBook_db")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        pop_category.clear();
                        for(DocumentSnapshot snapshot : value.getDocuments())
                        {
                            PopularBookModel pmodel = snapshot.toObject(PopularBookModel.class);
                                pmodel.setPid(snapshot.getId());
                                pop_category.add(pmodel);

                        }
                        popadapters.notifyDataSetChanged();
                    }
                });
        firestoredb.collection("Book_db")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                             categoryAdapters.clear();
                             for(DocumentSnapshot snapshot:value.getDocuments())
                             {
                                 BookCategoryModel bookmodel = snapshot.toObject(BookCategoryModel.class);
                                 bookmodel.setBookID(snapshot.getId());
                                 categoryAdapters.add(bookmodel);
                             }
                                bookAdapters.notifyDataSetChanged();

                            }

                        });

        binding.popularRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.popularRecycleview.setAdapter(popadapters);
        binding.allCategorybookRv.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.allCategorybookRv.setAdapter(bookAdapters);

        return binding.getRoot();
    }
}
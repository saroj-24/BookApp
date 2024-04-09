package com.example.codingbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.codingbook.databinding.ActivityPopularBookBinding;
import com.example.codingbook.popularbookfunction.bookAccessAdapter;
import com.example.codingbook.popularbookfunction.popularBookAccessModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.DescriptorProtos;

import java.util.ArrayList;
import java.util.Objects;

public class PopularBookActivity extends AppCompatActivity {
   private ActivityPopularBookBinding binding;

   private FirebaseFirestore firestore;
    private bookAccessAdapter adapter; // Assuming BookAccessAdapter is your custom adapter
    private ArrayList<popularBookAccessModel> bookModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityPopularBookBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         setSupportActionBar(binding.toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setTitle("Popular book");
         final String catId = getIntent().getStringExtra("catId");
         firestore = FirebaseFirestore.getInstance();
         bookModelList = new ArrayList<>();
         adapter = new bookAccessAdapter(this,bookModelList);
         bookAccessAdapter bookadapter = new bookAccessAdapter(this,bookModelList);


        bookModelList.clear();
        firestore.collection("PopularBook_db")
                .document("book_id")
                .collection("popularbook_database")
                .orderBy("book_id", Query.Direction.ASCENDING)
                .addSnapshotListener(((value, error) -> {
                    if(error != null)
                    {
                        Toast.makeText(getApplicationContext(),"book loaded ",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    for(DocumentChange  dc : Objects.requireNonNull(value).getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            bookModelList.add(dc.getDocument().toObject(popularBookAccessModel.class));
                        }
                        bookadapter.notifyDataSetChanged();
                    }


                }));

        binding.popularBookAccessRV.setLayoutManager(new LinearLayoutManager(this));
        binding.popularBookAccessRV.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
//    public void loadData()
//    {
//        bookmodel.clear();
//        firestore.collection("PopularBook_db")
//                .document("catId")
//                .collection("popularbook_database")
//                .orderBy("book_id", Query.Direction.ASCENDING)
//                .addSnapshotListener(((value, error) -> {
//                    if(error != null)
//                    {
//                        Toast.makeText(getApplicationContext(),"book loaded ",Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                    for(DocumentChange  dc : Objects.requireNonNull(value).getDocumentChanges()) {
//                        if (dc.getType() == DocumentChange.Type.ADDED) {
//                            bookmodel.add(dc.getDocument().toObject(popularBookAccessModel.class));
//                        }
//                         bookAccessAdapter.notifyDataSetChanged();
//
//                                        }
//
//                }));
//    }
}
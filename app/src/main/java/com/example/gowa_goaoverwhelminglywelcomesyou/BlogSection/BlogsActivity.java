package com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.example.gowa_goaoverwhelminglywelcomesyou.StringVariable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlogsActivity extends AppCompatActivity {

    ArrayList<BlogListAdapter.BlogModal> blogList;
    RecyclerView listView;
    BlogListAdapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        blogList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child(StringVariable.BLOGS);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("xxx",dataSnapshot.toString());
                blogList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    blogList.add(new BlogListAdapter.BlogModal(String.valueOf(snapshot.getKey()),
                            String.valueOf(snapshot.child("imageURL").getValue()),
                            String.valueOf(snapshot.child("desc").getValue()),
                            String.valueOf(snapshot.child("URL").getValue())));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new BlogListAdapter(this, blogList);
        listView = findViewById(R.id.blog_recycler_view);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
    }
}
package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.TaxiFaresAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllComplaintsActivity extends AppCompatActivity {

    RecyclerView complaintsRecycler;
    ArrayList<ComplaintModel> complaintList = new ArrayList<>();
    ComplaintsAdapter complaintsAdapter;
    float distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_complaints);
        initialize();

    }

    private void initialize() {
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("complaints").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    complaintList.add(new ComplaintModel(String.valueOf(ds.child("title").getValue()), String.valueOf(ds.child("body").getValue()), Long.parseLong(String.valueOf(ds.child("timestamp").getValue()))));
                }
                complaintsRecycler = findViewById(R.id.recycler_view);
                complaintsAdapter = new ComplaintsAdapter(AllComplaintsActivity.this, complaintList);

                complaintsRecycler.setAdapter(complaintsAdapter);
                complaintsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder>{
    Context context;
    ArrayList<ComplaintModel> list;

        public ComplaintsAdapter(Context context, ArrayList<ComplaintModel> list) {
            this.context = context;
            this.list = list;
        }
        @NonNull
        @Override
        public ComplaintsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.complaint_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ComplaintsAdapter.ViewHolder holder, int position) {
            holder.title.setText(list.get(position).title);
            holder.body.setText(list.get(position).body);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            holder.date.setText(formatter.format(new Date(list.get(position).timestamp)));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView title, body, date;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.title = itemView.findViewById(R.id.title);
                this.body = itemView.findViewById(R.id.body);
                this.date = itemView.findViewById(R.id.date);
            }
        }
    }
}


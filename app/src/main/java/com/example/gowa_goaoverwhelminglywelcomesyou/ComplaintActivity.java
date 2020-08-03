package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ComplaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        findViewById(R.id.all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintActivity.this, AllComplaintsActivity.class));
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((EditText) findViewById(R.id.subject)).getText().equals("") || ((EditText) findViewById(R.id.body)).getText().equals("")){
                    Toast.makeText(ComplaintActivity.this,"You must enter all the information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Map<String, Object> map = new HashMap<>();
                map.put("title", "" + ((EditText) findViewById(R.id.subject)).getText());
                map.put("body", "" + ((EditText) findViewById(R.id.body)).getText());
                map.put("uid", "" + FirebaseAuth.getInstance().getCurrentUser().getUid());
                map.put("timestamp", "" + System.currentTimeMillis());
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("complaints");
                final String key = dbref.push().getKey();
                dbref.child(key).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("complaints").child(key).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                    Toast.makeText(ComplaintActivity.this, "Successfully registered",Toast.LENGTH_SHORT).show();
                                ((EditText) findViewById(R.id.subject)).setText("");
                                ((EditText) findViewById(R.id.body)).setText("");
                            }
                        });
                    }

                    ;
                });
            }
        });
    }

}


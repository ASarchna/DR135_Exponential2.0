package com.example.gowa_goaoverwhelminglywelcomesyou.Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowa_goaoverwhelminglywelcomesyou.Login.LoginActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password,con_password;
    TextView sign_up,already_account;
    RelativeLayout progressBar;
    String userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        con_password=(EditText)findViewById(R.id.con_password);
        sign_up=(TextView)findViewById(R.id.Sign_up_button);
        already_account=(TextView)findViewById(R.id.already_account);
        progressBar=(RelativeLayout) findViewById(R.id.linear_progress);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = String.valueOf(email.getText());
                String pass1 = String.valueOf(password.getText());
                String pass2 = String.valueOf(con_password.getText());
                if (email.equals("") || pass1.equals("") || pass2.equals("")) {
                    Toast.makeText(SignupActivity.this, "The fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!pass1.equals(pass2)) {
                    Toast.makeText(SignupActivity.this, "The passwords don't match", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    sign(email1, pass1);
                }
            }

        });
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }
    private void sign(String email1, String pass1) {
        mAuth.createUserWithEmailAndPassword(email1, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG-SignupActivity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            setUser();
//                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG-SignupActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });
    }

    private void setUser() {
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Uri profileURI = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();

        Log.e("xxxu", userUID);
//        Log.e("xxxp",profileURI.toString());
        Log.e("xxxe", email.toString());
//        Log.e("xxxn",name.toString());

//        UserProfile userProfile = new UserProfile(name,email,profileURI.toString());
        //    setSharedPrefs("0", "", "");
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        userRef.child(userUID).child("email").setValue(email);
        userRef.child(userUID).child("isProfileComplete").setValue("0");
//        startActivity(new Intent(SignupActivity.this, HomeSliderActivity.class).putExtra("userUID", userUID));
        finish();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //   FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }
}

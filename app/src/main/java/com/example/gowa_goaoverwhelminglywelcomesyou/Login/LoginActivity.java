package com.example.gowa_goaoverwhelminglywelcomesyou.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.example.gowa_goaoverwhelminglywelcomesyou.Signup.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email1,password;
    TextView con_button,create_account;
    private FirebaseAuth mAuth;
    RelativeLayout relativeLayout;
    String userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email1=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        con_button=(TextView)findViewById(R.id.Sign_in_button);
        create_account=(TextView)findViewById(R.id.create_account);
        relativeLayout=(RelativeLayout)findViewById(R.id.linear_progress);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        con_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //login_verify
        con_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, pass;
                email = String.valueOf(email1.getText());
                pass = String.valueOf(password.getText());
                if(email.equals("")){
                    email1.setError("Email cannot be empty");
                }
                else if(pass.equals("")){
                    password.setError("Password cannot be empty");
                }
                else{

                    relativeLayout.setVisibility(View.VISIBLE);
                    login(email,pass);
                }

            }
        });
    }

    private void login(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG LoginActivity", "signInWithEmail:success");
                            getUserInfo();


//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            relativeLayout.setVisibility(View.GONE);
                        }

                        // ...
                    }
                });

    }

    private void getUserInfo() {
        setSharedPrefs(0,userUID,1);
    }
    private void setSharedPrefs(int isProfileComplete, String userUID, int isLoggedIn) {
        try {
            Log.d("aman ","sharedpreference login");

            SharedPreferences pref = getSharedPreferences("UserPrefs", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("isProfileComplete",isProfileComplete);
            editor.putInt("isLoggedin", isLoggedIn);
            editor.putString("userid", userUID);
            editor.apply();
//            startActivity(new Intent(LoginActivity.this,LiveScanning.class));
            relativeLayout.setVisibility(View.GONE);

        }
        catch (Exception e){
            Log.e("TAG LoginActivity","Unable to set sharedprefs");
            relativeLayout.setVisibility(View.GONE);
        }
    }

}

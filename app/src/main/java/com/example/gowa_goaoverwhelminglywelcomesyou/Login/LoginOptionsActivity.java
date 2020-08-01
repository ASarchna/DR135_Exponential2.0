package com.example.gowa_goaoverwhelminglywelcomesyou.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowa_goaoverwhelminglywelcomesyou.MainActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.example.gowa_goaoverwhelminglywelcomesyou.Signup.SignupActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginOptionsActivity extends AppCompatActivity {
    TextView Sign_up,Sign_in;
    ImageView sign_up_google;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    String TAG = "LoginOptionsActivity";
    ProgressBar relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        Sign_up=(TextView) findViewById(R.id.Sign_up);
        Sign_in=(TextView) findViewById(R.id.Sign_in_button);
        relativeLayout=(ProgressBar) findViewById(R.id.progress_id);
        sign_up_google=(ImageView)findViewById(R.id.sign_up_using_google);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOptionsActivity.this,LoginActivity.class));
            }
        });
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOptionsActivity.this, SignupActivity.class));
            }
        });
        sign_up_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
                signIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.d("xxxx", "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("firebase_auth", "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressBar();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            relativeLayout.setVisibility(View.GONE);
                            setSharedPrefs(1,mAuth.getCurrentUser().getUid(),1);
                            startActivity(new Intent(LoginOptionsActivity.this, MainActivity.class));
                            finish();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("xxxx", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            relativeLayout.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "xxxx:failure", task.getException());
                            //     Snackbar.make(findViewById(R.id.), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //     updateUI(null);
                            Toast.makeText(LoginOptionsActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        //    hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void setSharedPrefs(int isProfileComplete, String userUID, int isLoggedIn) {
        try {
            Log.d("aman ","sharedpreference login");

//            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            SharedPreferences pref = getSharedPreferences("UserPrefs", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("isProfileComplete",isProfileComplete);
            editor.putInt("isLoggedin", isLoggedIn);
            editor.putString("userid", userUID);
            editor.apply();
            relativeLayout.setVisibility(View.GONE);
        }
        catch (Exception e){
            relativeLayout.setVisibility(View.GONE);
            Log.e("TAG LoginActivity","Unable to set sharedprefs");
        }
    }

}

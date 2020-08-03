package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gowa_goaoverwhelminglywelcomesyou.MainActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.MyHelper;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    SimpleDraweeView imageView;
    EditText nameEditText, ageEditText;
    Spinner usernameEditText, genderSpinner, religionSpinner;
    Button goButton;
    LinearLayout linearProgress;
    String userUID;
    int avatar;
    private int[] views = {R.id.image_profile, R.id.profile_label,R.id.create_profile_image, R.id.profile_info, R.id.age_label, R.id.profile_age, R.id.name_label, R.id.profile_name, R.id.username_label, R.id.user_name, R.id.create_profile_button};
    String[] items = new String[]{"Single", "Married", "Others"};
    String[] items2 = new String[]{"Male", "Female", "Others"};
    String[] items3 = new String[]{"Christianity", "Hinduism", "Islam", "Buddhism", "others"};
    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        if(getIntent().getIntExtra("from",0)==1){
            isUpdating = true;
            ((TextView)findViewById(R.id.create_profile_button)).setText("Save");
        }
        init();
    }

    private void init() {
        imageView = findViewById(R.id.create_profile_image);
        nameEditText = findViewById(R.id.profile_name);
        ageEditText = findViewById(R.id.profile_age);
        goButton = findViewById(R.id.create_profile_button);
        linearProgress = findViewById(R.id.linear_progress);
        usernameEditText = findViewById(R.id.user_name);
        genderSpinner = findViewById(R.id.profile_gender);
        religionSpinner = findViewById(R.id.profile_religion);
        avatar = 1;
        goButton.setOnClickListener(this);
        imageView.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        usernameEditText.setAdapter(adapter);
        genderSpinner.setAdapter(adapter2);
        religionSpinner.setAdapter(adapter3);

//        animateAvatar();
        userUID = getIntent().getStringExtra("userUID");
        try {
            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        } catch (NullPointerException np) {
            np.printStackTrace();
        }

        imageView.performClick();

        imageView.performClick();
        imageView.performClick();
        imageView.performClick();
        imageView.performClick();
        if(isUpdating){
            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    nameEditText.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                    ageEditText.setText(String.valueOf(dataSnapshot.child("age").getValue()));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void animateAvatar() {

    }

    private void fadeOutAndHideImage(final SimpleDraweeView img, final int avatar) {
        Animation fadeOut = new AlphaAnimation(1, 0.5f);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(200);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                fadeInAndShowImage(img);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        img.startAnimation(fadeOut);
    }


    private void fadeInAndShowImage(final SimpleDraweeView img) {

        Animation fadeIn = new AlphaAnimation(0.5f, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(200);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        img.startAnimation(fadeIn);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //-----------------------------
        if (id == R.id.create_profile_button) {
            linearProgress.setVisibility(View.VISIBLE);
            checkUserName();

        }
        //---------------------------------
        else if (id == R.id.create_profile_image) {
            if (avatar != 5) {
                avatar++;
                fadeOutAndHideImage(imageView, avatar);
            } else {
                avatar = 1;
                fadeOutAndHideImage(imageView, avatar);
            }

        }
        //------------------------------------
    }

    private void checkUserName() {
        final String name = String.valueOf(nameEditText.getText());
        final String age = String.valueOf(ageEditText.getText());
        final String married = items[Integer.parseInt(String.valueOf(usernameEditText.getSelectedItemId()))];
        final String gender = items2[Integer.parseInt(String.valueOf(genderSpinner.getSelectedItemId()))];
        final String religion = items3[Integer.parseInt(String.valueOf(religionSpinner.getSelectedItemId()))];
        Log.d("xxxx",name+age+gender+married+religion);

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userUID);
        dbref.child("name").setValue(name);
        dbref.child("age").setValue(age);
        dbref.child("married").setValue(married);
        dbref.child("gender").setValue(gender);
        dbref.child("religion").setValue(religion);
        dbref.child("isProfileComplete").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setSharedPrefs(name, age, married, gender, religion);
                startActivity(new Intent(CreateProfileActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

    }

    private void setSharedPrefs(String name, String age, String married, String gender, String religion) {
        try {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPrefs", 0); // 0 - for private mode
            Log.d("aman ", "sharedpreference create" + pref.toString());

            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("isProfileComplete", 1);
            editor.putString("name", name);
            editor.putString("age", age);
            editor.putString("married", married);
            editor.putString("gender", gender);
            editor.putString("religion", religion);
            editor.apply();
        } catch (Exception e) {
            Log.e("TAG CreateProfileAct", "Unable to set sharedprefs");
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Context context = getApplicationContext();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}

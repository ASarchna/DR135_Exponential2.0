package com.example.gowa_goaoverwhelminglywelcomesyou.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.gowa_goaoverwhelminglywelcomesyou.Login.LoginOptionsActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.MainActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                SharedPreferences sharedpreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                Log.d("aman",sharedpreferences.getInt("isLoggedIn",0)+"");
                if(sharedpreferences.getInt("isLoggedin", 0)!=0){
                    if(sharedpreferences.getInt("isProfileComplete",0)==1){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
                else{
                    Intent i = new Intent(SplashActivity.this, LoginOptionsActivity.class);
                    startActivity(i);
                }
                finish();
            }

        }, 3*1000); // wait for 5 seconds


    }
}

package com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadingBlogsActivity extends AppCompatActivity {

    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i= getIntent();
        URL = i.getStringExtra("URL");
        WebView myWebView = new WebView(this);
        setContentView(myWebView);
        myWebView.loadUrl(URL);
    }
}
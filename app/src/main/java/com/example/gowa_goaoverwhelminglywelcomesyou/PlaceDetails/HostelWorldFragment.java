package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;


public class HostelWorldFragment extends Fragment {


    String URL;
    WebView mWebView;
    String month1, date1, year1, month2, date2, year2, to,child,adults,rooms;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent i= getActivity().getIntent();
        URL = i.getStringExtra("URL");

        year1 = i.getStringExtra("year1");
        month1 = i.getStringExtra("month1");
        date1 = i.getStringExtra("date1");

        year2 = i.getStringExtra("year2");
        month2 = i.getStringExtra("month2");
        date2 = i.getStringExtra("date2");

        rooms = i.getStringExtra("rooms");
        adults = i.getStringExtra("adults");
        child = i.getStringExtra("child");


        WebView myWebView = new WebView(getActivity());
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);

        String uri = "https://www.hostelworld.com/search?search_keywords=Goa,%20India&country=India&city=Goa&date_from="+year1+"-"+month1+"-"+date1+"&date_to="+year2+"-"+month2+"-"+date2+"&number_of_guests="+adults;
        mWebView = (WebView) view.findViewById(R.id.webViewFragment);
        mWebView.loadUrl(uri);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return view;

        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}




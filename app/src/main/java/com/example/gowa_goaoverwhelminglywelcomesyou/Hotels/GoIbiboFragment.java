package com.example.gowa_goaoverwhelminglywelcomesyou.Hotels;

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


public class GoIbiboFragment extends Fragment {


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

        String uri = "https://www.booking.com/searchresults.en-gb.html?src=index&rows=20&error_url=https%3A%2F%2Fwww.booking.com%2Findex.en-gb.html%3Flabel%3Dgen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4AQfIAQzYAQHoAQH4AQuIAgGoAgO4ArD8jfEFwAIB%3Bsid%3Ddbef8fc5e7a00da70f5fd8852ac4983b%3Bsb_price_type%3Dtotal%26%3B&label=gen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4AQfIAQzYAQHoAQH4AQuIAgGoAgO4ArD8jfEFwAIB&sid=dbef8fc5e7a00da70f5fd8852ac4983b&sb=1&search_form_id=826399e7bc58005b&checkin_monthday="+date1+"&checkin_year_month="+year1+"-1&checkout_monthday="+date2+"&checkout_year_month="+year2+"-1&checkin="+year1+"-"+month1+"-"+date1+"&checkout="+year2+"-"+month2+"-"+date2+"&group_adults="+adults+"&group_children="+child+"&no_rooms="+rooms+"&group_adults_overlay="+adults+"&group_children_overlay="+child+"&no_rooms_overlay="+rooms+"&lpsr=1&htca=1&dest_type=region&dest_id=4127&ss_raw=goa&ss_label=Goa%2C%20India&place_id_lon=73.771179&place_id_lat=15.482176&ac_position=0&ac_suggestion_list_length=5&ac_langcode=en&search_selected=true&ac_size=5&ac_click_type=b&latitude=15.482176&longitude=73.771179&search_pageview_id=826399e7bc58005b";

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




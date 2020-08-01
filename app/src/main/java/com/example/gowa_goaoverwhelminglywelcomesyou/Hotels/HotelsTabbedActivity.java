package com.example.gowa_goaoverwhelminglywelcomesyou.Hotels;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.HostelWorldFragment;
import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.TrivagoFragment;
import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.ViewPagerAdapter;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.google.android.material.tabs.TabLayout;


public class HotelsTabbedActivity extends AppCompatActivity implements View.OnClickListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        https:
//www.booking.com/searchresults.en-gb.html?src=index&rows=20&
// error_url=https%3A%2F%2Fwww.booking.com%2Findex.en-gb.html%3Flabel%3Dgen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4AQfIAQzYAQHoAQH4AQuIAgGoAgO4ArD8jfEFwAIB%3Bsid%3Ddbef8fc5e7a00da70f5fd8852ac4983b%3
// Bsb_price_type%3Dtotal%26%3B&label=gen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4AQfIAQzYAQHoAQH4AQuIAgGoAgO4ArD8jfEFwAIB&sid=dbef8fc5e7a00da70f5fd8852ac4983b&sb=1&search_form_id=826399e7bc58005b&checkin_monthday=2
// 1&checkin_year_month=2020-1&checkout_monthday=25&checkout_year_month=2020-1&checkin=2020-01-21&checkout=2020-01-25&group_adults=3&group_children=0&no_rooms=2&group_adults_overlay=3&group_children_overlay=0&no_rooms_overlay=2&lpsr=1&htca=1&dest_type=region&dest_id=4127&ss_raw=goa&ss_label=Goa%2C%20India&place_id_lon=73.771179&place_id_lat=15.482176&ac_position=0&ac_suggestion_list_length=5&ac_langcode=en&search_selected=true&ac_size=5&ac_click_type=b&latitude=15.482176&longitude=73.771179&search_pageview_id=826399e7bc58005b

        setContentView(R.layout.activity_hotels_tabbed);
        tabLayout = findViewById(R.id.hotels_tablayout);
        viewPager = findViewById(R.id.hotels_viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new GoIbiboFragment(), "Booking.com");
        adapter.addFragment(new HostelWorldFragment(), "Hostel World");
        adapter.addFragment(new TrivagoFragment(), "Trivago");



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

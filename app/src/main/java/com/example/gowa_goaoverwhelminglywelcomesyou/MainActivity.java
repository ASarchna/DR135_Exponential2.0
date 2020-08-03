package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection.BlogListAdapter;
import com.example.gowa_goaoverwhelminglywelcomesyou.Emergency.EmergencyActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.Flights_to_goa_fragment;
import com.example.gowa_goaoverwhelminglywelcomesyou.HelperClasses.SnapHelperByOne;
import com.example.gowa_goaoverwhelminglywelcomesyou.Hotels.HotelSelectorActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Packages.PackagesActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.CreateProfileActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Trains.Train_Fragment;
import com.example.gowa_goaoverwhelminglywelcomesyou.YoutubeRecommendation.YoutubeAdapter;
import com.example.gowa_goaoverwhelminglywelcomesyou.exploreGoa.ExploreModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.ui.home.HomeViewModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private Context mContext;
    BlogListAdapter blogAdapter;
    DatabaseReference blogRefrence;
    LinearLayout mapsButton, flightsButton, trainsButton, toursButton, taxiButton;
    FloatingActionButton fab;

    private AppBarConfiguration mAppBarConfiguration;

    String[] appPermissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    ArrayList<FestivalModel> festivalList = new ArrayList<>();

    ImageButton navIcon;
    int last = 7;


    private class ImageViewFactory implements ViewSwitcher.ViewFactory {

        @Override
        public View makeView() {
            final ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final ViewGroup.LayoutParams lp = new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);

            return imageView;
        }
    }

    private int[][] dotCoords = new int[5][2];
    private int[] pics = {R.drawable.lakes, R.drawable.meuseum, R.drawable.food, R.drawable.culture, R.drawable.beaches, R.drawable.fort, R.drawable.church, R.drawable.temples, R.drawable.adventure};
    private int[] maps = {R.drawable.map_paris, R.drawable.map_seoul, R.drawable.map_london, R.drawable.map_beijing, R.drawable.map_greece};
    private String[] descriptions = {"Pick from an exclusive range of Goa holiday packages from Delhi, Mumbai, Kolkata and other cities for a lovely Goa holiday",
            "Goa is all about boho vibes, hippie culture, beaches and flea markets. The history of Goa boasts of minimalism and people wearing whatever they are comfortable in and enjoying their lives to the core with a hint of boho jewelry.",
            "Let your love bloom on the Romantic beaches in Goa. Beaches are what mainly attract travelers to Goa. For a blissful beginning of married life, new couples often head to the Goa beaches.",
            "The multi-religious fabric of Goa’s society shines brightly imbibed with the spirit of “Sarva Dharma, Sarva Bhava” or Equal Respect for all Religions.",
            "The mighty land of beaches has some of the best museums each sharing a separate story regarding the life of Goa from the ancient centuries. ",
            "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world",
            "If you have always fancied a destination wedding in Goa but are still brooding over the idea,",
            "Some of the adventure trips in Goa include Dudhsagar waterfall trip, Grande Island Trip, and overnight houseboat trip."};
    private String[] place_ids = {"lakes", "meuseums", "food", "culture", "beaches", "forts", "churches", "temples", "adventure"};
    private String[] places = {"Lakes/Falls/Springs", "Meuseums", "Food", "Culture", "Beaches", "Forts", "Churches", "Temples/Mosques", "Adventure"};
    private String[] temperatures = {"223", "143", "432", "876", "345", "654", "324", "456", "987"};
    private String[] times = new String[10];

    private RecyclerView topRecyclerView, festivalRecyclerView;
    private ImageSwitcher mapSwitcher;
    private TextSwitcher temperatureSwitcher;
    private TextSwitcher placeSwitcher;
    private TextSwitcher clockSwitcher;
    private TextView descriptionsSwitcher;
    private View greenDot;
    private ImageButton navButton;

    private TextView country1TextView;
    private TextView country2TextView;
    private int countryOffset1;
    private int countryOffset2;
    private long countryAnimDuration;
    private int currentPosition;
    private  int lastPosition = 0;

    private ArrayList<MonumentsModel> monumentsList;

    RecyclerView bottomRecyclerView;
    RecyclerView blogRecommendation;
    RecyclerView youTubeRecommendation;
    ArrayList<LocalRecommendationCard> localRecommendationList;
    ArrayList<BlogListAdapter.BlogModal> blogList;
    ArrayList<YoutubeAdapter.YoutubeModel> youtubeLinksList;
    LocalHorizonalViewAdapter localHorizonalViewAdapter;
    YoutubeAdapter youtubeAdapter;
    ExploreAdapter exploreAdapter;
    FestivalsAdapter festivalsAdapter;
    ArrayList<ExploreModel> exploreList;
    TextView hello;
    SimpleDraweeView pic;
    int currentTheme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentTheme = getSharedPreferences("Theme",MODE_PRIVATE).getInt("theme",0);
        if(currentTheme == 0)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

//        SharedPreferences sharedpreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//        sharedpreferences.edit().putInt("isLoggedin",0).apply();
//        FirebaseAuth.getInstance().signOut();

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        exploreList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Explore");

        exploreList.add(new ExploreModel("https://www.peakadventuretour.com/blog/wp-content/uploads/2018/06/Bungee-Jumping-in-India.jpg","Adventure"));
        exploreList.add(new ExploreModel("https://static-blog.treebo.com/wp-content/uploads/2018/04/Arambol-Sweet-Water-Lake.jpg","Destination Wedding"));
        exploreList.add(new ExploreModel("https://images.adsttc.com/media/images/5ced/70a5/284d/d1e7/0300/041f/newsletter/03_cutaway-london-big-ben.jpg?1559064722","Landmarks"));
        exploreList.add(new ExploreModel("https://www.newzealand.com/assets/Tourism-NZ/Auckland/5d4dac429b/img-1536218738-4897-18029-A08AC5F0-99A6-A552-9AF13DF35947554D__FocalPointCropWzQyNyw2NDAsNTAsNTAsODUsImpwZyIsNjUsMi41XQ.jpg","Museums"));
        exploreList.add(new ExploreModel("https://media.cntraveller.in/wp-content/uploads/2016/11/goafolkdanceslead-866x487.jpg","Religions"));
        exploreList.add(new ExploreModel("https://www.newzealand.com/assets/Tourism-NZ/Auckland/5d4dac429b/img-1536218738-4897-18029-A08AC5F0-99A6-A552-9AF13DF35947554D__FocalPointCropWzQyNyw2NDAsNTAsNTAsODUsImpwZyIsNjUsMi41XQ.jpg","Romantic"));
        exploreList.add(new ExploreModel("https://imgstaticcontent.lbb.in/lbbnew/wp-content/uploads/sites/9/2017/01/flea2.jpg","Shops"));
        exploreList.add(new ExploreModel("https://atlas.etihad.com/wp-content/uploads/2018/02/shutterstock_482978608.jpg","Tour and Packages"));

        festivalList.add(new FestivalModel("famous festival in Goa that is celebrated on the quiet island of Divar","Bonderam Festival","Divar Island","https://www.golokaso.com/wp-content/uploads/2018/08/cover.png",8, new Date(2020,8,4)));
        festivalList.add(new FestivalModel("This century old festival is a tribute to numerous people who lost their lives in a severe epidemic that hit Vasco","Saptah Festival","Vasco region of Goa","https://2.bp.blogspot.com/-A4Y8j2jZv7c/Um6V_kJI96I/AAAAAAAAANM/rVYc9rA6lnE/s1600/lord-damodar-vasco.jpg",8, new Date(2020,8,16)));
        festivalList.add(new FestivalModel("Sunburn is a synergy of music, entertainment, food, shopping and lifestyle that pulls music tourism in India","Sunburn Festival","Calangute Beach","https://whatupintown.com/media/uploads/events/cover_images/w538ZQdZD9wm.jpg",12, new Date(2020,12,1)));
        festivalList.add(new FestivalModel("The festival is commemorated as the death anniversary of the holy saint","Feast of St. Francis Xavier","Basilica of Bom Jesus","https://www.navhindtimes.in/wp-content/uploads/2018/12/30novena-660x330.jpg",12, new Date(2021,12,3)));
        festivalList.add(new FestivalModel("Shigmo parade is a street festival binged with music, dance, colours and float parade","Shigmo festival","Ponda, Mapusa, etc","https://i0.wp.com/itsgoa.com/wp-content/uploads/2017/03/16201040563_baea974254_z.jpglokaso-shigmo.jpg?resize=640%2C396&ssl=1",12, new Date(2020,12,25)));
        festivalList.add(new FestivalModel("As the winter strikes, it open doors to usher in the grandest of all celebrations in Goa.","Christmas","All over the state","https://bongyatra.com/wp-content/uploads/2016/12/Goa-Christmas-Holiday-Package1.jpg",12, new Date(2020,12,25)));
        festivalList.add(new FestivalModel("Block your dates for the most awaited EMD beach party in India – Vh1 Supersonic","VH1 Supersonic","Candolim Beach","https://rollingstoneindia.com/wp-content/uploads/2016/01/Super-2-960x503.jpg",12, new Date(2021,12,27)));
        festivalList.add(new FestivalModel("With its inherent party culture, the state is the ideal venue for celebrating the birth of a new year amid fun and frolic","New Year Fiesta","All over the state","https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/09/anjuna-beach.jpg",12, new Date(2020,12,29)));
        festivalList.add(new FestivalModel("The Grape Escapade is a popular gourmet and unique cultural celebration","Grape Escapade","Panjim","https://i1.wp.com/www.incrediblegoa.org/wp-content/uploads/2019/02/Grape-Escapade-2019.jpg",1, new Date(2021,1,1)));
        festivalList.add(new FestivalModel("What happens in this festival is that three boys enact the scene where the three kings of Magi come to meet the baby Jesus bearing gifts","Three Kings Feast","Cansaulim, Arossim and Quelim","https://i.ytimg.com/vi/DTa63BkDsZw/maxresdefault.jpg",1, new Date(2021,1,6)));
        festivalList.add(new FestivalModel("Do you drool over the Goan curries, the Portuguese Vindaloo and the Goan fish ‘n’ chips?!","Goa Food and Cultural Festival","Beaches of Goa","https://www.navhindtimes.in/wp-content/uploads/2019/02/HSP_1219-660x330.jpg",2, new Date(2021,2,1)));
        festivalList.add(new FestivalModel("One of the hottest and most happening festivals in Goa, Carnival or the ‘Intruz’ is the only one of its kind in India!","Goa Carnival","Panjim, Mapusa, Margoa and Vasco Da Gama","https://s3.india.com/wp-content/uploads/2018/08/25travel-goa-carnival.jpg",2, new Date(2021,2,22)));
        festivalList.add(new FestivalModel("One of the most interesting festivals in Goa showcases rare arts, cuisines and concoctions","Goa Cashew and Coconut Fest","Panaji","https://static.wixstatic.com/media/7c7ab9_8d20d59eda9841e69251301902ace1ed~mv2.jpg/v1/fit/w_960,h_640,al_c,q_80/file.png",4, new Date(2021,4,15)));
        festivalList.add(new FestivalModel("Image a procession of village youth wearing crowns of leaves & feathers, carrying bottles of Fenny advancing through the village","Sao Joao","All over the state","https://images.rove.me/w_1920,q_85/fpgbiu67tbdsiijnixyp/goa-sao-joao-festival.jpg",6, new Date(2021,6,24)));
        festivalList.add(new FestivalModel("This is hosted by Goa’s agriculture directorate. Through the festival Goa celebrates the onset of the mango season.","Goa Mango Festival","Panaji","https://kmit.in/emagazine/wp-content/uploads/2018/06/International-Mango-Festival.jpg",7, new Date(2021,7,15)));

        topRecyclerView = findViewById(R.id.recycler_view);
        festivalRecyclerView = findViewById(R.id.festivals_recyclerView);
        Collections.reverse(exploreList);

                LinearSnapHelper linearSnapHelper = new SnapHelperByOne();
                linearSnapHelper.attachToRecyclerView(topRecyclerView);

                exploreAdapter = new ExploreAdapter(MainActivity.this, exploreList);
                LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
                lm.setStackFromEnd(true);
                topRecyclerView.setLayoutManager(lm);
                topRecyclerView.setAdapter(exploreAdapter);


        LinearSnapHelper linearSnapHelper2 = new SnapHelperByOne();

        linearSnapHelper2.attachToRecyclerView(festivalRecyclerView);

        festivalsAdapter = new FestivalsAdapter(MainActivity.this, festivalList);
        LinearLayoutManager lm1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        lm1.setStackFromEnd(true);
        Collections.reverse(festivalList);
        festivalRecyclerView.setLayoutManager(lm1);
        festivalRecyclerView.setAdapter(festivalsAdapter);



        initCountryText();
        initSwitchers();


                exploreAdapter.notifyDataSetChanged();


//        initGreenDot(root);


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        bottomRecyclerView = findViewById(R.id.local_recommendation_recyclerview);
        getLocalRecommendation();
        blogRecommendation = findViewById(R.id.blog_recommendation_recyclerview);
        getBlogRecommendation();
        youTubeRecommendation = findViewById(R.id.youtube_recyclerview);
        mapsButton = findViewById(R.id.maps_linear);
        flightsButton = findViewById(R.id.flights_linear);
        trainsButton = findViewById(R.id.trains_linear);
        toursButton = findViewById(R.id.tours_linear);
        taxiButton = findViewById(R.id.taxi_linear);
        fab = findViewById(R.id.fab);
        hello = findViewById(R.id.tv_country_1);
        descriptionsSwitcher = findViewById(R.id.ts_description);
        descriptionsSwitcher.setText(descriptions[7]);
        hello.setText("Hello, " + user.getDisplayName().split(" ")[0]);

        findViewById(R.id.emergency_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmergencyActivity.class));
            }
        });

        pic = findViewById(R.id.profilepic);
        pic.setImageURI(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateProfileActivity.class).putExtra("from",1));
            }
        });
        topRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState != RecyclerView.SCROLL_STATE_DRAGGING && newState != RecyclerView.SCROLL_STATE_SETTLING){
                    int cp = ((LinearLayoutManager)topRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                    try {
                        descriptionsSwitcher.setText(descriptions[cp]);
                    }
                    catch (Exception e){

                    }

                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        findViewById(R.id.complaint_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ComplaintActivity.class));
            }
        });

        findViewById(R.id.light_mode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTheme==0){
                    currentTheme = 1;
                }
                else{
                    currentTheme = 0;
                }
                SharedPreferences.Editor e = getSharedPreferences("Theme",MODE_PRIVATE).edit();
                e.putInt("theme", currentTheme);
                e.commit();

                findViewById(R.id.light_floater).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.light_floater)).setImageResource((currentTheme==1)?R.drawable.ic_light:R.drawable.ic_dark);
                findViewById(R.id.light_floater).animate().setDuration(1000).translationY(-1100);
                ((ScrollView)findViewById(R.id.main_scroll)).smoothScrollTo(0,0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("themeChanged",true);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in2, R.anim.fade_out2);
                    }
                },1000);

            }
        });
        //todo add listeners
//        mapsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MapsActivity.class));
//            }
//        });
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=nearby places, restaurants, atms, monuments", 15.492508, 73.773457);
                Intent mapIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapIntent2.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent2);            }
        });
        taxiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HotelSelectorActivity.class));
            }
        });
        toursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PackagesActivity.class));
            }
        });

        trainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Train_Fragment.class));
            }
        });
        flightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Flights_to_goa_fragment.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> permissionsNeeded = new ArrayList<>();
                for(String perm: appPermissions){
                    if (ContextCompat.checkSelfPermission(MainActivity.this, perm)
                            == PackageManager.PERMISSION_DENIED){
                        permissionsNeeded.add(perm);
                                            }
                }
                if(!permissionsNeeded.isEmpty()){
                    ActivityCompat.requestPermissions(MainActivity.this, permissionsNeeded.toArray(new String[permissionsNeeded.size()]), 100);
                }
                else {
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                }
            }
        });
        getTrendingVideos();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    void getLocalRecommendation(){
        localRecommendationList = new ArrayList<LocalRecommendationCard>();
        ArrayList<LocalRecommendationCard> temp = MyHelper.getRecommendations(MainActivity.this);
        for(LocalRecommendationCard card : temp){
            localRecommendationList.add(card);
        }
        localRecommendationList.add(new LocalRecommendationCard(StringVariable.getSeCathedral().getMonumentName(),StringVariable.SE_CATHEDRAL.getDescription(),"987","https://upload.wikimedia.org/wikipedia/commons/7/72/Se%E2%80%99_Cathedral%2C_Goa.jpg","se-cathedral"));
        localRecommendationList.add(new LocalRecommendationCard(StringVariable.getAzadMaidan().getMonumentName(),StringVariable.AZAD_MAIDAN.getDescription(),"127","https://blog.parrikar.com/wp-content/uploads/2011/04/az-1.jpg","azad-maidan"));
        localRecommendationList.add(new LocalRecommendationCard(StringVariable.getFortTiracol().getMonumentName(),StringVariable.FORT_TIRACOL.getDescription(),"457","https://www.thegoavilla.com/static/img/articles/terekhol-fort.jpg","fort-tiracol"));

        localHorizonalViewAdapter = new LocalHorizonalViewAdapter(MainActivity.this, localRecommendationList);
        bottomRecyclerView.setAdapter(localHorizonalViewAdapter);
        bottomRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }
    void getBlogRecommendation(){
        blogList = new ArrayList<>();
        blogRefrence = FirebaseDatabase.getInstance().getReference().child(StringVariable.BLOGS);
        blogRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("xxx",dataSnapshot.toString());
                blogList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    blogList.add(new BlogListAdapter.BlogModal(String.valueOf(snapshot.getKey()),
                            String.valueOf(snapshot.child("imageURL").getValue()),
                            String.valueOf(snapshot.child("desc").getValue()),
                            String.valueOf(snapshot.child("url").getValue())));
                }
                blogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        blogAdapter = new BlogListAdapter(MainActivity.this, blogList);
        blogRecommendation.setAdapter(blogAdapter);
        blogRecommendation.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }
    void getTrendingVideos(){
        youtubeLinksList = new ArrayList<>();
        youtubeLinksList.add(new YoutubeAdapter.YoutubeModel("Offbeat Places Of Goa","Zi3x8YF88yc"));
        youtubeLinksList.add(new YoutubeAdapter.YoutubeModel("Goa Tourism","XQwcCdKGjPQ"));
        youtubeLinksList.add(new YoutubeAdapter.YoutubeModel("Famous 10 places to visit in Goa","5VsEgJqHeeo"));
        youtubeLinksList.add(new YoutubeAdapter.YoutubeModel("4 DAYS TRIP TO GOA","BdqYsE38xzs"));


        youtubeAdapter = new YoutubeAdapter(MainActivity.this,youtubeLinksList);
        youTubeRecommendation.setAdapter(youtubeAdapter);
        youTubeRecommendation.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));

    }
    @Override
    public void onPause() {
        super.onPause();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void initSwitchers() {
//        temperatureSwitcher = (TextSwitcher) findViewById(R.id.ts_temperature);
//        temperatureSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));
//        temperatureSwitcher.setCurrentText(temperatures[0]);
//
//        clockSwitcher = (TextSwitcher) findViewById(R.id.ts_clock);
//        clockSwitcher.setFactory(new TextViewFactory(R.style.ClockTextView, false));
////        clockSwitcher.setCurrentText(times[0]);
//
//        descriptionsSwitcher = (TextSwitcher) findViewById(R.id.ts_description);
//        descriptionsSwitcher.setInAnimation(MainActivity.this, android.R.anim.fade_in);
//        descriptionsSwitcher.setOutAnimation(MainActivity.this, android.R.anim.fade_out);
//        descriptionsSwitcher.setFactory(new TextViewFactory(R.style.DescriptionTextView, false));
//        descriptionsSwitcher.setCurrentText(descriptions[0]);

    }
    private void initCountryText() {
//        countryAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
//        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
//        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
//        country1TextView = (TextView) findViewById(R.id.tv_country_1);
////        country2TextView = (TextView) findViewById(R.id.tv_country_2);
//
//        country1TextView.setX(countryOffset1);
//        country2TextView.setX(countryOffset2);
////        country1TextView.setText(countries[0]);
//        country2TextView.setAlpha(0f);

//        country1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
//        country2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    private class TextViewFactory implements  ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(MainActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(MainActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }
    public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
        private Context context;
        private ArrayList<ExploreModel> list;
        private int maximize = 0;

        public ExploreAdapter(Context context, ArrayList<ExploreModel> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.explore_card_view, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
            ExploreModel item=list.get(position);
            holder.simpleDraweeView.setImageURI(item.getImage());
            holder.explore_text.setText(item.getName());
            holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, PackagesActivity.class).putExtra("type",2));
                }
            });
//            if(position == maximize+1){
//                holder.layout.animate().scaleX(1.2f);
//                holder.layout.animate().scaleY(1.2f);
//            }
//            else{
//                holder.layout.animate().scaleX(1f);
//                holder.layout.animate().scaleY(1f);
//            }
            Log.e("xxxx",item.getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView simpleDraweeView;
            public TextView explore_text;
            public LinearLayout layout;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                simpleDraweeView=(SimpleDraweeView)itemView.findViewById(R.id.imageview);
                explore_text=(TextView)itemView.findViewById(R.id.explore_place);
                layout = (LinearLayout)itemView.findViewById(R.id.main_layout);
            }
        }

        public void maximizeView(int pos){
            this.maximize = pos;
//            notifyDataSetChanged();

        }
    }
    public String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("slider.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;
    }


    public class FestivalsAdapter extends RecyclerView.Adapter<FestivalsAdapter.ViewHolder> {
        private Context context;
        private ArrayList<FestivalModel> list;
        private int maximize = 0;

        public FestivalsAdapter(Context context, ArrayList<FestivalModel> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.destival_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull FestivalsAdapter.ViewHolder holder, int position) {
            final FestivalModel item=list.get(position);
            holder.cvfest.setBackgroundResource(R.drawable.festival_bg);
            holder.simpleDraweeView.setImageURI(item.getImage());
            holder.festname.setText(item.getName());
            if(item.getMonth()>=8 && item.getMonth()<=12){
                holder.upcoming.setVisibility(View.VISIBLE);
            }
            else {
                holder.upcoming.setVisibility(View.GONE);
            }
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM");
            String date = df.format(new Date(item.getDate().getYear(), item.getDate().getMonth()-1, item.getDate().getDay()));

            holder.festDate.setText(date);
            holder.desc.setText(item.desc);
            holder.location.setText(item.location);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, FestivalActivity.class).putExtra("festival",item));
                }
            });
//            if(position == maximize+1){
//                holder.layout.animate().scaleX(1.2f);
//                holder.layout.animate().scaleY(1.2f);
//            }
//            else{
//                holder.layout.animate().scaleX(1f);
//                holder.layout.animate().scaleY(1f);
//            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView simpleDraweeView;
            public TextView festname, festDate, desc, location;
            public LinearLayout layout, upcoming;
            public CardView cvfest;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                simpleDraweeView=(SimpleDraweeView)itemView.findViewById(R.id.imageview);
                festname=(TextView)itemView.findViewById(R.id.festival_name);
                desc=(TextView)itemView.findViewById(R.id.desc);
                festDate=(TextView)itemView.findViewById(R.id.date);
                location=(TextView)itemView.findViewById(R.id.location);
                layout = (LinearLayout)itemView.findViewById(R.id.main_layout);
                upcoming = (LinearLayout) itemView.findViewById(R.id.upcoming);
                cvfest = (CardView) itemView.findViewById(R.id.cv_fest);
            }
        }

        public void maximizeView(int pos){
            this.maximize = pos;
//            notifyDataSetChanged();

        }
    }
}

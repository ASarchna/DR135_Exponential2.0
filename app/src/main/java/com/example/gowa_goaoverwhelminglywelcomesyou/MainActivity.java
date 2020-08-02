package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
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
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection.BlogListAdapter;
import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.Flights_to_goa_fragment;
import com.example.gowa_goaoverwhelminglywelcomesyou.HelperClasses.SnapHelperByOne;
import com.example.gowa_goaoverwhelminglywelcomesyou.Hotels.HotelSelectorActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Packages.PackagesActivity;
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

    ImageButton navIcon;


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
    private String[] descriptions = {"Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world", "Refreshing experience of lakes and springs. Get a whole new experience and immerse into a whole new world"};
    private String[] place_ids = {"lakes", "meuseums", "food", "culture", "beaches", "forts", "churches", "temples", "adventure"};
    private String[] places = {"Lakes/Falls/Springs", "Meuseums", "Food", "Culture", "Beaches", "Forts", "Churches", "Temples/Mosques", "Adventure"};
    private String[] temperatures = {"223", "143", "432", "876", "345", "654", "324", "456", "987"};
    private String[] times = new String[10];

    private RecyclerView topRecyclerView;
    private ImageSwitcher mapSwitcher;
    private TextSwitcher temperatureSwitcher;
    private TextSwitcher placeSwitcher;
    private TextSwitcher clockSwitcher;
    private TextSwitcher descriptionsSwitcher;
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
    ArrayList<ExploreModel> exploreList;
    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

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
        topRecyclerView = findViewById(R.id.recycler_view);
        Collections.reverse(exploreList);

                LinearSnapHelper linearSnapHelper = new SnapHelperByOne();
                linearSnapHelper.attachToRecyclerView(topRecyclerView);

                exploreAdapter = new ExploreAdapter(MainActivity.this, exploreList);
                LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
                lm.setStackFromEnd(true);
                topRecyclerView.setLayoutManager(lm);
                topRecyclerView.setAdapter(exploreAdapter);

                topRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        int cp = ((LinearLayoutManager)topRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                        if(cp!=lastPosition) {
                            lastPosition = cp;
                            exploreAdapter.maximizeView(cp);
                        }

                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                exploreAdapter.notifyDataSetChanged();

        initCountryText();
        initSwitchers();
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
        hello.setText("Hello, " + user.getDisplayName().split(" ")[0]);

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

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=nearby plcaes, restaurants, atms, monuments", 15.492508, 73.773457);
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
        temperatureSwitcher = (TextSwitcher) findViewById(R.id.ts_temperature);
        temperatureSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));
        temperatureSwitcher.setCurrentText(temperatures[0]);

        clockSwitcher = (TextSwitcher) findViewById(R.id.ts_clock);
        clockSwitcher.setFactory(new TextViewFactory(R.style.ClockTextView, false));
//        clockSwitcher.setCurrentText(times[0]);

        descriptionsSwitcher = (TextSwitcher) findViewById(R.id.ts_description);
        descriptionsSwitcher.setInAnimation(MainActivity.this, android.R.anim.fade_in);
        descriptionsSwitcher.setOutAnimation(MainActivity.this, android.R.anim.fade_out);
        descriptionsSwitcher.setFactory(new TextViewFactory(R.style.DescriptionTextView, false));
        descriptionsSwitcher.setCurrentText(descriptions[0]);

    }
    private void initCountryText() {
        countryAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        country1TextView = (TextView) findViewById(R.id.tv_country_1);
        country2TextView = (TextView) findViewById(R.id.tv_country_2);

        country1TextView.setX(countryOffset1);
        country2TextView.setX(countryOffset2);
//        country1TextView.setText(countries[0]);
        country2TextView.setAlpha(0f);

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
            notifyDataSetChanged();

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

}

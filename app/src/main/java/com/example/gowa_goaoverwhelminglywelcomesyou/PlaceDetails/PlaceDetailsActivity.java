package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gowa_goaoverwhelminglywelcomesyou.HelperClasses.SnapHelperByOne;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.example.gowa_goaoverwhelminglywelcomesyou.StringVariable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlaceDetailsActivity extends AppCompatActivity {

    boolean isupdating = false;
    ImageView likeView;
    int likeState = 0;
    String type,placeId = "se-cathedral";
    TextView placeName, placeDesc, placeLoc;
    RelativeLayout loader;
    ImageView back_imageView;
    RecyclerView imageRecycler;
    RecyclerView commentsRecycler;
    PlaceImageSliderAdapter imageAdapter;
    ArrayList<String> imageList = new ArrayList<>();
    Context mContext;
    CommentsAdapter commentsAdapter;
    EditText addCommentEditText;
    SimpleDraweeView addCommentUserImageView;
    Button addCommentButton;
    ArrayList<CommentModel> commentsList = new ArrayList<>();
    Button showOnMapsButton;
    String lattitude, longitude;
    String place_lattitude, place_longitude;
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    Location location;
    Button getFaresButton;
    TextView distanceView;
    ImageButton imageButton;
    TextToSpeech tts;
    TextView languageBox;
    String descEng, descHindi;
    int Language=0;
    String hindi ;

    int isSpeaking = 0;
    TextView history,facts;

    private FusedLocationProviderClient fusedLocationClient;
    int currentRatings = 5;




    String userUID, userName, userURI;

    //for cur   rent location
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    float distance_bw_one_and_two=0;
    private RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        placeId = getIntent().getStringExtra("placeId");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d("xxxx","location null");
                    return;
                }
                lattitude = ""+locationResult.getLastLocation().getLatitude();
                longitude = ""+locationResult.getLastLocation().getLatitude();
                location = locationResult.getLastLocation();
                for (Location location : locationResult.getLocations()) {
                    Log.d("xxxx",""+location.getLatitude());
                    Log.d("xxxx",""+location.getLongitude());
                }
            }
        };
        getPermissions();

        initialize();


    }

    private void getPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck != PackageManager
                .PERMISSION_GRANTED && permissionCheck1 != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_ACCESS_COARSE_LOCATION);


            }
        }
    }
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }

    }
    private LocationRequest locationRequest;
    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        requestingLocationUpdates = true;
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }


    @Override
    protected void onPause() {
        super.onPause();
        tts.stop();
//        tts.shutdown();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        stopLocationUpdates();


    }
    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void initialize() {
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.UK);

            }
        });


        likeView = findViewById(R.id.heartView);
        placeName = findViewById(R.id.placeName);
        placeLoc = findViewById(R.id.place_location);
        placeDesc = findViewById(R.id.place_description);
        loader = findViewById(R.id.place_details_loader);
        back_imageView = findViewById(R.id.back_imageView);
        imageRecycler = findViewById(R.id.image_recycler_view);
        commentsRecycler = findViewById(R.id.commentsRecyclerView);
        addCommentButton = findViewById(R.id.comment_add_button);
        addCommentEditText = findViewById(R.id.add_comment_edittext);
        addCommentUserImageView = findViewById(R.id.add_comment_user_image);
        showOnMapsButton = findViewById(R.id.locateButton);
        getFaresButton = findViewById(R.id.getFaresButton);
        distanceView = findViewById(R.id.distanceView);
        history = findViewById(R.id.history);
        facts = findViewById(R.id.facts);
        imageButton = findViewById(R.id.textToSpeechButton);
        languageBox = findViewById(R.id.languageBox);
        ratingBar = findViewById(R.id.rating_bar);

        ratingBar.setMax(5);
        ratingBar.setStepSize(1);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating, boolean fromUser) {
                if(isupdating) return;
                FirebaseDatabase.getInstance().getReference().child(StringVariable.PLACE_DETAILS).child(placeId).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Log.d("xxxx",dataSnapshot.toString());
                        String current = String.valueOf(dataSnapshot.getValue());
                        String[] arrOfStr = current.split("/", 5);

                        currentRatings = Integer.valueOf(arrOfStr[0]);
                        int ratingCount = Integer.valueOf(arrOfStr[1]);

                        int newratings = currentRatings+(int)rating;
                        int newcount = ratingCount+1;

                        FirebaseDatabase.getInstance().getReference().child(StringVariable.PLACE_DETAILS).child(placeId).child("rating").setValue(
                          newratings+"/"+newcount
                        );
                        FirebaseDatabase.getInstance().getReference().child(StringVariable.PLACE_DETAILS).child(placeId).child("ratingsBy").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(
                                rating
                        );


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(String.valueOf(languageBox.getText()).equalsIgnoreCase("en")){
                    String toSpeak = placeDesc.getText().toString();
//                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                }
else{
    tts.speak(descHindi, TextToSpeech.QUEUE_FLUSH,null);
                }

            }
        });
        languageBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(languageBox.getText()).equalsIgnoreCase("en")){
                    languageBox.setText("HI");
                    languageBox.setBackgroundResource(R.drawable.language_bg2);
                    tts.stop();
                    tts.shutdown();

                    tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            tts.setLanguage(new Locale("hin"));

                        }
                    });
                }
                else{
                    languageBox.setText("EN");
                    languageBox.setBackgroundResource(R.drawable.language_bg);

                    tts.stop();
                    tts.shutdown();

                    tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            tts.setLanguage(Locale.UK);

                        }
                    });

                }
            }
        });


        //todo remove this

        userName = "Aman Raj Sundram";
        userURI = "https://pbs.twimg.com/profile_images/986390757484347392/KYd9FCdc.jpg";
        userUID =  "FSs8fSDaDsFfsD8fSsFd8";

        //todo

        LinearSnapHelper linearSnapHelper = new SnapHelperByOne();
        linearSnapHelper.attachToRecyclerView(imageRecycler);



        showOnMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mapsIntent = new Intent(PlaceDetailsActivity.this,PlaceMapsActivity.class);
//                mapsIntent.putExtra("lat",lattitude);
//                mapsIntent.putExtra("long",longitude);
//                startActivity(mapsIntent);

//                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lattitude+","+longitude);
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=tourist plcaes", Float.parseFloat(place_lattitude), Float.parseFloat(place_longitude));
                Intent mapIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapIntent2.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent2);

//                Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+lattitude+","+longitude);
//
//// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//// Make the Intent explicit by setting the Google Maps package
//                mapIntent.setPackage("com.google.android.apps.maps");
//
//// Attempt to start an activity that can handle the Intent
//                startActivity(mapIntent);


            }
        });

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addCommentEditText.getText()==null ||
                        String.valueOf(addCommentEditText.getText()).equalsIgnoreCase("")){
                    Toast.makeText(PlaceDetailsActivity.this,"Please wite some comment to post.", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child(StringVariable.PLACE_DETAILS).child(placeId).child(StringVariable.COMMENTS);
                    String key = dbref.push().getKey();
                    Map<String, String> data = new HashMap<>();
                    data.put(StringVariable.COMMENT_POSTED_BY, userName);
                    data.put(StringVariable.COMMENT_IMAGE_URI, userURI);
                    data.put(StringVariable.COMMENT_TIME, String.valueOf(System.currentTimeMillis()/1000));
                    data.put(StringVariable.COMMENT_CONTENT, String.valueOf(addCommentEditText.getText()));

                    dbref.child(key).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(PlaceDetailsActivity.this,"Successfully posted.", Toast.LENGTH_SHORT).show();
                            addCommentEditText.setText("");

                        }
                    });
//
//                    dbref.child(key).child(StringVariable.COMMENT_POSTED_BY).setValue(userName);
//                    dbref.child(key).child(StringVariable.COMMENT_IMAGE_URI).setValue(userURI);
//                    dbref.child(key).child(StringVariable.COMMENT_CONTENT).setValue(String.valueOf(addCommentEditText.getText()));
//                    dbref.child(key).child(StringVariable.COMMENT_TIME).setValue(String.valueOf(System.currentTimeMillis()/1000)).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(PlaceDetailsActivity.this,"Successfully posted.",Toast.LENGTH_SHORT).show();
//                            addCommentEditText.setText("");
//
//                        }
//                    });



                }
            }
        });


        mContext = getApplicationContext();


        loader.setVisibility(View.VISIBLE);


        back_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        if(getIntent().getStringExtra("placeId")!=null)
        placeId = getIntent().getStringExtra("placeId");


        likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeState==0) {
                    likeView.setImageResource(R.drawable.ic_heart_filled);
                    likeState = 1;
                }
                else{
                    likeView.setImageResource(R.drawable.ic_heart);
                    likeState = 0;

                }
            }
        });


        getDataFromFirebase(type, placeId);
    }

    private void getDataFromFirebase(String type, String placeId) {
        Log.e("xxxxu",placeId);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariable.PLACE_DETAILS).child(placeId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loader.setVisibility(View.GONE);
                Log.e("yyyyy",dataSnapshot.toString());
                placeDesc.setText(String.valueOf(dataSnapshot.child(StringVariable.PLACE_DETAILS_DESC).getValue()));
                placeName.setText(String.valueOf(dataSnapshot.child(StringVariable.PLACE_DETAILS_NAME).getValue()));
                placeLoc.setText(String.valueOf(dataSnapshot.child(StringVariable.PLACE_DETAILS_LOCATION).getValue()));

                String current = String.valueOf(dataSnapshot.child("rating").getValue());
                String[] arrOfStr = current.split("/", 5);

                currentRatings = Integer.valueOf(arrOfStr[0]);
                int ratingCount = Integer.valueOf(arrOfStr[1]);

                ((RatingBar)findViewById(R.id.RatingBar_Id)).setRating(currentRatings/ratingCount);
                ((TextView)findViewById(R.id.rating_text)).setText(currentRatings/ratingCount+"  |  45 reviews");
                if(dataSnapshot.hasChild("ratingsBy")){
                    if(String.valueOf(dataSnapshot.child("ratingsBy").getValue()).contains(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        isupdating = true;
                        ratingBar.setRating(Float.parseFloat(String.valueOf(dataSnapshot.child("ratingsBy").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue())));
                    }
                }

//                imageList.clear();
                imageList.add(String.valueOf(dataSnapshot.child("img1").getValue()));
                imageList.add(String.valueOf(dataSnapshot.child("img2").getValue()));
                imageList.add(String.valueOf(dataSnapshot.child("img3").getValue()));
                imageList.add(String.valueOf(dataSnapshot.child("img4").getValue()));
                imageList.add(String.valueOf(dataSnapshot.child("img5").getValue()));

                lattitude = String.valueOf(dataSnapshot.child("lat").getValue());
                longitude = String.valueOf(dataSnapshot.child("long").getValue());

                history.setText(String.valueOf(dataSnapshot.child("History").getValue()));
                facts.setText(String.valueOf(dataSnapshot.child("Facts").getValue()));

                Location l1;
                place_lattitude = lattitude;
                place_longitude = longitude;

                descEng = String.valueOf(dataSnapshot.child(StringVariable.PLACE_DETAILS_DESC).getValue());
                descHindi = String.valueOf(dataSnapshot.child(StringVariable.PLACE_DETAILS_DESC_HINDI).getValue());



                if(location!=null) {
                    l1 = new Location("One");
                    l1.setLatitude(location.getLatitude());
                    l1.setLongitude(location.getLongitude());
                }
                else{
                    l1 = new Location("One");
                    l1.setLatitude(25.620058);
                    l1.setLongitude(85.173896);

                }

                Location l2=new Location("Two");
                l2.setLatitude(Double.parseDouble(lattitude));
                l2.setLongitude(Double.parseDouble(longitude));

                distance_bw_one_and_two=l1.distanceTo(l2)/1000;
                Log.e("xxxx",""+distance_bw_one_and_two);

                distanceView.setText(""+(int)distance_bw_one_and_two+" km");

                getFaresButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("xxxx",distance_bw_one_and_two+" lll");
                        Intent intent = new Intent(PlaceDetailsActivity.this,FaresDetailsActivity.class);
                        intent.putExtra("distance",distance_bw_one_and_two);
                        startActivity(intent);
                    }
                });
                commentsList.clear();

                findViewById(R.id.directions).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("google.navigation:q="+lattitude+","+longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });
                for(DataSnapshot ds : dataSnapshot.child(StringVariable.COMMENTS).getChildren()){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = "19/01/2020";
                    try {


                         dateString = formatter.format(new Date(Long.parseLong(String.valueOf(ds.child(StringVariable.COMMENT_TIME).getValue()))));

                    }
                    catch (Exception e) {
                    }
                        String date = "";

                    commentsList.add(new CommentModel(String.valueOf(ds.child(StringVariable.COMMENT_IMAGE_URI).getValue()), String.valueOf(ds.child(StringVariable.COMMENT_POSTED_BY).getValue()), String.valueOf(ds.child(StringVariable.COMMENT_CONTENT).getValue()),dateString, Long.parseLong(String.valueOf(ds.child(StringVariable.COMMENT_TIME).getValue()))));
                }

                Collections.sort(commentsList,new CustomComparator());

                imageAdapter = new PlaceImageSliderAdapter(mContext, imageList);
                imageRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                imageRecycler.setAdapter(imageAdapter);


                commentsAdapter = new CommentsAdapter(mContext, commentsList);


                commentsRecycler.setAdapter(commentsAdapter);
                commentsRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

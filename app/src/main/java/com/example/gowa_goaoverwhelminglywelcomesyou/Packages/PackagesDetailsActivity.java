package com.example.gowa_goaoverwhelminglywelcomesyou.Packages;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PackagesDetailsActivity extends AppCompatActivity {

    PackageDetailsModel packagesAdapter;
    RecyclerView recyclerView;
    PackageDetailsModel packageDetailsModel;
    ProgressDialog progressDialog ;
    String id  = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_package_details);

//        recyclerView = findViewById(R.id.recycler_view);


        progressDialog = new ProgressDialog(PackagesDetailsActivity.this);


        id = getIntent().getStringExtra("id");
        id = (id.split("/")[id.split("/").length -1 ]);
        Log.e("xxxx",(id.split("/")[id.split("/").length -1 ]));

//        packageDetailsModel = new ArrayList<>();
         getPackagesData();
//        packagesAdapter = new PackagesAdapter(getApplicationContext(), packagesList);
//
//
//        recyclerView.setAdapter(packagesAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));





    }

    private void getPackagesData() {
        progressDialog.setMessage("Loading");
        progressDialog.show();
        String Url = "https://sih-backend.herokuapp.com/toursDetails?id="+id;
        Log.e("xxxx-sent",Url);
        StringRequest stringRequest = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("xxxx",jsonObject.toString());
                    Log.e("xxxx",jsonObject.getString("title"));

                    JSONArray jsonArray = jsonObject.getJSONArray("img");
                    ((SimpleDraweeView)findViewById(R.id.top_image)).setImageURI(jsonArray.getString(0));
                    ((TextView)findViewById(R.id.package_name)).setText(jsonObject.getString("title"));
                    ((TextView)findViewById(R.id.package_price)).setText(jsonObject.getString("price"));
                    ((TextView)findViewById(R.id.rating_reviews)).setText(jsonObject.getString("rating")+" | "+jsonObject.getString("ratings_count"));
                    if(jsonObject.getString("highlight").equalsIgnoreCase("")){
                        findViewById(R.id.highlights_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Includes").equalsIgnoreCase("")){
                        findViewById(R.id.includes_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Overview").equalsIgnoreCase("")){
                        findViewById(R.id.overview_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Meal").equalsIgnoreCase("")){
                        findViewById(R.id.meals_header).setVisibility(View.GONE);
                    }if(jsonObject.getString("Activity").equalsIgnoreCase("")){
                        findViewById(R.id.activities_header).setVisibility(View.GONE);
                    }if(jsonObject.getString("Other Inclusions").equalsIgnoreCase("")){
                        findViewById(R.id.otherinclusions_header).setVisibility(View.GONE);
                    }if(jsonObject.getString("Things To Carry").equalsIgnoreCase("")){
                        findViewById(R.id.thingstocarry_header).setVisibility(View.GONE);
                    }if(jsonObject.getString("Advisory").equalsIgnoreCase("")){
                        findViewById(R.id.advisory_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Cancellation Policy").equalsIgnoreCase("")){
                        findViewById(R.id.cancel_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Refund Policy").equalsIgnoreCase("")){
                        findViewById(R.id.refund_header).setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("Booking Confirmation Policy").equalsIgnoreCase("")){
                        findViewById(R.id.bookingconfirm_header).setVisibility(View.GONE);
                    }






                    ((TextView)findViewById(R.id.highlights_tv)).setText(jsonObject.getString("highlight"));
                    ((TextView)findViewById(R.id.includes_tv)).setText(jsonObject.getString("Includes"));
                    ((TextView)findViewById(R.id.overview_tv)).setText(jsonObject.getString("Overview"));
                    ((TextView)findViewById(R.id.meals_tv)).setText(jsonObject.getString("Meal"));
                    ((TextView)findViewById(R.id.activities_tv)).setText(jsonObject.getString("Activity"));
                    ((TextView)findViewById(R.id.other_inclusions_tv)).setText(jsonObject.getString("Other Inclusions"));
                    ((TextView)findViewById(R.id.thing_to_carry_tv)).setText(jsonObject.getString("Things To Carry"));
                    ((TextView)findViewById(R.id.advisory)).setText(jsonObject.getString("Advisory"));
                    ((TextView)findViewById(R.id.tour_type_tv)).setText(jsonObject.getString(""));
                    ((TextView)findViewById(R.id.cancellation_policy_tv)).setText(jsonObject.getString("Cancellation Policy"));
                    ((TextView)findViewById(R.id.refund_policy_tv)).setText(jsonObject.getString("Refund Policy"));
                    ((TextView)findViewById(R.id.booking_confirmation_tv)).setText(jsonObject.getString("Booking Confirmation Policy"));

//                    Log.e("FLIGHT RESPONSE : ", jsonArray.length() + "");

//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        Log.e("FLIGHT RESP OBJECT: ", object + "");
//
//                        packageDetailsModel = new PackageDetailsModel(
//                                String.valueOf(object.getString("title")),
//                                String.valueOf(object.getString("ratings_count")),
//                                String.valueOf(object.getString("ratings_val")),
//                                String.valueOf(object.getString("price")),
//                                object.get("ratings"),
//                                object.getJSONObject("highlight"),
//                                object.getJSONObject("includes"),
//                                object.getJSONObject("overview"),
//                                object.getJSONObject("meal"),
//                                object.getJSONObject("activity"),
//                                object.getJSONObject("other_inclusions"),
//                                object.getJSONObject("activity"),
//                                object.getJSONObject("things_to_carry"),
//                                object.getJSONObject("advisory"),
//                                object.getJSONObject("tourtype"),
//                                object.getJSONObject("advisory"),
//                                object.getJSONObject("cancellation"),
//                                object.getJSONObject("refund"),
//                                object.getJSONObject("booking")
//
//
//                        ));
//                    }

//                    packagesAdapter.notifyDataSetChanged();

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();




                } catch (JSONException e) {
                    Log.e("FLIGHT RESPONSE : ", e + "");
                    Log.e("xxxx",e.toString());

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Anything you want
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}

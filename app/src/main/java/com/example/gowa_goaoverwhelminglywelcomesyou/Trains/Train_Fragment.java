package com.example.gowa_goaoverwhelminglywelcomesyou.Trains;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.FlightsModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.google.firebase.database.annotations.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Train_Fragment extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<FlightsModel> trainModelList;
    TrainAdapter flightsAdapter;
    String Url = "https://sih-backend.herokuapp.com/rails/?";
    ArrayAdapter<String> arrayAdapter;
    private StringRequest request;
    SearchView from;
    Button go;
    ListView listView;
    HashMap<String, String> map;
    ArrayList<String> sources;
    CalendarView calendarView;
    String DestinationCode;
    String Json, selectedDate;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    RelativeLayout upperSearchBar;
    LinearLayout calenderLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_fragment);
        recyclerView = (RecyclerView) findViewById(R.id.flights_recycler_view);
        from = findViewById(R.id.search);
        listView = findViewById(R.id.list_view);
        calendarView = findViewById(R.id.flight_cal);
        from.setActivated(false);
        from.setQueryHint("Source");
        from.onActionViewExpanded();
        from.setIconified(false);
        map = new HashMap<>();
        sources = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);
        upperSearchBar = findViewById(R.id.start_end);
        calenderLayout = findViewById(R.id.calenderLayout);

        Json = getAssetJsonData(this);

        try {
            JSONArray jsonArray = new JSONArray(Json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                map.put(obj.getString("display"), obj.getString("id"));
                sources.add(obj.getString("display"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("sources xxx", sources.toString() + "  i");
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sources);
        listView.setAdapter(arrayAdapter);
        from.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                from.setQuery(((TextView) view).getText(), false);
                DestinationCode = map.get(((TextView) view).getText());
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NotNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth+"/"+(month+1)+"/"+year;
            }
        });
        go = findViewById(R.id.go_button);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Loading Trains");
                progressDialog.show();
                calendarView.setVisibility(View.GONE);
                // TODO MAke url and request
//                to=6513&date=Fri,31stJan&from=3038
                Url = Url + "to=" + "677" + "&date=" + selectedDate + "&from=" + DestinationCode;
                Log.e("xcv", Url);
                getFlights();
                Url = "";
            }
        });

        trainModelList = new ArrayList<>();
        flightsAdapter = new TrainAdapter(this, trainModelList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(flightsAdapter);
    }

    public String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("stations.json");
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

    void getFlights(){

        Log.e("Entered Get Flight","Yes "+ Url);
        StringRequest stringRequest = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("FLIGHT RESPONSE : ", "received " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("trains");
                    Log.e("FLIGHT RESPONSE : ", jsonArray.length() + "");
                    if(jsonArray.length()==0){
                        findViewById(R.id.not_avail).setVisibility(View.VISIBLE);
                    }

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.e("FLIGHT RESP OBJECT: ", object + "");

                        trainModelList.add(new FlightsModel(
                                object.getString("number"),
                                object.getString("source_departure_time"),
                                object.getString("destination_arrival_time"),
                                object.getString("source_name"),
                                object.getString("destination_name"),
                                object.getString("end"),
                                object.getString("name"),
                                object.getString("destination_name"),
                                object.getString("travel_time"),
                                "GOA",
                                object.getString("source_name")));
                    }
                    flightsAdapter.notifyDataSetChanged();

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    upperSearchBar.setVisibility(View.GONE);
                    calenderLayout.setVisibility(View.GONE);



                } catch (JSONException e) {
                    Log.e("FLIGHT RESPONSE : ", e + "");

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FLIGHT RESPONSE : ", error + "");

                // Anything you want
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
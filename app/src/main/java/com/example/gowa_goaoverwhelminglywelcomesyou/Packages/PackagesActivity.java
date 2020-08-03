package com.example.gowa_goaoverwhelminglywelcomesyou.Packages;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gowa_goaoverwhelminglywelcomesyou.Hotels.PackagesModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PackagesActivity extends AppCompatActivity {

    PackagesAdapter packagesAdapter;
    RecyclerView recyclerView;
    ArrayList<PackagesModel> packagesList;
    ProgressDialog progressDialog;
    ArrayList<Integer> prices;
    EditText weight;
    int budget = 0;
    int i = 0;
    int bag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bag =0;
        setContentView(R.layout.activity_packages);
        weight = findViewById(R.id.weight);
        weight.setEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);
        prices = new ArrayList<>();
        progressDialog = new ProgressDialog(PackagesActivity.this);








        packagesList = new ArrayList<>();
        packagesAdapter = new PackagesAdapter(PackagesActivity.this, packagesList);


        recyclerView.setAdapter(packagesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        if(getIntent().getIntExtra("type",1) == 2){
            findViewById(R.id.package_data_intaker).setVisibility(View.GONE);
            getPackagesData(50000);
        }

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int budget  = Integer.parseInt(String.valueOf(weight.getText()));
                getPackagesData(budget);
            }
        });
    }

    private void getPackagesData(final int budget) {
        progressDialog.setMessage("Loading");
        progressDialog.show();
        String Url = "https://sih-backend.herokuapp.com/tours?page=1";


        StringRequest stringRequest = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    Log.e("FLIGHT RESPONSE : ", jsonArray.length() + "");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        if(Integer.parseInt(object.getString("price").replace("INR ","").replace(",",""))<=budget)
                        packagesList.add(new PackagesModel(
                                String.valueOf(object.getString("description")),
                                String.valueOf(object.getString("img")),
                                String.valueOf(object.getString("price")),
                                String.valueOf(object.getString("no_of_ratings")),
                                String.valueOf(object.getString("ratings")),
                                String.valueOf(object.getString("time")),
                                String.valueOf(object.getString("title")),
                                String.valueOf(object.getString("link"))));
                        prices.add(Integer.parseInt(object.getString("price").split(" ")[1].replace(",","")));
                        findViewById(R.id.package_data_intaker).setVisibility(View.GONE);
                        packagesAdapter.notifyDataSetChanged();
                    }


//                    weight.addTextChangedListener(new TextWatcher() {
//                        @Override
//                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                        }
//
//                        @Override
//                        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                        }
//
//                        @Override
//                        public void afterTextChanged(Editable s) {
//                            budget = Integer.parseInt(weight.getText().toString());
//                            while (bag<=budget)
//                            {
//                                bag += prices.get(i++);
//
//                            }
//                            prices.subList(0,i);
//                            packagesList.subList(0,i);
//                        }
//                    });



//                    int priceess[] = new int[prices.size()];
//                    SubSet_sum subSet_sum = new SubSet_sum();
//                    for(int i=0;i<prices.size();i++){
//                        priceess[i] = prices.get(i);
//                    }
//                    subSet_sum.printAllSubsets(priceess,prices.size(),budget);
//
//                    SubSet_sum subSet_sum = new SubSe
//

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();


                } catch (JSONException e) {
                    Log.e("FLIGHT RESPONSE : ", e + "");

                    if (progressDialog.isShowing())
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

//    class SubSet_sum
//    {
//        // dp[i][j] is going to store true if sum j is
//        // possible with array elements from 0 to i.
//        boolean[][] dp;
//
//        void display(ArrayList<Integer> v)
//        {
//            System.out.println(v);
//        }
//
//        // A recursive function to print all subsets with the
//        // help of dp[][]. Vector p[] stores current subset.
//        void printSubsetsRec(int arr[], int i, int sum,
//                             ArrayList<Integer> p)
//        {
//            // If we reached end and sum is non-zero. We print
//            // p[] only if arr[0] is equal to sun OR dp[0][sum]
//            // is true.
//            if (i == 0 && sum != 0 && dp[0][sum])
//            {
//                p.add(arr[i]);
//                display(p);
//                p.clear();
//                return;
//            }
//
//            // If sum becomes 0
//            if (i == 0 && sum == 0)
//            {
//                display(p);
//                p.clear();
//                return;
//            }
//
//            // If given sum can be achieved after ignoring
//            // current element.
//            if (dp[i-1][sum])
//            {
//                // Create a new vector to store path
//                ArrayList<Integer> b = new ArrayList<>();
//                b.addAll(p);
//                printSubsetsRec(arr, i-1, sum, b);
//            }
//
//            // If given sum can be achieved after considering
//            // current element.
//            if (sum >= arr[i] && dp[i-1][sum-arr[i]])
//            {
//                p.add(arr[i]);
//                printSubsetsRec(arr, i-1, sum-arr[i], p);
//            }
//        }
//
//        // Prints all subsets of arr[0..n-1] with sum 0.
//        void printAllSubsets(int arr[], int n, int sum)
//        {
//            if (n == 0 || sum < 0)
//                return;
//
//            // Sum 0 can always be achieved with 0 elements
//            boolean [][] dp = new boolean[n][sum + 1];
//            for (int i=0; i<n; ++i)
//            {
//                dp[i][0] = true;
//            }
//
//            // Sum arr[0] can be achieved with single element
//            if (arr[0] <= sum)
//                dp[0][arr[0]] = true;
//
//            // Fill rest of the entries in dp[][]
//            for (int i = 1; i < n; ++i)
//                for (int j = 0; j < sum + 1; ++j)
//                    dp[i][j] = (arr[i] <= j) ? (dp[i-1][j] ||
//                            dp[i-1][j-arr[i]])
//                            : dp[i - 1][j];
//            if (dp[n-1][sum] == false)
//            {
//                return;
//            }
//
//            // Now recursively traverse dp[][] to find all
//            // paths from dp[n-1][sum]
//            ArrayList<Integer> p = new ArrayList<>();
////            printSubsetsRec(arr, n-1, sum, p);
//
//        }
//
//        //Ravikant Bro>>> to test above functions
//        public void main(String args[])
//        {
//            int arr[] = {1, 2, 3, 4, 5};
//            int n = arr.length;
//            int sum = 10;
//            printAllSubsets(arr, n, sum);
//        }
//    }
}

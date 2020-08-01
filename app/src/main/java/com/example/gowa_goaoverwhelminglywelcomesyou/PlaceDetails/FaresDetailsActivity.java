package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;

import java.util.ArrayList;

public class FaresDetailsActivity extends AppCompatActivity {

    RecyclerView taxiRecycler;
    ArrayList<TaxiFareModal> fareList = new ArrayList<>();
    TaxiFaresAdapter taxiFaresAdapter;
    float distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fares_details);

        initialize();
//        TextView textView = findViewById(R.id.toolbar_text);
//        textView.setText("Taxi Fares");
//        setList();

        Log.e("yyyyx",""+getIntent().getFloatExtra("distance",0));
        if(getIntent().getFloatExtra("distance",0)!=0){
            setList();

            taxiFaresAdapter = new TaxiFaresAdapter(getApplicationContext(), fareList);

            taxiRecycler.setAdapter(taxiFaresAdapter);
            taxiRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }



    }

    private void initialize() {
        taxiRecycler = findViewById(R.id.taxi_recycler);
    }

    private void setList() {
        fareList.add(new TaxiFareModal("Non A/C Taxi","200","300","600","900","1200","1300","1560","1820","2080","2340","2600","26",getIntent().getFloatExtra("distance",0)));
        fareList.add(new TaxiFareModal("A/C Taxi","300","400","800","1100","1300","1400","1680","1960","2280","2520","2800","28",getIntent().getFloatExtra("distance",0)));
        fareList.add(new TaxiFareModal("MUV Taxi","500","700","900","1380","1840","2300","2760","3220","3680","4100","4500","46",getIntent().getFloatExtra("distance",0)));
        fareList.add(new TaxiFareModal("Luxury Sedan","-1","-1","-1","-1","-1","2000","-1","-1","-1","-1","3500","40",getIntent().getFloatExtra("distance",0)));
        fareList.add(new TaxiFareModal("Super Luxury","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","9000","90",getIntent().getFloatExtra("distance",0)));
    }
}

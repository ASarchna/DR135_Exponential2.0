package com.example.gowa_goaoverwhelminglywelcomesyou.Emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.FlightsAdapter;
import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.FlightsModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EmergencyActivity extends AppCompatActivity {

    private RecyclerView emergencyRecycler;
    private EmergencyAdapter emergencyAdapter;
    private ArrayList<EmergencyModel> emergencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        emergencyList.add(new EmergencyModel("Police", "https://cdn.iconscout.com/icon/free/png-256/police-1659481-1410003.png", "", 100));
        emergencyList.add(new EmergencyModel("Fire", "https://image.flaticon.com/icons/svg/206/206877.svg", "", 101));
        emergencyList.add(new EmergencyModel("Ambulance", "https://image.flaticon.com/icons/png/512/1823/1823735.png", "", 102));
        emergencyList.add(new EmergencyModel("Emergency Service", "https://image.flaticon.com/icons/png/512/2840/2840342.png", "", 108));
        emergencyList.add(new EmergencyModel("Hotline", "https://image.flaticon.com/icons/png/512/1048/1048490.png", "", 1098));
        emergencyList.add(new EmergencyModel("Helpline", "", "", 2412121));

        emergencyRecycler = findViewById(R.id.emergency_recycler);
        emergencyAdapter = new EmergencyAdapter(this, emergencyList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        emergencyRecycler.setLayoutManager(layoutManager);
        emergencyRecycler.setAdapter(emergencyAdapter);

        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(EmergencyActivity.this,R.color.colorPrimary));


    }
    public void back(View v){
        onBackPressed();
    }

    class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder> {

        private Context context;
        private ArrayList<EmergencyModel> list;

        public EmergencyAdapter(Context context, ArrayList<EmergencyModel> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public EmergencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.contact_item, parent, false);
            EmergencyAdapter.ViewHolder holder = new EmergencyAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull EmergencyAdapter.ViewHolder holder, int position) {

            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
            Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));

            final EmergencyModel item = list.get(position);
            holder.img.setImageURI(item.getImage());
            holder.name.setText(item.getName());
            holder.number.setText(""+item.getPhone());
            holder.callIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.getPhone()));
                    if (ActivityCompat.checkSelfPermission(EmergencyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public SimpleDraweeView img;
            public TextView name, number;
            public ImageView callIcon;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.requestProfilePic);
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.phone);
                callIcon = itemView.findViewById(R.id.requestTime);
            }
        }
    }

}

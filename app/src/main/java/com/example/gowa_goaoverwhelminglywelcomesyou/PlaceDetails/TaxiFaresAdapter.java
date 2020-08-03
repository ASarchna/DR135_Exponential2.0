package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TaxiFaresAdapter extends RecyclerView.Adapter<TaxiFaresAdapter.ViewHolder> {

    ArrayList<TaxiFareModal> list = new ArrayList<>();
    Context context;

    public TaxiFaresAdapter(Context context, ArrayList<TaxiFareModal> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taxiType, price;
        ImageView img;


        ViewHolder(View itemView) {
            super(itemView);
            this.taxiType = itemView.findViewById(R.id.taxi_type);
            this.price = itemView.findViewById(R.id.taxi_fare);
            this.img = itemView.findViewById(R.id.call);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.taxi_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        float price = 0;
        float distance = list.get(position).getDistance();
        Log.e("yyyyzzzz", distance + "");
        holder.taxiType.setText(list.get(position).getType());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8759384732"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

        distance = list.get(position).getDistance();
        if(distance>100){
            price = Float.parseFloat(list.get(position).getKm100())+
                    list.get(position).getDistance()* Float.parseFloat(list.get(position).getKm100());
        }
        holder.price.setText("Rs. "+ new DecimalFormat("##,##,##0").format(price));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

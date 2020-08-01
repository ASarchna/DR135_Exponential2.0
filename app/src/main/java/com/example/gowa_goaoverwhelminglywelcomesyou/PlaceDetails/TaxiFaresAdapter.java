package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TaxiFaresAdapter extends RecyclerView.Adapter<TaxiFaresAdapter.ViewHolder>{

    ArrayList<TaxiFareModal> list = new ArrayList<>();
    Context context;

    public TaxiFaresAdapter(Context context, ArrayList<TaxiFareModal> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taxiType, price;


        ViewHolder(View itemView) {
            super(itemView);
            this.taxiType = itemView.findViewById(R.id.taxi_type);
            this.price = itemView.findViewById(R.id.taxi_fare);
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
        Log.e("yyyyzzzz",distance+"");
        holder.taxiType.setText(list.get(position).getType());
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

package com.example.gowa_goaoverwhelminglywelcomesyou.Trains;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection.ReadingBlogsActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Flights.FlightsModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;


import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FlightsModel> list;

    public TrainAdapter(Context context, ArrayList<FlightsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.flight_card_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));

        FlightsModel item = list.get(position);
        holder.img.setImageResource(R.drawable.ic_train);
        holder.land_place.setText(item.getDestination());
        holder.leave_place.setText(item.getOrigin());
        holder.time_to_land.setText(item.getArrival_time());
        holder.time_to_leave.setText(item.getDeparture_time());
        holder.total_time.setText(item.getFly_duration());
//        holder.price.setText(format.format(new BigDecimal(item.getPrice())));
        holder.price.setText(item.getAirlines());
        holder.flight_name.setText(item.getAirlines());
        holder.flightCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ReadingBlogsActivity.class);
                i.putExtra("URL","https://www.irctc.co.in/nget/train-search");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;
        public SimpleDraweeView img;
        public TextView flight_name, price, time_to_leave, time_to_land, total_time, leave_place, land_place;
        public CardView flightCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.flight_logo);
            price = itemView.findViewById(R.id.price);
            time_to_leave = itemView.findViewById(R.id.time_to_leave);
            time_to_land = itemView.findViewById(R.id.time_to_land);
            total_time = itemView.findViewById(R.id.total_time);
            leave_place = itemView.findViewById(R.id.leave_place);
            land_place = itemView.findViewById(R.id.landing_place);
            flight_name = itemView.findViewById(R.id.flight_name);
            flightCard = itemView.findViewById(R.id.flightCard);
        }
    }
}

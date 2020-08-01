package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;

public class PlaceImageSliderAdapter extends RecyclerView.Adapter<PlaceImageSliderAdapter.ViewHolder> {

    private ArrayList<String> list;
    private Context context;

    public PlaceImageSliderAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imagecard);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.place_details_image_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = list.get(position);
        holder.imageView.setImageURI(item);
 }

    @Override
    public int getItemCount() {
        return list.size();
    }

  }

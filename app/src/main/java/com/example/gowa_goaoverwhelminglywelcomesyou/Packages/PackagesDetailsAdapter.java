package com.example.gowa_goaoverwhelminglywelcomesyou.Packages;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.Hotels.PackagesModel;
import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;

public class PackagesDetailsAdapter extends RecyclerView.Adapter<PackagesDetailsAdapter.ViewHolder>{

    ArrayList<PackagesModel> list = new ArrayList<>();
    Context context;

    public PackagesDetailsAdapter(Context context, ArrayList<PackagesModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView packageImage;
        TextView packageName, packagePrice, packageRating;


        ViewHolder(View itemView) {
            super(itemView);
            this.packageImage = itemView.findViewById(R.id.packageImage);
            this.packageName = itemView.findViewById(R.id.package_name);
            this.packagePrice = itemView.findViewById(R.id.package_price);
            this.packageRating = itemView.findViewById(R.id.rating_reviews);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_package, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.packageImage.setImageURI(list.get(position).getImg());
        holder.packageName.setText(list.get(position).getTitle());
        holder.packageRating.setText(list.get(position).getRatings()+" | "+list.get(position).getNo_of_ratings()+" reviews");
        holder.packagePrice.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

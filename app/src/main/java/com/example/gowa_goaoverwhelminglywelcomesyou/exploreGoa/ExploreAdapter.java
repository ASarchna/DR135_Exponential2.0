package com.example.gowa_goaoverwhelminglywelcomesyou.exploreGoa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ExploreModel> list;

    public ExploreAdapter(Context context, ArrayList<ExploreModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.explore_card_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExploreModel item=list.get(position);
        holder.simpleDraweeView.setImageURI(item.getImage());
        holder.explore_text.setText(item.getName());
        Log.e("xxxx",item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public SimpleDraweeView simpleDraweeView;
       public TextView explore_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView=(SimpleDraweeView)itemView.findViewById(R.id.imageview);
            explore_text=(TextView)itemView.findViewById(R.id.explore_place);


        }
    }
}

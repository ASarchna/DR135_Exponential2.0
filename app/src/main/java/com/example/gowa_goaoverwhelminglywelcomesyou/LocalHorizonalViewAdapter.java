package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.PlaceDetailsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LocalHorizonalViewAdapter extends RecyclerView.Adapter<LocalHorizonalViewAdapter.LocalHorizontalViewHolder>{
        private List<LocalRecommendationCard> localRecommendationList;
        Context context;

        public LocalHorizonalViewAdapter(Context context, List<LocalRecommendationCard> horizontalGrocderyList){
            this.localRecommendationList = horizontalGrocderyList;
            this.context = context;
        }

        @Override
        public LocalHorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the layout file
            View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_local_recommendation_card, parent, false);
            LocalHorizontalViewHolder gvh = new LocalHorizontalViewHolder(groceryProductView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(LocalHorizontalViewHolder holder, final int position) {

            holder.imageView.setImageURI(localRecommendationList.get(position).getImageURI());
            holder.txtview.setText(localRecommendationList.get(position).getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaceDetailsActivity.class);
                    intent.putExtra("placeId",localRecommendationList.get(position).getPlaceId());
                    Log.e("xxxxk",localRecommendationList.get(position).getPlaceId());
                    context.startActivity(intent );
                    String productName = localRecommendationList.get(position).getDescription().toString();
                }
            });
        }

        @Override
        public int getItemCount() {
            return localRecommendationList.size();
        }
        public class LocalHorizontalViewHolder extends RecyclerView.ViewHolder {
            SimpleDraweeView imageView;
            TextView txtview;
            public LocalHorizontalViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.localRecyclerImageView);
                txtview=view.findViewById(R.id.localRecyclerTitle);
            }


        }
    }


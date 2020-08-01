package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowa_goaoverwhelminglywelcomesyou.R;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    ArrayList<CommentModel> list = new ArrayList<>();
    Context context;

    public CommentsAdapter(Context context, ArrayList<CommentModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView userImageView;
        TextView username, content, time;


        ViewHolder(View itemView) {
            super(itemView);
            this.userImageView = itemView.findViewById(R.id.userImageVIew);
            this.username = itemView.findViewById(R.id.userName);
            this.time = itemView.findViewById(R.id.comment_time);
            this.content = itemView.findViewById(R.id.comment_content);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userImageView.setImageURI(list.get(position).getImageURI());
        holder.username.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getTime());
        holder.content.setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

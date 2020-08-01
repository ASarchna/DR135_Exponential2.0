package com.example.gowa_goaoverwhelminglywelcomesyou.BlogSection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {

    private ArrayList<BlogModal> list;
    private Context context;

    public BlogListAdapter(Context context, ArrayList<BlogModal> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final BlogModal item = list.get(position);

        holder.titleView.setText(item.getTitle());
        holder.imageView.setImageURI(Uri.parse(item.getImgURL()));
        holder.completeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ReadingBlogsActivity.class);
                i.putExtra("URL",item.getURL());
                Log.d("Opening Blog :" , item.getURL());
                context.startActivity(i);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;
        TextView titleView, descView;
        View completeCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.blog_img);
            this.titleView = itemView.findViewById(R.id.blog_title);
            this.completeCard = itemView.findViewById(R.id.complete_card);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.element_blog, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BlogModal {
        String title, imgURL,desc,URL;

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public BlogModal(String title, String imgURL, String desc, String url) {
            this.title = title;
            this.imgURL = imgURL;
            this.desc = desc;
            this.URL = url;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getImgURL() {
            return imgURL;
        }
    }
}

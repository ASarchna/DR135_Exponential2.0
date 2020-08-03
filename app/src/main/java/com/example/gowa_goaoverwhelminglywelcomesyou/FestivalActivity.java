package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class FestivalActivity extends AppCompatActivity {

    FestivalModel data;
    TextView name, location, date, desc;
    SimpleDraweeView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_details);

        try {
            data = (FestivalModel) getIntent().getExtras().getSerializable("festival");
        }
        catch (Exception e){

        }
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM");
        String dates = df.format(new Date(data.getDate().getYear(), data.getDate().getMonth()-1, data.getDate().getDay()));


        name = findViewById(R.id.festival_name);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        desc = findViewById(R.id.desc);
        img = findViewById(R.id.top_image);

        name.setText(data.name);
        date.setText(dates);
        location.setText(data.location);
        desc.setText("While most will relate the state of Goa with beaches, liquor and modernity, this beachy haven is in every sense a land of culture, heritage and traditions. Every year, a large number of festivals are celebrated throughout the Goan territory which serves as vital attractions for tourists and locals alike. Let's take a look at some of the most celebrated festivals of Goa.\n\nPerhaps the most famous of all festivals, the Carnival is the epitome of celebrations in Goa. It is celebrated for three days and three nights and preceded by the month of Lent (February), this festival brings alive every nook and corner of Goa. Introduced by the Portuguese colonial rule in the 18th century, this festival is that time of the year when the legendary King Momo takes over the operations of the festivals and events.\nthe most fun-filled festival in Goa,  is meant to be a feast of food, drinks, music, dance and fun - before the 40 day period of fasting and absenteeism of meat in the month of Lent. The days are marked by colourful and musical parades which initiate in the capital Panaji and travel throughout the state making it one of the most popular festivals of Goa. The evenings are kept for gala balls, and people engage in partying on the streets all through the night. The float parade in Panaji is led by King Momo, who floats a specially decorated float to declare the initiation of eating, drinking and partying.\n" +
                "\n" + "Though the Carnival was on a decline in the past few years, yet the efforts of Goa Tourism Department and the state government have ensured that not only is the festival revived but also contributes to attracting tourists for the state. The Carnival just cannot be missed.");
        img.setImageURI(data.image);

    }
}

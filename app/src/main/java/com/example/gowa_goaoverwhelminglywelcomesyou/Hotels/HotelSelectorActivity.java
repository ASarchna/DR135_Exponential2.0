package com.example.gowa_goaoverwhelminglywelcomesyou.Hotels;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gowa_goaoverwhelminglywelcomesyou.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HotelSelectorActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    EditText fromDate,toDate;

    TextView noAdults, noChilds, noRooms;

    String month1, date1, year1, month2, date2, year2, to,child="0",adults="2",rooms="1";
    int dateType = 0;

    Button search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_selector);

        fromDate = (EditText) findViewById(R.id.fromDate);
        toDate = (EditText) findViewById(R.id.toDate);
        noAdults = (TextView) findViewById(R.id.tv_adults);
        noChilds = (TextView) findViewById(R.id.tv_children);
        noRooms = (TextView) findViewById(R.id.tv_rooms);
        search_button = findViewById(R.id.search_button);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(dateType==0){
                    date1 = String.valueOf(dayOfMonth);
                    year1 = String.valueOf(year);
                    month1 = String.valueOf(monthOfYear);

                }
                else{
                    date2 = String.valueOf(dayOfMonth);
                    year2 = String.valueOf(year);
                    month2 = String.valueOf(monthOfYear);

                }
                updateLabel(dateType);
            }

        };

        fromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateType = 0;
                // TODO Auto-generated method stub
                new DatePickerDialog(HotelSelectorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateType = 1;
                // TODO Auto-generated method stub
                new DatePickerDialog(HotelSelectorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        noAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(HotelSelectorActivity.this);

                d.setTitle("NumberPicker");
                d.setContentView(R.layout.number_selector_dialog);
                Button b1 = (Button) d.findViewById(R.id.button1);
                Button b2 = (Button) d.findViewById(R.id.button2);
                final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
                np.setMaxValue(100);
                np.setMinValue(0);
                np.setWrapSelectorWheel(false);
                b1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        noAdults.setText(String.valueOf(np.getValue()));
                        adults = String.valueOf(np.getValue());
                        d.dismiss();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();
                Window window = d.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);




            }
        });




     noChilds.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog d = new Dialog(HotelSelectorActivity.this);

            d.setTitle("NumberPicker");
            d.setContentView(R.layout.number_selector_dialog);
            Button b1 = (Button) d.findViewById(R.id.button1);
            Button b2 = (Button) d.findViewById(R.id.button2);
            final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
            np.setMaxValue(100);
            np.setMinValue(0);
            np.setWrapSelectorWheel(false);
            b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    noChilds.setText(String.valueOf(np.getValue()));
                    child = String.valueOf(np.getValue());
                    d.dismiss();
                }
            });
            b2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            d.show();
            Window window = d.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);




        }
    });

        noRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(HotelSelectorActivity.this);

                d.setTitle("NumberPicker");
                d.setContentView(R.layout.number_selector_dialog);
                Button b1 = (Button) d.findViewById(R.id.button1);
                Button b2 = (Button) d.findViewById(R.id.button2);
                final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
                np.setMaxValue(100);
                np.setMinValue(0);
                np.setWrapSelectorWheel(false);
                b1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        noRooms.setText(String.valueOf(np.getValue()));
                        rooms = String.valueOf(np.getValue());

                        d.dismiss();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();
                Window window = d.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);




            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelSelectorActivity.this, HotelsTabbedActivity.class);
                intent.putExtra("date1",date1);
                intent.putExtra("year1",year1);
                intent.putExtra("month1",month1);

                intent.putExtra("date2",date2);
                intent.putExtra("year2",year2);
                intent.putExtra("month2",month2);

                intent.putExtra("rooms",rooms);
                intent.putExtra("child",child);
                intent.putExtra("adults",adults);
                startActivity(intent);

            }
        });



}


    private void updateLabel(int dateType) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if(dateType==0) {
            fromDate.setText(sdf.format(myCalendar.getTime()));
        }


        if(dateType==1) {
            toDate.setText(sdf.format(myCalendar.getTime()));
        }

    }

}

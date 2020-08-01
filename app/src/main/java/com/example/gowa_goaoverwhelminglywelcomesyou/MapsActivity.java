package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {

    private MapView mapView;
    ArrayList<MonumentsModel> monumentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, "pk.eyJ1IjoiYXJzdW5kcmFtIiwiYSI6ImNrNWU0cHoxdDAwa2EzbW5tcTEwcGNreW0ifQ.eD0eBcMOmA2SM0_03t6p_w");

        setContentView(R.layout.maps_activity);

        init();

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });
                // Create an Icon object for the marker to use
//                IconFactory iconFactory = IconFactory.getInstance(MapsActivity.this);
//                Icon icon = iconFactory.fromResource(R.drawable.marker);
                /*
                TODO Redesign icons on the map
                 */



                putMarkers(mapboxMap);

                }
        });
    }

    private void putMarkers(MapboxMap mapboxMap) {
        for(int i=0; i<monumentsList.size();i++){
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(monumentsList.get(i).lattitude, monumentsList.get(i).longitude))
                    .title(monumentsList.get(i).monumentName));


        }

    }

    private void init() {
        monumentsList = new ArrayList<>();
        monumentsList.add(StringVariable.AGUADA_FORT);
        monumentsList.add(StringVariable.AZAD_MAIDAN);
        monumentsList.add(StringVariable.BASILLICA_OF_BOM_JESUS);
        monumentsList.add(StringVariable.CHAPORA_FORT);
        monumentsList.add(StringVariable.CHURCH_OF_ST_CAJETAN);
        monumentsList.add(StringVariable.FORT_TIRACOL);
        monumentsList.add(StringVariable.CHURCH_OF_ST_FRANCIS_OF_ASSISI);
        monumentsList.add(StringVariable.SE_CATHEDRAL);
        monumentsList.add(StringVariable.REIS_MAGOS_FORT);
    }


}

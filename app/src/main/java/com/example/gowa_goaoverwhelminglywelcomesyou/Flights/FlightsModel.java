package com.example.gowa_goaoverwhelminglywelcomesyou.Flights;

import androidx.lifecycle.ViewModel;

public class FlightsModel extends ViewModel {

    private String images,departure_time,arrival_time,deep_link,stoppage,price,airlines,stops,fly_duration,destination,origin;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public FlightsModel(String images, String departure_time, String arrival_time, String deep_link, String stoppage, String price, String airlines, String stops, String fly_duration, String destination, String origin) {
        this.images = images;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.deep_link = deep_link;
        this.stoppage = stoppage;
        this.price = price;
        this.airlines = airlines;
        this.stops = stops;
        this.fly_duration = fly_duration;
        this.destination = destination;
        this.origin = origin;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeep_link() {
        return deep_link;
    }

    public void setDeep_link(String deep_link) {
        this.deep_link = deep_link;
    }

    public String getStoppage() {
        return stoppage;
    }

    public void setStoppage(String stoppage) {
        this.stoppage = stoppage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getFly_duration() {
        return fly_duration;
    }

    public void setFly_duration(String fly_duration) {
        this.fly_duration = fly_duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

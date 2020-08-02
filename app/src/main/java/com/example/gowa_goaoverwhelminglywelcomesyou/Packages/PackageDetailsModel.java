package com.example.gowa_goaoverwhelminglywelcomesyou.Packages;

import org.json.JSONObject;


public class PackageDetailsModel {

    String title, ratings_count, rating_val, price;
            JSONObject highlight,includes, oveview, meal, activity, other_inclusions, things_to_carry, advisory, tourtype, cancellation, refund, booking;

    public PackageDetailsModel(String title, String ratings_count, String rating_val, String price, JSONObject highlight, JSONObject includes, JSONObject oveview, JSONObject meal, JSONObject activity, JSONObject other_inclusions, JSONObject things_to_carry, JSONObject advisory, JSONObject tourtype, JSONObject cancellation, JSONObject refund, JSONObject booking) {
        this.title = title;
        this.ratings_count = ratings_count;
        this.rating_val = rating_val;
        this.price = price;
        this.highlight = highlight;
        this.includes = includes;
        this.oveview = oveview;
        this.meal = meal;
        this.activity = activity;
        this.other_inclusions = other_inclusions;
        this.things_to_carry = things_to_carry;
        this.advisory = advisory;
        this.tourtype = tourtype;
        this.cancellation = cancellation;
        this.refund = refund;
        this.booking = booking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getRating_val() {
        return rating_val;
    }

    public void setRating_val(String rating_val) {
        this.rating_val = rating_val;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public JSONObject getHighlight() {
        return highlight;
    }

    public void setHighlight(JSONObject highlight) {
        this.highlight = highlight;
    }

    public JSONObject getIncludes() {
        return includes;
    }

    public void setIncludes(JSONObject includes) {
        this.includes = includes;
    }

    public JSONObject getOveview() {
        return oveview;
    }

    public void setOveview(JSONObject oveview) {
        this.oveview = oveview;
    }

    public JSONObject getMeal() {
        return meal;
    }

    public void setMeal(JSONObject meal) {
        this.meal = meal;
    }

    public JSONObject getActivity() {
        return activity;
    }

    public void setActivity(JSONObject activity) {
        this.activity = activity;
    }

    public JSONObject getOther_inclusions() {
        return other_inclusions;
    }

    public void setOther_inclusions(JSONObject other_inclusions) {
        this.other_inclusions = other_inclusions;
    }

    public JSONObject getThings_to_carry() {
        return things_to_carry;
    }

    public void setThings_to_carry(JSONObject things_to_carry) {
        this.things_to_carry = things_to_carry;
    }

    public JSONObject getAdvisory() {
        return advisory;
    }

    public void setAdvisory(JSONObject advisory) {
        this.advisory = advisory;
    }

    public JSONObject getTourtype() {
        return tourtype;
    }

    public void setTourtype(JSONObject tourtype) {
        this.tourtype = tourtype;
    }

    public JSONObject getCancellation() {
        return cancellation;
    }

    public void setCancellation(JSONObject cancellation) {
        this.cancellation = cancellation;
    }

    public JSONObject getRefund() {
        return refund;
    }

    public void setRefund(JSONObject refund) {
        this.refund = refund;
    }

    public JSONObject getBooking() {
        return booking;
    }

    public void setBooking(JSONObject booking) {
        this.booking = booking;
    }
}

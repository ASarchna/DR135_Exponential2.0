package com.example.gowa_goaoverwhelminglywelcomesyou.Hotels;

public class PackagesModel {
    String description,img, price, no_of_ratings, ratings, time, title, link;


    public PackagesModel(String description, String img, String price, String no_of_ratings, String ratings, String time, String title, String link) {
        this.description = description;
        this.img = img;
        this.price = price;
        this.no_of_ratings = no_of_ratings;
        this.ratings = ratings;
        this.time = time;
        this.title = title;
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNo_of_ratings() {
        return no_of_ratings;
    }

    public void setNo_of_ratings(String no_of_ratings) {
        this.no_of_ratings = no_of_ratings;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package com.example.gowa_goaoverwhelminglywelcomesyou;

import java.io.Serializable;
import java.util.Date;

public class FestivalModel implements Serializable,Cloneable{
    String name, location, image, desc;
    int month;
    Date date;

    public FestivalModel(String desc, String name, String location, String image, int month, Date date) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.month = month;
        this.date = date;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

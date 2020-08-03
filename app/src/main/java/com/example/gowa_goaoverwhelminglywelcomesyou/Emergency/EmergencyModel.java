package com.example.gowa_goaoverwhelminglywelcomesyou.Emergency;

public class EmergencyModel {
        private String name,image, location;
        private int phone;

    public EmergencyModel(String name, String image, String location, int phone) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}


package com.example.gowa_goaoverwhelminglywelcomesyou;

public class MonumentsModel {

    public String monumentName;
    public Double lattitude;
    public Double longitude;
    public String description;

    public MonumentsModel(String monumentName, Double lattitude, Double longitude, String description) {
        this.monumentName = monumentName;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.description = description;
    }

    public MonumentsModel() {
    }

    public String getMonumentName() {
        return monumentName;
    }

    public void setMonumentName(String monumentName) {
        this.monumentName = monumentName;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

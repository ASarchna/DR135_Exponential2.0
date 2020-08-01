package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

public class TaxiFareModal {
    String type;
    String km5;
    String km10;
    String km20;
    String km30;
    String km40;
    String km50;

    public TaxiFareModal(String type, String km5, String km10, String km20, String km30, String km40, String km50, String km60, String km70, String km80, String km90, String km100, String extra, float distance) {
        this.type = type;
        this.km5 = km5;
        this.km10 = km10;
        this.km20 = km20;
        this.km30 = km30;
        this.km40 = km40;
        this.km50 = km50;
        this.km60 = km60;
        this.km70 = km70;
        this.km80 = km80;
        this.km90 = km90;
        this.km100 = km100;
        this.extra = extra;
        this.distance = distance;
    }

    String km60;
    String km70;
    String km80;
    String km90;
    String km100;
    String extra;
    float distance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKm5() {
        return km5;
    }

    public void setKm5(String km5) {
        this.km5 = km5;
    }

    public String getKm10() {
        return km10;
    }

    public void setKm10(String km10) {
        this.km10 = km10;
    }

    public String getKm20() {
        return km20;
    }

    public void setKm20(String km20) {
        this.km20 = km20;
    }

    public String getKm30() {
        return km30;
    }

    public void setKm30(String km30) {
        this.km30 = km30;
    }

    public String getKm40() {
        return km40;
    }

    public void setKm40(String km40) {
        this.km40 = km40;
    }

    public String getKm50() {
        return km50;
    }

    public void setKm50(String km50) {
        this.km50 = km50;
    }

    public String getKm60() {
        return km60;
    }

    public void setKm60(String km60) {
        this.km60 = km60;
    }

    public String getKm70() {
        return km70;
    }

    public void setKm70(String km70) {
        this.km70 = km70;
    }

    public String getKm80() {
        return km80;
    }

    public void setKm80(String km80) {
        this.km80 = km80;
    }

    public String getKm90() {
        return km90;
    }

    public void setKm90(String km90) {
        this.km90 = km90;
    }

    public String getKm100() {
        return km100;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setKm100(String km100) {
        this.km100 = km100;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}

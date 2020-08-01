package com.example.gowa_goaoverwhelminglywelcomesyou;

public class LocalRecommendationCard {
    String name,description,views;
    String imageURI;
    String placeId;


    public LocalRecommendationCard(String name, String description, String views, String imageURI, String placeId) {
        this.name = name;
        this.description = description;
        this.views = views;
        this.placeId = placeId;
        this.imageURI = imageURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}

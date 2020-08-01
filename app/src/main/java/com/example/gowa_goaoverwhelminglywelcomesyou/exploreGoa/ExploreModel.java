package com.example.gowa_goaoverwhelminglywelcomesyou.exploreGoa;

public class ExploreModel {
    private String image,Name;

    public ExploreModel(String image, String name) {
        this.image = image;
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

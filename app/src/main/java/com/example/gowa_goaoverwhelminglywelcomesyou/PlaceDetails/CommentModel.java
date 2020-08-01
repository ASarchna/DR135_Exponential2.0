package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;


public class CommentModel {
    private String imageURI;
    private String name;
    private String content;
    private String time;
    private long timestamp;

    public CommentModel(String imageURI, String name, String content, String time, long timestamp) {
        this.imageURI = imageURI;
        this.name = name;
        this.content = content;
        this.time = time;
        this.timestamp = timestamp;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
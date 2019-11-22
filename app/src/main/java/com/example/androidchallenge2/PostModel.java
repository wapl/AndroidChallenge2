package com.example.androidchallenge2;


import java.sql.Timestamp;


public class PostModel {

    private String Title;
    private String Description;
    private Timestamp timestamp;

    public PostModel(String Title,String description)
    {
        this.setTitle(Title);
        this.setDescription(description);
        setTimestamp(new Timestamp(System.currentTimeMillis()));
    }
    public void setTitle(String Title)
    {
        this.Title=Title;
    }
    public void setDescription(String Description)
    {
        this.Description=Description;
    }


    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

package com.example.androidchallenge2;


import java.security.Timestamp;
import java.sql.Time;

import java.util.Map;


public class PostModel {

    private String Title;
    private String Description;
    private long timestamp;
    private String Category;
    private String ID;



    public PostModel(String Title,String Description,String Category,String ID)
    {
        this.Title=Title;
        this.Description=Description;
        this.timestamp=System.currentTimeMillis()/1000;
        this.Category=Category;
        this.ID=ID;

    }
    public PostModel()
    {

        //this is required

    }
    public long getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp(long timestamp)
    {
        this.timestamp=timestamp;
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


    public void setCategory(String Category)
    {
        this.Category=Category;
    }
    public String getCategory()
    {
        return Category;
    }

    public String getId()
    {
        return ID;

    }
    public void setId(String ID)
    {
        this.ID=ID;
    }


}

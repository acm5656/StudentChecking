package com.example.checkingsystem.beans;

/**
 * Created by 那年.盛夏 on 2017/3/10.
 */

public class Item {
    private int pictureID;
    private String name;

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name,int pictureID) {
        this.pictureID = pictureID;
        this.name = name;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "pictureID=" + pictureID +
                ", name='" + name + '\'' +
                '}';
    }
}

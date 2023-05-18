package com.example.mobileproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

class ArtistModel {

    private  String rank;
    private  String artist;

    public ArtistModel(String rank, String artist) {
        this.rank = rank;
        this.artist = artist;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
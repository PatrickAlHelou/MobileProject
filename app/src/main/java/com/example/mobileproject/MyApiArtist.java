package com.example.mobileproject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

interface MyApiArtist
{

    @GET("top_20_by_followers")
    Call<ArrayList<ArtistModel>> callModel();
}
package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Artist extends AppCompatActivity {

    private ArrayList<ArtistModel> modelArrayList;
    private MyApiArtist myApiArtist;
    private ListView listView;

    private String baseUrl = "https://spotify81.p.rapidapi.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_artist);
        listView = findViewById(R.id.listView);
        modelArrayList = new ArrayList<>();

        displayData();
    }

    private void displayData() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-RapidAPI-Key", "ee2616c265mshb7b83e55f7f5887p1b9f85jsn8077a819f8de")
                        .header("X-RapidAPI-Host", "spotify81.p.rapidapi.com")
//                                                  .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        myApiArtist = retrofit.create(MyApiArtist.class);

        Call<ArrayList<ArtistModel>> arrayListCall = myApiArtist.callModel();
        arrayListCall.enqueue(new Callback<ArrayList<ArtistModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ArtistModel>> call, Response<ArrayList<ArtistModel>> response) {
                modelArrayList = response.body();
                for(int i = 0 ; i < modelArrayList.size() ; i++)
                {
                    CustomArtist custom = new CustomArtist(modelArrayList,Artist.this,R.layout.singleviewartist);
                    listView.setAdapter(custom);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArtistModel>> call, Throwable t) {

            }
        });

    }
}
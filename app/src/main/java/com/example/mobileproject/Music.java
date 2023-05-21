package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
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

public class Music extends AppCompatActivity {

    private ArrayList<MusicModel> modelArrayList;
    private MyApi myApi;
    private ListView listView;

    private String baseUrl = "https://my-json-server.typicode.com/p-andrea/test/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
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

        myApi = retrofit.create(MyApi.class);
        Call<ArrayList<MusicModel>> arrayListCall = myApi.callModel();
        arrayListCall.enqueue(new Callback<ArrayList<MusicModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MusicModel>> call, Response<ArrayList<MusicModel>> response) {



                modelArrayList = response.body();
                for(int i = 0 ; i < modelArrayList.size() ; i++)
                {
                    Custom custom = new Custom(modelArrayList,Music.this,R.layout.singleview);

                    listView.setAdapter(custom);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MusicModel>> call, Throwable t) {

            }

        });


    }

}
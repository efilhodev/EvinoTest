package com.example.eduardojulio.evinotestapp.data;

import com.example.eduardojulio.evinotestapp.data.response.TopGamesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe responsável pela instancia e gerenciamento do retrofit
 *
 * Created by Eduardo Julio on 3/21/2018.
 */

public class AppRestManager {
    private static final AppRestManager ourInstance = new AppRestManager();
    private Retrofit retrofit;

    public static AppRestManager getInstance() {
        return ourInstance;
    }

    private AppRestManager() {
        if(null == retrofit){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.twitch.tv/kraken/")
                    .addConverterFactory(GsonConverterFactory.create(getGsonConfig()))
                    .build();
        }
    }

    public AppRestEndpoint getAppRestEndPoint() {
        return retrofit.create(AppRestEndpoint.class);
    }

    private Gson getGsonConfig(){
        return  new GsonBuilder().registerTypeAdapter(TopGamesResponse.class, new TopGamesDeserializer()).create();
    }
}

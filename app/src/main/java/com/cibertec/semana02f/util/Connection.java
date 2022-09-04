package com.cibertec.semana02f.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {


    public static final String URL = "https://jsonplaceholder.typicode.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getConnecion(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}

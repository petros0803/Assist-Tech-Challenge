package com.example.myhotel.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BASE_URL="http://192.168.100.224:5000";
    private static Retrofit retrofit=null;


    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;

    }

    public static RestApiInterface getApiInterface()
    {
        return getApiClient().create(RestApiInterface.class);
    }
    }











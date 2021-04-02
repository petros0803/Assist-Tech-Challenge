package com.example.myhotel.api;

import com.example.myhotel.api.models.Login;
import com.example.myhotel.api.models.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiInterface {
    //for login
    @POST("/api/Users/authenticate")
    Call<Login> performLOGIN(@Body Login login);
    //for register
    @POST("/api/Users")
    Call<Register> performREGISTER(@Body Register register);

}

package com.example.myhotel.api;

import com.example.myhotel.api.models.Login;
import com.example.myhotel.api.models.Register;
import com.example.myhotel.api.models.Rooms;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApiInterface {
    //for login
    @POST("/api/Users/authenticate")
    Call<Login> performLOGIN(@Body Login login);
    //for register
    @POST("/api/Users")
    Call<Register> performREGISTER(@Body Register register);

    @GET("/api/Rooms")
    Call<List<Rooms>> getRooms();


}

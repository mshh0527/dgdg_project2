package com.example.dgdg.network;

import com.example.dgdg.data.JoinData;
import com.example.dgdg.data.JoinResponse;
import com.example.dgdg.data.LoginData;
import com.example.dgdg.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}
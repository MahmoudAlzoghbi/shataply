package com.example.mahmoudalzoghby.graduationproject.User;

import android.arch.persistence.room.RawQuery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {

    @POST("/api/signUp")
    @FormUrlEncoded
    Call<UserModel>saveUser(
                            //@Body User user
                            @Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("role") String role,
                            @Field("address") String address
                            );


    @POST("/api/signIn")
    @FormUrlEncoded
    Call<UserSignIn>LogIn(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/")
    Call<HelloWorld> Hello();


}

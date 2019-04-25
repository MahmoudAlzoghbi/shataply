package com.example.mahmoudalzoghby.graduationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;
import com.example.mahmoudalzoghby.graduationproject.User.Workers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkersActivity extends AppCompatActivity {


    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_workers);

        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        getWorkers("cairo" , 1);
        /*Call<Workers> call = apiServices.getAllWorkersRelatedToSpecificTask("cairo" , 1);

        call.enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(Call<Workers> call, Response<Workers> response) {
                if (response.isSuccessful()){
                        Log.i("Message" , response.body().getMessage());
                        Log.i("Last Name" , response.body().getWorkers().get(0).getLastName());
                }else
                    Log.i("Failed" , String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Workers> call, Throwable t) {
                Log.i("Failed" , t.getMessage());
            }
        });*/
    }

    public void getWorkers(String lName , int id) {
        apiServices.getAllWorkersRelatedToSpecificTask(lName , id).enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(Call<Workers> call, Response<Workers> response) {
                if (response.isSuccessful()){
                        // Log.i("Message" , response.body().getMessage());
                        Log.i("size", response.body().getWorkers().get(1).getLastName());
                }else
                    Log.i("Failed" , String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Workers> call, Throwable t) {
                Log.i("Failed" , t.getMessage());
            }
        });
    }
}

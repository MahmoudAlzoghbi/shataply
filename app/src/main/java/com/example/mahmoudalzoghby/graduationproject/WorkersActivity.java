package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;
import com.example.mahmoudalzoghby.graduationproject.User.Worker;
import com.example.mahmoudalzoghby.graduationproject.User.Workers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkersActivity extends AppCompatActivity {


    ApiServices apiServices;
    ListView listView , listView2 ;
    List<String> WorkerRelatedToSpecificDept = new ArrayList<>();
    List<Integer> WorkerRate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_workers);

        listView = (ListView)findViewById(R.id.show_workers_name);
        listView2 = (ListView)findViewById(R.id.show_workers_rate);

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        Bundle bundle = getIntent().getExtras();

        String  id = bundle.getString("DepartmentId");
        int Id = new Integer(id);
        String area = bundle.getString("Area");

        area = area.toLowerCase();

        Log.i("Area " , area);

        getWorkers(area , Id);


    }

    public void getWorkers(String lName , int id) {
        apiServices.getAllWorkersRelatedToSpecificTask(lName , id).enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(Call<Workers> call, Response<Workers> response) {
                if (response.isSuccessful()){
                    for (int i = 0 ; i < response.body().getWorkers().size() ; i++) {
                        //Log.i("Message" , response.body().getMessage());
                        //Log.i("size", response.body().getWorkers().get(1).getLastName());
                        WorkerRelatedToSpecificDept.add(response.body().getWorkers().get(i).getLastName());
                        WorkerRate.add(response.body().getWorkers().get(i).getTotalRate());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            WorkersActivity.this,
                            android.R.layout.simple_list_item_1,
                            WorkerRelatedToSpecificDept
                    );
                    listView.setAdapter(adapter);

                    ArrayAdapter<Integer> rate = new ArrayAdapter<>(
                            WorkersActivity.this,
                            android.R.layout.simple_list_item_1,
                            WorkerRate
                    );
                    listView2.setAdapter(rate);
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

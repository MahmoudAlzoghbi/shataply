package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetermineUserActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button client , worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.determine_user);

        client = (Button)findViewById(R.id.client_btn);
        worker = (Button)findViewById(R.id.worker_btn);

        sp = getSharedPreferences("login" , MODE_PRIVATE);

        if (sp.getBoolean("Client" , false))
            onClickButtonCustomer();
        else if (sp.getBoolean("Worker" , false))
            onClickButtonWorker();


        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButtonCustomer();
                sp.edit().putBoolean("Client" , true).apply();
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButtonWorker();
                sp.edit().putBoolean("Worker" , true).apply();
            }
        });
    }

    public void onClickButtonCustomer() {
        Intent intent = new Intent(this  , LoginActivity.class);
        startActivity(intent);
    }

    public void onClickButtonWorker() {
        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);
    }
}

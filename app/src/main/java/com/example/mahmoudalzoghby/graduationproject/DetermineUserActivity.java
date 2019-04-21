package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetermineUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.determine_user);
    }

    public void onClickButtonCustomer(View view) {
        Intent intent = new Intent(this  , ClientSignUpActivity.class);
        startActivity(intent);
    }

    public void onClickButtonWorker(View view) {
        Intent intent = new Intent(this , WorkerSignUpActivity.class);
        startActivity(intent);
    }
}

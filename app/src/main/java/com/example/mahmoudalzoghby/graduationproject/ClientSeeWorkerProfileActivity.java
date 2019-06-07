package com.example.mahmoudalzoghby.graduationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class ClientSeeWorkerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_see_worker_profile);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.rating_bar);

        ratingBar.setRating((float) 3.5);
    }
}

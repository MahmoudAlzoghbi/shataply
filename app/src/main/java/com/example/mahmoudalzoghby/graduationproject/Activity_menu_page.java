package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_menu_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
    }

    public void about_us(View view) {

        Intent about_intent = new Intent(this, AboutShattably.class);
        startActivity(about_intent);
    }

    public void contact_us(View view) {
        Intent contact_us_intent = new Intent(this,ContactUS.class );
        startActivity(contact_us_intent);
    }

    public void my_profile(View view) {

        Intent profile_intent = new Intent (this, ClientProfile.class);
        startActivity(profile_intent);

    }

    public void log_out(View view) {
        Intent logout_intent = new Intent(this ,LoginActivity.class);
        startActivity(logout_intent);
    }

    public void home_page(View view) {

        Intent home_page_intent = new Intent(this, HomePageActivity.class);
        startActivity(home_page_intent);
    }

    public void my_requests(View view) {

        Intent my_requests_intent = new Intent(this,UserSeeHisOrders.class);
        startActivity(my_requests_intent);
    }
}

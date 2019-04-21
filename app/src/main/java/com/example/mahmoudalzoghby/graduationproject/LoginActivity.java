package com.example.mahmoudalzoghby.graduationproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;
import com.example.mahmoudalzoghby.graduationproject.User.UserSignIn;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email , pass;

    ApiServices apiServices;

    String emailAddress;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Starting...");
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },3000);
    }



    // Moving to Determine the UserModel
    public void SignUpActivity(View view) {
        Intent intent = new Intent(this , DetermineUserActivity.class);
        startActivity(intent);
    }

    public void onClickButtonLogIn(View view) {

        /*SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("01026945658" , null , "123" , null , null);*/

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        email = (EditText) findViewById(R.id.log_in_email_address);
        pass = (EditText) findViewById(R.id.log_in_password);

        emailAddress = email.getText().toString();
        password = pass.getText().toString();

        if (!validate()){
            Toast.makeText(this , "Failed to Log in, try again." , Toast.LENGTH_LONG).show();
        }
        else{
            OnLogIn(emailAddress , password);
        }
    }

    public boolean validate(){
        boolean valid = true;
        emailAddress = email.getText().toString();
        password = pass.getText().toString();

        if (emailAddress.isEmpty()){
            email.setError("enter a valid email address or phone number");
            valid = false;
        }
        if (password.isEmpty()){
            pass.setError("enter a valid password");
            valid = false;
        }

        return valid;
    }

    public void checkUserFoundOrNot(String mesage){

        if (mesage.equals("user found ")){
            Intent intent = new Intent(this , ClientProfile.class);
            startActivity(intent);

        }
        else if (mesage.equals("Not found user please register ")){
            Toast.makeText(this , mesage , Toast.LENGTH_LONG).show();
        }
    }

    public void OnLogIn(String emailAddress , String password){
        apiServices.LogIn(emailAddress , password).enqueue(new Callback<UserSignIn>() {
            @Override
            public void onResponse(Call<UserSignIn> call, Response<UserSignIn> response) {
                if (response.isSuccessful()){
                    checkUserFoundOrNot(response.body().getMesage());
                    Log.i("Success" , response.body().getMesage());
                }
            }

            @Override
            public void onFailure(Call<UserSignIn> call, Throwable t) {
                Toast.makeText(LoginActivity.this , t.getMessage() ,Toast.LENGTH_LONG).show();
                Log.i("Failed" , t.getMessage());
            }
        });

    }
}

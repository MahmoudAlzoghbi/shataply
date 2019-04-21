package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;
import com.example.mahmoudalzoghby.graduationproject.User.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientSignUpActivity extends AppCompatActivity {

    ApiServices apiServices;

    EditText name , email , address , pass , con_pass;

    String Name , Email , Address ,Pass , Con_Pass;
    String role = "Client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);
    }

    public void onClickButtonCustomerSignUp(View view) {
        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        Name = getName();
        Email = getEmail();
        Pass = getPass();
        Address = getAddress();

        if (!validation()){
            Toast.makeText(ClientSignUpActivity.this ,  "Please Complete your Information" , Toast.LENGTH_LONG).show();
        }
        else {
            sendUser(Name ,Email , Pass , role , Address );
        }


    }

    public boolean validation(){
        boolean valid = true;
        Name = getName();
        Email = getEmail();
        Address = getAddress();
        Pass = getPass();
        Con_Pass = getCon_Pass();

        if (Name.isEmpty() || Name.length() < 3){
            name.setError("enter your name");
            valid = false;
        }
        if (Email.isEmpty() ){
            email.setError("enter your email address or username");
            valid = false;
        }
        if (Address.isEmpty() || Address.length() < 3){
            address.setError("Enter a valid Address");
            valid = false;
        }
        if (Pass.isEmpty() || Pass.length() < 6){
            pass.setError("Password should greater than 6 characters");
            valid = false;
        }
        if (Con_Pass.isEmpty() || Con_Pass.length() < 6 || !Con_Pass.equals(Pass)){
            con_pass.setError("Confirm the Password Please!");
            valid = false;
        }
        return valid;
    }

    public void sendUser(String name , String email , String pass , String role ,String address){
        apiServices.saveUser(name , email , pass , role , address).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!(response.body().getUser() == null)){
                    checkUserFoundOrNot(response.body().toString());
                    Log.i("Success" , "User Saved Successfully");
                }
                else {
                    Toast.makeText(ClientSignUpActivity.this , "هذا المستخدم موجود بالفعل" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.i("Failed" , t.getMessage());

            }
        });

    }

    public void checkUserFoundOrNot(String mesage){
        if (!mesage.isEmpty()){
            Intent intent = new Intent(ClientSignUpActivity.this , ClientProfile.class);
            Bundle bu = new Bundle();
            bu.putString("name" , getName());
            bu.putString("phoneNumber" , getEmail());
            intent.putExtras(bu);
            startActivity(intent);
        }
    }

    public String getName(){
        name = (EditText) findViewById(R.id.c_name);
        Name = name.getText().toString().trim();
        return Name;
    }

    public String getEmail(){
        email = (EditText) findViewById(R.id.c_email);
        Email= email.getText().toString().trim();
        return Email;
    }

    public String getAddress(){
        address = (EditText) findViewById(R.id.c_address);
        Address = address.getText().toString().trim();
        return Address;
    }

    public String getPass(){
        pass = (EditText) findViewById(R.id.c_password);
        Pass = pass.getText().toString();
        return Pass;
    }

    public String getCon_Pass(){
        con_pass = (EditText) findViewById(R.id.c_confirm_password);
        Con_Pass = con_pass.getText().toString();
        return Con_Pass;
    }

}

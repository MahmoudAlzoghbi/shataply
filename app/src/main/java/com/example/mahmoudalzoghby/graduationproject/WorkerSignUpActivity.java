package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;
import com.example.mahmoudalzoghby.graduationproject.User.User;
import com.example.mahmoudalzoghby.graduationproject.User.UserModel;
import com.example.mahmoudalzoghby.graduationproject.User.UserSignIn;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerSignUpActivity extends AppCompatActivity {

    private ApiServices apiServices;
    //private User user;

    EditText name ,phNum ,address ,job ,password ,con_pass;

    String role = "Worker";
    String Name , email , addres , jobs , pass ,confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_sign_up);

        /*SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage("01026945658" , null , "123" , null , null);*/

        name = (EditText)findViewById(R.id.w_f_name);
        phNum = (EditText)findViewById(R.id.w_phone_number);
        address = (EditText)findViewById(R.id.w_address);
        job = (EditText)findViewById(R.id.w_type_of_job);
        password = (EditText)findViewById(R.id.w_password);
        con_pass = (EditText)findViewById(R.id.w_confirm_password);
        Button btn_sign_up = (Button)findViewById(R.id.w_btn_sign_up);

        Name = name.getText().toString().trim();
        email = phNum.getText().toString().trim();
        addres = address.getText().toString().trim();
        jobs = job.getText().toString().trim();
        pass = password.getText().toString();
        confirm_pass = con_pass.getText().toString();

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validation()){
                    Toast.makeText(WorkerSignUpActivity.this ,  "من فضلك تاكد من اكتمال البيانات" , Toast.LENGTH_LONG).show();
                }
                else {
                    sendUser(Name ,email , pass ,role , addres );
                }

                //user = new User(f_name , email , pass , role , addres);
            }
        });
    }

    public boolean validation(){
        boolean valid = true;
        Name = name.getText().toString().trim();
        email = phNum.getText().toString().trim();
        addres = address.getText().toString().trim();
        jobs = job.getText().toString().trim();
        pass = password.getText().toString();
        confirm_pass = con_pass.getText().toString();

        if (Name.isEmpty() || Name.length() < 3){
            name.setError("على الاقل 3 حروف ");
            valid = false;
        }
        if (email.isEmpty() || email.length() != 11){
            phNum.setError("ادخل رقم الموبايل  ");
            valid = false;
        }
        if (addres.isEmpty() || address.length() < 3){
            address.setError("ادخل العنوان ");
            valid = false;
        }
        if (jobs.isEmpty() || jobs.length() < 3){
            job.setError("المهنه التابع لها ");
            valid = false;
        }
        if (pass.isEmpty() || pass.length() < 6){
            password.setError("على الاقل 6 ارقام او حرزف ");
            valid = false;
        }
        if (confirm_pass.isEmpty() || confirm_pass.length() < 6){
            con_pass.setError("تاكيد كلمه السر ");
            valid = false;
        }

        if (!pass.equals(confirm_pass)){
            con_pass.setError("تأكد من ان كلمه السر متشابهه ");
            valid = false;
        }
        return valid;
    }

    public void checkUserFoundOrNot(String mesage){
        if (!mesage.isEmpty()){
            Intent intent = new Intent(WorkerSignUpActivity.this , ConfirmPhNumberActivity.class);
            startActivity(intent);
        }
    }



    public void sendUser( String name , String email , String pass , String role , String address ) {
        apiServices.saveUser(name , email , pass , role ,address  ).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
                    if (!(response.body().getUser() == null)){
                        checkUserFoundOrNot(response.body().toString());
                        Log.i("Success" , "User Saved Successfully");
                    }
                    else {
                        Toast.makeText(WorkerSignUpActivity.this , "هذا المستخدم موجود بالفعل" , Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                //Log.i("Failed" , t.getMessage());
                Log.e("fail" , "Failed to save User");
            }
        });

    }
}

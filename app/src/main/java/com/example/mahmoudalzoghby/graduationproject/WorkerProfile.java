package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.HelloWorld;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerProfile extends AppCompatActivity {

    ApiServices apiServices;

    private ImageView image1 , image2 , image3 , image4 , image5 , image6 , image7 ,image8 ,image9 ,image10;
    public Boolean[] imagechicked = {false , false , false , false , false , false , false , false , false , false};
    public Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        Call<HelloWorld> call = apiServices.Hello();

        call.enqueue(new Callback<HelloWorld>() {
            @Override
            public void onResponse(Call<HelloWorld> call, Response<HelloWorld> response) {
                Toast.makeText(WorkerProfile.this , "Alzoghbi"+response.body().toString() , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<HelloWorld> call, Throwable t) {
                Toast.makeText(WorkerProfile.this , t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });



        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        image3 = (ImageView)findViewById(R.id.image3);
        image4 = (ImageView)findViewById(R.id.image4);
        image5 = (ImageView)findViewById(R.id.image5);
    }

    public void addPicture1(View view) {
        imagechicked[0] = true;
        UploadPhoto(view);
    }


    public void addPicture2(View view) {
        imagechicked[1] = true;
        UploadPhoto(view);
    }

    public void addPicture3(View view) {
        imagechicked[2] = true;
        UploadPhoto(view);
    }

    public void addPicture4(View view) {
        imagechicked[3] = true;
        UploadPhoto(view);
    }

    public void addPicture5(View view) {
        imagechicked[4] = true;
        UploadPhoto(view);
    }

    public void UploadPhoto(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        File pictureFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String pictureFilePath = pictureFile.getPath();

        Uri data = Uri.parse(pictureFilePath);

        intent.setDataAndType(data , "image/*");

        startActivityForResult(intent ,  1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            Toast.makeText(WorkerProfile.this , selectedImage.toString() , Toast.LENGTH_LONG).show();
            InputStream inputStream;

            try {

                inputStream = getContentResolver().openInputStream(selectedImage);

                bitmap = BitmapFactory.decodeStream(inputStream);
                if (imagechicked[0] == true){
                    image1.setImageBitmap(bitmap);
                    imagechicked[0] = false;
                } else if (imagechicked[1] == true){
                    image2.setImageBitmap(bitmap);
                    imagechicked[1] = false;
                } else if (imagechicked[2] == true){
                    image3.setImageBitmap(bitmap);
                    imagechicked[2] = false;
                } else if (imagechicked[3] == true){
                    image4.setImageBitmap(bitmap);
                    imagechicked[3] = false;
                } else if (imagechicked[4] == true){
                    image5.setImageBitmap(bitmap);
                    imagechicked[4] = false;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this , e.getMessage() , Toast.LENGTH_LONG).show();
            }
            //clintPhoto.setImageURI(selectedImage);
        }
    }

}

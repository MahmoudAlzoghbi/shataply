package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClientProfile extends AppCompatActivity {

    ImageView clintPhoto ;
    TextView name ;
    TextView phoneNumber;
    String phNumber ,Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        clintPhoto = (ImageView)findViewById(R.id.client_photo);

        /*Bundle bundle = getIntent().getExtras();
        Name = bundle.getString("name");
        phNumber = bundle.getString("phoneNumber");
*/
        name = (TextView)findViewById(R.id.client_name);
        phoneNumber = (TextView) findViewById(R.id.client_phone_number);

        /*name.setText(Name);
        phoneNumber.setText(phNumber);*/
    }

    public void UploadPhoto(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        File pictureFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String pictureFilePath = pictureFile.getPath();

        Uri data = Uri.parse(pictureFilePath);

        intent.setDataAndType(data , "image/*");

        startActivityForResult(intent ,  1);

        //Toast.makeText(ClientProfile.this , data.toString() , Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            Toast.makeText(ClientProfile.this , selectedImage.toString() , Toast.LENGTH_LONG).show();
            InputStream inputStream;

            try {

                inputStream = getContentResolver().openInputStream(selectedImage);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                clintPhoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this , e.getMessage() , Toast.LENGTH_LONG).show();
            }
            //clintPhoto.setImageURI(selectedImage);
        }
    }
}

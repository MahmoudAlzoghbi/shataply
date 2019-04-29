package com.example.mahmoudalzoghby.graduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoudalzoghby.graduationproject.User.ApiServices;
import com.example.mahmoudalzoghby.graduationproject.User.Datum;
import com.example.mahmoudalzoghby.graduationproject.User.Department;
import com.example.mahmoudalzoghby.graduationproject.User.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClintSelection2Activity extends AppCompatActivity {

    Button cairo , alex , giza;

    TextView t1 , t2;

    Spinner ListOfArea , ListOfCategory ;

    String gove , cat , area;

    Button search;

    public boolean isClicked = false;

    ApiServices apiServices;

    List<Datum> datumList = new ArrayList<>();
    List<String> depertment = new ArrayList<>();
    List<Integer> deptId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clint_selection_2);

        apiServices = RetrofitClient.getClient().create(ApiServices.class);

        Call<Department> departmentCall = apiServices.getDepartment();

        departmentCall.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(retrofit2.Call<Department> call, Response<Department> response) {

                datumList = response.body().getData();
                for(int i = 0 ; i < datumList.size() ; i++){
                    depertment.add(datumList.get(i).getName());
                    deptId.add(datumList.get(i).getId());

                }
                //Log.i("dept" , depertment.get(0));

                //Log.i("Success" , response.body().getMessage());
            }

            @Override
            public void onFailure(retrofit2.Call<Department> call, Throwable t) {
                Log.i("Failed" , t.getMessage());
            }
        });
    }

    public void onClickBtnMansoura(View view) {
        initializeAllAttribute();

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);


        ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this,
                R.array.el_mansoura_area,
                android.R.layout.simple_list_item_1
        );
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfArea.setAdapter(ar);

        ArrayAdapter<String> cat = new ArrayAdapter<String>(
                ClintSelection2Activity.this ,
                android.R.layout.simple_list_item_1,
                depertment
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);

        gove = "El-Mansoura";
        isClicked = true;
    }

    public void onClickBtnAlex(View view) {
        initializeAllAttribute();

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);


        ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this,
                R.array.alex_area,
                android.R.layout.simple_list_item_1
        );
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfArea.setAdapter(ar);

        ArrayAdapter<String> cat = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_list_item_1,
                depertment
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);

        gove = "alexandria";
        isClicked = true;
    }

    public void onClickBtnCairo(View view) {
        initializeAllAttribute();

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);


        ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this,
                R.array.cairo_area,
                android.R.layout.simple_list_item_1
        );
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfArea.setAdapter(ar);

        ArrayAdapter<String> cat = new ArrayAdapter<>(
                ClintSelection2Activity.this ,
                android.R.layout.simple_list_item_1,
                depertment
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);
        gove = "cairo";
        isClicked = true;
    }

    public void initializeAllAttribute(){
        t1 = (TextView)findViewById(R.id.textview1);
        t2 = (TextView)findViewById(R.id.textview2);
        ListOfArea = (Spinner)findViewById(R.id.ListOfArea);
        ListOfCategory = (Spinner)findViewById(R.id.ListOfCategory);
    }

    public void onClickBtnSearch(View view) {
        ListOfArea = (Spinner)findViewById(R.id.ListOfArea);
        ListOfCategory = (Spinner)findViewById(R.id.ListOfCategory);
        Log.i("isCklicked" , String.valueOf(isClicked));
        if (isClicked == false){
            Toast.makeText(this , "Please Click in Button Government" , Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this , WorkersActivity.class);

            Bundle bundle = new Bundle();

            String ar = ListOfArea.getSelectedItem().toString();
            String ca = ListOfCategory.getSelectedItem().toString();

            int index = depertment.indexOf(ca);
            int depID = deptId.get(index);

            bundle.putString("DepartmentId" , String.valueOf(depID));
            bundle.putString("Area" , gove);

            intent.putExtras(bundle);

            startActivity(intent);

            //Log.i("id" , String.valueOf(depID));
            //Log.i("Category" , ca);
            //Log.i("Area" , ar);
        }

    }
}

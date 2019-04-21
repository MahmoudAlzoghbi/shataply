package com.example.mahmoudalzoghby.graduationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ClintSelection2Activity extends AppCompatActivity {

    Button cairo , alex , giza;

    TextView t1 , t2;

    Spinner ListOfArea , ListOfCategory ;

    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clint_selection_2);

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

        ArrayAdapter<CharSequence> cat = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this ,
                R.array.category,
                android.R.layout.simple_list_item_1
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);

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

        ArrayAdapter<CharSequence> cat = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this ,
                R.array.category,
                android.R.layout.simple_list_item_1
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);
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

        ArrayAdapter<CharSequence> cat = ArrayAdapter.createFromResource(
                ClintSelection2Activity.this ,
                R.array.category,
                android.R.layout.simple_list_item_1
        );
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ListOfCategory.setAdapter(cat);

        ListOfArea.setVisibility(View.VISIBLE);
        ListOfCategory.setVisibility(View.VISIBLE);
    }

    public void initializeAllAttribute(){
        t1 = (TextView)findViewById(R.id.textview1);
        t2 = (TextView)findViewById(R.id.textview2);
        ListOfArea = (Spinner)findViewById(R.id.ListOfArea);
        ListOfCategory = (Spinner)findViewById(R.id.ListOfCategory);
    }
}

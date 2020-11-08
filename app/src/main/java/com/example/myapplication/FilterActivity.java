package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private Chip btn_a1, btn_a2, btn_a3, btn_a4, btn_a5, btn_a6, btn_a7, btn_a8,
            btn_a9,btn_a10,btn_a11,btn_a12,btn_a13,btn_a14,btn_a15,btn_a16,btn_a17,btn_a18,btn_a19;



    private Button btnApply;
    private ArrayList<String> selectedChipsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btn_a1 = findViewById(R.id.btn_a1);        btn_a2 = findViewById(R.id.btn_a2);        btn_a3 = findViewById(R.id.btn_a3);
        btn_a4 = findViewById(R.id.btn_a4);        btn_a5 = findViewById(R.id.btn_a5);        btn_a6 = findViewById(R.id.btn_a6);
        btn_a7 = findViewById(R.id.btn_a7);        btn_a8 = findViewById(R.id.btn_a8);        btn_a9 = findViewById(R.id.btn_a9);
        btn_a10 = findViewById(R.id.btn_a10); btn_a11 = findViewById(R.id.btn_a11); btn_a12 = findViewById(R.id.btn_a12);
        btn_a13 = findViewById(R.id.btn_a13); btn_a14 = findViewById(R.id.btn_a14); btn_a15 = findViewById(R.id.btn_a15); btn_a16 = findViewById(R.id.btn_a6);
        btn_a17 = findViewById(R.id.btn_a17); btn_a18 = findViewById(R.id.btn_a18); btn_a19 = findViewById(R.id.btn_a19);

        selectedChipsData = new ArrayList<>();

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedChipsData.add(buttonView.getText().toString());
                }
                else{
                    selectedChipsData.remove(buttonView.getText().toString());
                }
            }

        };
        btn_a1.setOnCheckedChangeListener(checkedChangeListener);
        btn_a2.setOnCheckedChangeListener(checkedChangeListener);
        btn_a3.setOnCheckedChangeListener(checkedChangeListener);
        btn_a4.setOnCheckedChangeListener(checkedChangeListener);
        btn_a5.setOnCheckedChangeListener(checkedChangeListener);
        btn_a6.setOnCheckedChangeListener(checkedChangeListener);
        btn_a7.setOnCheckedChangeListener(checkedChangeListener);
        btn_a8.setOnCheckedChangeListener(checkedChangeListener);
        btn_a9.setOnCheckedChangeListener(checkedChangeListener);
        btn_a10.setOnCheckedChangeListener(checkedChangeListener);
        btn_a11.setOnCheckedChangeListener(checkedChangeListener);
        btn_a12.setOnCheckedChangeListener(checkedChangeListener);
        btn_a13.setOnCheckedChangeListener(checkedChangeListener);
        btn_a14.setOnCheckedChangeListener(checkedChangeListener);
        btn_a15.setOnCheckedChangeListener(checkedChangeListener);
        btn_a16.setOnCheckedChangeListener(checkedChangeListener);
        btn_a17.setOnCheckedChangeListener(checkedChangeListener);
        btn_a18.setOnCheckedChangeListener(checkedChangeListener);
        btn_a19.setOnCheckedChangeListener(checkedChangeListener);

        btnApply = findViewById(R.id.bntApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", selectedChipsData.toString());
                setResult(101,resultIntent);
                finish();
            }
        });




    }
}
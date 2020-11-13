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

    private Chip btn_allergy_nanlyu, btn_allergy_uyu, btn_allergy_memil, btn_allergy_ttangkong,
            btn_allergy_daedu, btn_allergy_mil, btn_allergy_godeungeo, btn_allergy_ge,
            btn_allergy_saeu,btn_allergy_dqaejigogi,btn_allergy_bogsunga,btn_allergy_tomato,
            btn_allergy_ahwangsanlyu,btn_allergy_hodu,btn_allergy_dalggogi,btn_allergy_jas,
            btn_allergy_ojingeo,btn_allergy_sogogi,btn_allergy_jogaelyu;

    private Chip btn_illness_1;

    private Button btnApply;

    private ArrayList<String> selectedChipsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btn_allergy_nanlyu = findViewById(R.id.btn_allergy_nanlyu);
        btn_allergy_uyu = findViewById(R.id.btn_allergy_uyu);
        btn_allergy_memil = findViewById(R.id.btn_allergy_memil);
        btn_allergy_ttangkong = findViewById(R.id.btn_allergy_ttangkong);
        btn_allergy_daedu = findViewById(R.id.btn_allergy_daedu);
        btn_allergy_mil = findViewById(R.id.btn_allergy_mil);
        btn_allergy_godeungeo = findViewById(R.id.btn_allergy_godeungeo);
        btn_allergy_ge = findViewById(R.id.btn_allergy_ge);
        btn_allergy_saeu = findViewById(R.id.btn_allergy_saeu);
        btn_allergy_dqaejigogi = findViewById(R.id.btn_allergy_dqaejigogi);
        btn_allergy_bogsunga = findViewById(R.id.btn_allergy_bogsunga);
        btn_allergy_tomato = findViewById(R.id.btn_allergy_tomato);
        btn_allergy_ahwangsanlyu = findViewById(R.id.btn_allergy_ahwangsanlyu);
        btn_allergy_hodu = findViewById(R.id.btn_allergy_hodu);
        btn_allergy_dalggogi = findViewById(R.id.btn_allergy_dalggogi);
        btn_allergy_jas = findViewById(R.id.btn_allergy_jas);
        btn_allergy_ojingeo = findViewById(R.id.btn_allergy_ojingeo);
        btn_allergy_sogogi = findViewById(R.id.btn_allergy_sogogi);
        btn_allergy_jogaelyu = findViewById(R.id.btn_allergy_jogaelyu);

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

        btn_allergy_nanlyu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_uyu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_memil.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_ttangkong.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_daedu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_mil.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_godeungeo.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_ge.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_saeu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_dqaejigogi.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_bogsunga.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_tomato.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_ahwangsanlyu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_hodu.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_dalggogi.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_jas.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_ojingeo.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_sogogi.setOnCheckedChangeListener(checkedChangeListener);
        btn_allergy_jogaelyu.setOnCheckedChangeListener(checkedChangeListener);

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
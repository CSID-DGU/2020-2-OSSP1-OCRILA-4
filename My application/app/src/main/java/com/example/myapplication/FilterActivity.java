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

    private Chip btn_disease_COPD, btn_disease_tuberculosis, btn_disease_enteritis, btn_disease_fattyliver, btn_disease_crohn, btn_disease_hepatitis, btn_disease_highbloodpressure, btn_disease_angina,
            btn_disease_CABG,btn_disease_cardiac_infarction,btn_disease_sugar_diabetes,btn_disease_obesity,btn_disease_leukemia,btn_disease_epilepsy,btn_disease_cerebral,
            btn_disease_cerebromeningitis,btn_disease_osteoporosis,btn_disease_gout,btn_disease_cystitis, btn_disease_incontinence, btn_disease_allergic_coryza,
            btn_disease_inflammation_middle_ear, btn_disease_depression, btn_disease_insomnia, btn_disease_dementia, btn_disease_panic, btn_disease_schizophrenia,
            btn_disease_bipolar, btn_disease_ADHD,btn_disease_posttraumatic_stress, btn_disease_bladder_cancer, btn_disease_bloodpoisoning;


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


        btn_disease_COPD = findViewById(R.id.btn_disease_COPD);        btn_disease_tuberculosis = findViewById(R.id.btn_disease_tuberculosis);
        btn_disease_enteritis = findViewById(R.id.btn_disease_enteritis);        btn_disease_fattyliver = findViewById(R.id.btn_disease_fattyliver);
        btn_disease_crohn = findViewById(R.id.btn_disease_crohn);                btn_disease_hepatitis = findViewById(R.id.btn_disease_hepatitis);
        btn_disease_highbloodpressure = findViewById(R.id.btn_disease_highbloodpressure);        btn_disease_angina = findViewById(R.id.btn_disease_angina);
        btn_disease_CABG = findViewById(R.id.btn_disease_CABG);        btn_disease_cardiac_infarction = findViewById(R.id.btn_disease_cardiac_infarction);
        btn_disease_sugar_diabetes = findViewById(R.id.btn_disease_sugar_diabetes);      btn_disease_obesity = findViewById(R.id.btn_disease_obesity);
        btn_disease_leukemia = findViewById(R.id.btn_disease_leukemia);      btn_disease_epilepsy = findViewById(R.id.btn_disease_epilepsy);
        btn_disease_cerebral = findViewById(R.id.btn_disease_cerebral); btn_disease_cerebromeningitis = findViewById(R.id.btn_disease_cerebromeningitis);
        btn_disease_osteoporosis = findViewById(R.id.btn_disease_osteoporosis);      btn_disease_gout = findViewById(R.id.btn_disease_gout);
        btn_disease_cystitis = findViewById(R.id.btn_disease_cystitis);        btn_disease_incontinence = findViewById(R.id.btn_disease_incontinence);
        btn_disease_allergic_coryza = findViewById(R.id.btn_disease_allergic_coryza);              btn_disease_inflammation_middle_ear = findViewById(R.id.btn_disease_inflammation_middle_ear);
        btn_disease_depression = findViewById(R.id.btn_disease_depression);        btn_disease_insomnia = findViewById(R.id.btn_disease_insomnia);
        btn_disease_dementia = findViewById(R.id.btn_disease_dementia);        btn_disease_panic = findViewById(R.id.btn_disease_panic);
        btn_disease_schizophrenia = findViewById(R.id.btn_disease_schizophrenia);                btn_disease_bipolar = findViewById(R.id.btn_disease_bipolar);
        btn_disease_ADHD = findViewById(R.id.btn_disease_ADHD);              btn_disease_posttraumatic_stress = findViewById(R.id.btn_disease_posttraumatic_stress);
        btn_disease_bladder_cancer = findViewById(R.id.btn_disease_bladder_cancer);        btn_disease_bloodpoisoning = findViewById(R.id.btn_disease_bloodpoisoning);

        Button btn_no_caution_disease_list = findViewById(R.id.btn_no_caution_disease_list);


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
        btn_disease_COPD.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_tuberculosis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_enteritis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_fattyliver.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_crohn.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_hepatitis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_highbloodpressure.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_angina.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_CABG.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_cardiac_infarction.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_sugar_diabetes.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_obesity.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_leukemia.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_epilepsy.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_cerebral.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_cerebromeningitis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_osteoporosis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_gout.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_cystitis.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_incontinence.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_allergic_coryza.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_inflammation_middle_ear.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_depression.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_insomnia.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_dementia.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_panic.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_schizophrenia.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_bipolar.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_ADHD.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_posttraumatic_stress.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_bladder_cancer.setOnCheckedChangeListener(checkedChangeListener);
        btn_disease_bloodpoisoning.setOnCheckedChangeListener(checkedChangeListener);

        btn_no_caution_disease_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),NoCautionDiseaseListActivity.class);
                startActivity(intent);
            }
        });

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
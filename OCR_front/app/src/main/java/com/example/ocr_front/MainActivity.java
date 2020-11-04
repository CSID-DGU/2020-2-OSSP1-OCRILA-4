package com.example.ocr_front;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Fragment1 fragment_1;
    Fragment2 fragment_2;
    Fragment3 fragment_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ActionBar actionbar =getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);

        fragment_1 = new Fragment1();
        fragment_2 = new Fragment2();
        fragment_3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("사진"));
        tabs.addTab(tabs.newTab().setText("최근 본 상품"));
        tabs.addTab(tabs.newTab().setText("내 정보"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if(position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_1).commit();
                }else if(position == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_2).commit();
                }else if(position == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_3).commit();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
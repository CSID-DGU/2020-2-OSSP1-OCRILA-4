package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CameraActivity extends AppCompatActivity {
    private  Button btn_camera_result;
    Button btn_camera_camera, btn_camera_reset;
    ImageView camera_image_view;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btn_camera_result =(Button)findViewById(R.id.btn_camera_result);
        btn_camera_camera = findViewById(R.id.btn_camera_camera);
        btn_camera_reset = findViewById(R.id.btn_camera_reset);
        camera_image_view = findViewById(R.id.camera_image_view);

        btn_camera_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(CameraActivity.this);
            }
        });
        btn_camera_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera_image_view.setImageBitmap(null);
            }
        });
        btn_camera_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),CameraResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                uri=imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);
            }else{
                startCrop(imageuri);
            }
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                camera_image_view.setImageURI(result.getUri());
                Toast.makeText(this, "Image Update successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCrop(Uri imageuri) {
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
}



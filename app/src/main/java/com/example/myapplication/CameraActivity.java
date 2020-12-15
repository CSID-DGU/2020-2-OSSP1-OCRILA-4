package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends AppCompatActivity {
    private static final String CLOUD_VISION_API_KEY = BuildConfig.API_KEY;
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_TEXT_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;
    private static final String TAG = CameraActivity.class.getSimpleName();

    private  Button btn_camera_result;
    Button btn_camera_camera, btn_camera_reset;
    ImageView camera_image_view;
    Uri uri;
    Uri cropResult;
    private TextView tv_ocrResult; //camera_image_details
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
                if(cropResult!=null) {
                    Intent intent = new Intent (getApplicationContext(),ResultActivity.class);
                    intent.putExtra("cropResult",cropResult);
                    startActivity(intent);

                }else{
                    Toast.makeText(CameraActivity.this, "사진을 먼저 골라주세요.", Toast.LENGTH_LONG).show();
                }
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
                cropResult = result.getUri();
                camera_image_view.setImageURI(cropResult);
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



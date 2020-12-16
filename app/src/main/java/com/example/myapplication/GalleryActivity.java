package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends AppCompatActivity {

    private static final String CLOUD_VISION_API_KEY = BuildConfig.API_KEY;
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_TEXT_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;
    private static final String TAG = GalleryActivity.class.getSimpleName();
    private static final int GALLERY_IMAGE_REQUEST = 1;

    private ImageView Gallery_image_view;
    private Button btn_gallery_gallery;
    private  Button btn_gallery_result;
    private  Button btn_gallery_reset;
    private final String SAMPLE_CROPPED_IMG_NAME="SampleCrop";

    private Uri imageUriResultCrop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Gallery_image_view=(ImageView)findViewById(R.id.Gallery_image_view);
        btn_gallery_gallery =(Button)findViewById(R.id.btn_gallery_gallery);
        btn_gallery_result =(Button)findViewById(R.id.btn_gallery_result);
        btn_gallery_reset = findViewById(R.id.btn_gallery_reset);
        btn_gallery_gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                            GALLERY_IMAGE_REQUEST);
                    Toast.makeText(GalleryActivity.this, "원재료 분석표에 맞춰서 사진을 잘라주세요.", Toast.LENGTH_LONG).show();
                }
        });

        btn_gallery_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery_image_view.setImageBitmap(null);
            }
        });

        btn_gallery_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent = new Intent (getApplicationContext(),CameraResultActivity.class);
               // startActivity(intent);
                if(imageUriResultCrop!=null) {
                    Intent intent = new Intent (getApplicationContext(),ResultActivity.class);
                    intent.putExtra("cropResult",imageUriResultCrop);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(GalleryActivity.this, "사진을 먼저 골라주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().
                        setAction(Intent.ACTION_GET_CONTENT)
                        .setType("image/*"),CODE_IMG_GALLERY);
            }
        });*/

    }

    private void init(){
        this.Gallery_image_view=findViewById(R.id.Gallery_iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){

            //DESDE GALLERY
            Uri imageUri=data.getData();
            if(imageUri!=null){
                startCrop(imageUri);
            }

        }else if(requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK){
            imageUriResultCrop = UCrop.getOutput(data);

            if(imageUriResultCrop!=null){
                Gallery_image_view.setImageURI(null);
                Gallery_image_view.setImageURI(imageUriResultCrop);
                Gallery_image_view.invalidate();
            }

        }

    }

    private void startCrop(@NonNull Uri uri){
        String destinationFileName = SAMPLE_CROPPED_IMG_NAME;
        destinationFileName +=".jpg";

        UCrop uCrop=UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),destinationFileName)));

        uCrop.withAspectRatio(1,1);
        //uCrop.withAspectRatio(3,4);
        //uCrop.useSourceImageAspectRatio()
        //uCrop.withAspectRatio(2,3);
        //uCrop.withAspectRatio(16,5);

        uCrop.withMaxResultSize(450,450);

        uCrop.withOptions(getCropOption());

        uCrop.start(GalleryActivity.this);
    }

    private UCrop.Options getCropOption(){
        UCrop.Options options=new UCrop.Options();

        options.setCompressionQuality(70);

        //CompressType
        //options.setCompressionFormat(Bitmap.CompressFormat.PNG);
        //options.setCompressionFormat(Bitmap.CompressFormat.JPEG);

        //UI
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);

        //Colors
        options.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        options.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        options.setToolbarTitle("Cropper");

        return options;
    }

}
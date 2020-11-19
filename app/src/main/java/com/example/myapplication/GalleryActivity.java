package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yalantis.ucrop.UCrop;

import java.io.File;


public class GalleryActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn_gallery_gallery;
    private  Button btn_gallery_result;
    private final int CODE_IMG_GALLERY=1;
    private final String SAMPLE_CROPPED_IMG_NAME="SampleCrop";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        img=(ImageView)findViewById(R.id.Gallery_iv);
        btn_gallery_gallery =(Button)findViewById(R.id.btn_gallery_gallery);
        btn_gallery_result =(Button)findViewById(R.id.bnt_gallery_result);
        //init();

        btn_gallery_gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().
                        setAction(Intent.ACTION_GET_CONTENT)
                        .setType("image/*"),CODE_IMG_GALLERY);
                Toast.makeText(GalleryActivity.this, "원재료 분석표에 맞춰서 사진을 잘라주세요.", Toast.LENGTH_LONG).show();
            }

        });

        btn_gallery_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),GalleryresultActivity.class);
                startActivity(intent);
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
        this.img=findViewById(R.id.Gallery_iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_IMG_GALLERY && resultCode==RESULT_OK){

            //DESDE GALLERY
            Uri imageUri=data.getData();
            if(imageUri!=null){
                startCrop(imageUri);

            }

        }else if(requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK){
            Uri imageUriResultCrop = UCrop.getOutput(data);

            if(imageUriResultCrop!=null){
                img.setImageURI(null);
                img.setImageURI(imageUriResultCrop);
                img.invalidate();
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
        options.setStatusBarColor(getResources().getColor(R.color.design_default_color_primary_dark));
        options.setToolbarColor(getResources().getColor(R.color.design_default_color_primary));

        options.setToolbarTitle("Cropper");

        return options;
    }



}
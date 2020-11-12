package com.theartofdev.edmodo.cropper.test;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

  static final int REQUEST_TAKE_PHOTO = 308;
  static final int REQUEST_SAVE_IMAGE = 213;
  String mCurrentPhotoPath;
  boolean album = false;
  Uri albumURI = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /** Start pick image activity with chooser. */
  public void onSelectImageClick_camera(View view) {
    CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);
  }

  public void onSelectImageClick_gallery(View view) {
    pickFromGallery();
  }

  private void pickFromGallery() {
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
            .setType("image/*")
            .addCategory(Intent.CATEGORY_OPENABLE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      String[] mimeTypes = {"image/jpeg", "image/png"};
      intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
    }
    startActivityForResult(Intent.createChooser(intent,"Select Picture"), REQUEST_TAKE_PHOTO);
  }

  private File createImageFile() throws IOException{
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_"+timeStamp+"_";
    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(imageFileName,".jpg",storageDir);
    mCurrentPhotoPath = image.getAbsolutePath();
    return image;
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        ((ImageView) findViewById(R.id.quick_start_cropped_image)).setImageURI(result.getUri());
        Toast.makeText(
                this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
            .show();

      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
      }
    }
    if (requestCode == REQUEST_TAKE_PHOTO) {
      final Uri selectedUri = data.getData();
      if(selectedUri!=null){
        CropImage.activity(selectedUri).setGuidelines(CropImageView.Guidelines.ON).start(this);
      }
    }
  }
}

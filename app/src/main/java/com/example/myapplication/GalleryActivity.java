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

import info.debatty.java.stringsimilarity.*;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Allergy;
import com.example.myapplication.model.Disease;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    private TextView tv_ocrResult;


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
                    uploadImage(imageUriResultCrop);
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
    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature TextDetection = new Feature();
                TextDetection.setType("TEXT_DETECTION");
                TextDetection.setMaxResults(MAX_TEXT_RESULTS);
                add(TextDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    protected  class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<GalleryActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(GalleryActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();
                return Correction(convertResponseToString(response));
            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
            GalleryActivity activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                TextView tv_ocrResult = activity.findViewById(R.id.gallery_image_details);
                tv_ocrResult.setText(result);
            }
        }
    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        //mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private  String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message;
        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            message = labels.get(0).getDescription();
        } else {
            message ="nothing";
        }
        return message;
    }

    /*
    param:
    ocr : String from OCR
    */
    private int LCSprob(String ocr){

        // DB 부분작성
        DatabaseHelper mDb;
        mDb = new DatabaseHelper(this);
        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = mDb.getAllAllergy(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = mDb.getAllDisease(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for(Allergy allergy : aList) {
            if(allergy.getIsChecked() == 1) // ischecked가 1이면
                aResultList.add(allergy);
        }

        for(Disease disease : dList) {
            if(disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for(Allergy allergy : aResultList ) {
            Food.add(allergy.getAllergy());
        }

        for(Disease disease : dResultList ) {
            Food.add(disease.getFood_name());
        }

        int i;
        int index = -1; // Index of Food list
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        double two = 2.0; // Threshold for case of two characters
        double three = 3.0; // Threshold for case of three characters
        for(i=0;i<Food.size();i++) {

            //Case of one character
            //Case that length of CommonSubsequence = 1
            if(Food.get(i).length()==1){
                if(lcs.length(ocr,Food.get(i))==1.0){
                    index = i;
                }
            }
            //Case of two characters
            //Choose case that length of CommonSubsequence is maximum
            else if(Food.get(i).length()==2) {
                if (lcs.length(ocr, Food.get(i)) >= two) {
                    two = lcs.length(ocr, Food.get(i));
                    index = i;
                }
            }
            //Case of three characters
            //Choose case that length of CommonSubsequence is maximum
            else if(Food.get(i).length()>=3) {
                if (lcs.length(ocr, Food.get(i)) >= three) {
                    three = lcs.length(ocr, Food.get(i));
                    index = i;
                }
            }

        }

        return index;
    }

    /*
    param:
    ocr : String from OCR (seperateKOR)
    */
    private int LCSkor(String ocr){

        // DB 부분작성
        DatabaseHelper mDb;
        mDb = new DatabaseHelper(this);
        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = mDb.getAllAllergy(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = mDb.getAllDisease(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for(Allergy allergy : aList) {
            if(allergy.getIsChecked() == 1) // ischecked가 1이면
                aResultList.add(allergy);
        }

        for(Disease disease : dList) {
            if(disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for(Allergy allergy : aResultList ) {
            Food.add(allergy.getAllergy());
        }

        for(Disease disease : dResultList ) {
            Food.add(disease.getFood_name());
        }

        int i;
        int index = -1;
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        double temp = 50.0;
        for(i=0;i<Food.size();i++) {
            String Sfood = seperateKOR(Food.get(i));
            //Case of inclusion relationship
            if (lcs.length(ocr, Sfood) == (double) Sfood.length()) {
                index = i;
            }
            //Use korean consonant and vowel for getting score
            else if (ocr.length() <= Sfood.length()) {
                double score = (double) Sfood.length() - lcs.length(Sfood, ocr);
                if (score <= 1.0) {
                    if (score < temp) {
                        temp = score;
                        index = i;
                    }
                }
            }
        }

        return index;
    }
    /*
    param:
    ocr : String
    seperate(Consonant, vowel)
    module for seperating korean consonant and vowel using unicode
     */
    private String seperateKOR(String ocr){
        int HANGEUL_BASE = 0xAC00;
        int HANGEUL_END = 0xD7AF;
        int CHO_BASE = 0x1100;
        int JUNG_BASE = 0x1161;
        int JONG_BASE = (int)0x11A8 - 1;
        int JA_BASE = 0x3131;
        int MO_BASE = 0x314F;

        StringBuilder list = new StringBuilder();

        for(char c : ocr.toCharArray()) {

            if((c <= 10 && c <= 13) || c == 32) {
                list.append(c);
                continue;
            } else if (c >= JA_BASE && c <= JA_BASE + 36) {
                list.append(c);
                continue;
            } else if (c >= MO_BASE && c <= MO_BASE + 58) {
                list.append((char)0);
                continue;
            } else if (c >= HANGEUL_BASE && c <= HANGEUL_END){
                int choInt = (c - HANGEUL_BASE) / 28 / 21;
                int jungInt = ((c - HANGEUL_BASE) / 28) % 21;
                int jongInt = (c - HANGEUL_BASE) % 28;
                char cho = (char) (choInt + CHO_BASE);
                char jung = (char) (jungInt + JUNG_BASE);
                char jong = jongInt != 0 ? (char) (jongInt + JONG_BASE) : 0;

                list.append(cho);
                list.append(jung);
                list.append(jong);
            } else {
                list.append(c);
            }

        }
        String res = list.toString();
        return res;

    }

    //LCS 결과값에 따라 결과값 추출
    private String Correction(String message){

        // DB 부분작성
        DatabaseHelper mDb;
        mDb = new DatabaseHelper(this);
        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = mDb.getAllAllergy(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = mDb.getAllDisease(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for(Allergy allergy : aList) {
            if(allergy.getIsChecked() == 1) // ischecked가 1이면
                aResultList.add(allergy);
        }

        for(Disease disease : dList) {
            if(disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for(Allergy allergy : aResultList ) {
            Food.add(allergy.getAllergy());
        }

        for(Disease disease : dResultList ) {
            Food.add(disease.getFood_name());
        }

        message.replaceAll("\n","");

        List<String> result = Arrays.asList(message.split(",|\\(|\\)| "));
        String delim = "\n";
        StringBuilder test = new StringBuilder();
        int k;
        for(k=0;k<result.size();k++){
            int p = LCSkor(seperateKOR(result.get(k)));
            int r = LCSprob(result.get(k));
            //Check if the return value is same between result of LCSkor and result of LCSprob
            if(p == r) {
                if (p != -1) {
                    test.append(Food.get(p));
                    test.append(delim);
                }
            }
        }

        String ts = test.toString();
        return ts;
    }

}
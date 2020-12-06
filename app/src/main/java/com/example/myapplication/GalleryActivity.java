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

    protected static class LableDetectionTask extends AsyncTask<Object, Void, String> {
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

    private static String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message;
        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            message = labels.get(0).getDescription();
        } else {
            message ="nothing";
        }

        return message;
    }

    private static String split(String message){
        List<String> result = Arrays.asList(message.split(","));

        String delim = "\n";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < result.size() - 1) {
            sb.append(result.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(result.get(i));

        String res = sb.toString();

        return res;
    }
    /*
    param:
    s1 : String from OCR
    */
    private static int LCS(String ocr){

        List<String> Food = Arrays.asList("카페인", "가다랑어", "자몽", "알코올", "니코틴", "자몽", "참치", "철분", "마그네슘", "생강", "마늘", "오렌지", "감초캔디", "칼륨", "아스파라거스", "민들레차", "칼슘", "녹차", "비타민E", "비타민A", "인삼", "은행엽", "감자", "민들레", "철분보충제", "마그네슘보충제", "탄산염제산제", "칼슘인", "아연", "구리", "제산제", "칼륨보충제", "멜라토닌", "사과", "오렌지", "계란", "우유", "메밀", "땅콩", "대두", "밀", "고등어", "게", "새우", "돼지고기", "복숭아", "토마토", "아황산류", "호두", "닭고기", "쇠고기", "오징어", "잣", "조개류");

        int i;
        int index = -1;
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        double j = 2.0;
        for(i=0;i<Food.size();i++) {
            //한글자인 경우
            if(Food.get(i).length()==1){
                if(lcs.length(ocr,Food.get(i))==1.0){
                    index = i;
                }
            }
            //2글자인 경우
            else if(Food.get(i).length()==2) {
                if (lcs.length(ocr, Food.get(i)) >= j) {
                    j = lcs.length(ocr, Food.get(i));
                    index = i;
                }
            }
            //3글자인 경우
            else if(Food.get(i).length()==3) {
                if (lcs.length(ocr, Food.get(i)) >= 3.0) {
                    j = lcs.length(ocr, Food.get(i));
                    index = i;
                }
            }
        }

        return index;
    }
    //LCS 결과값에 따라 결과값 추출
    private static String Correction(String message){
        List<String> Food = Arrays.asList("카페인", "가다랑어", "자몽", "알코올", "니코틴", "자몽", "참치", "철분", "마그네슘", "생강", "마늘", "오렌지", "감초캔디", "칼륨", "아스파라거스", "민들레차", "칼슘", "녹차", "비타민E", "비타민A", "인삼", "은행엽", "감자", "민들레", "철분보충제", "마그네슘보충제", "탄산염제산제", "칼슘인", "아연", "구리", "제산제", "칼륨보충제", "멜라토닌", "사과", "오렌지", "계란", "우유", "메밀", "땅콩", "대두", "밀", "고등어", "게", "새우", "돼지고기", "복숭아", "토마토", "아황산류", "호두", "닭고기", "쇠고기", "오징어", "잣", "조개류");
        List<String> result = Arrays.asList(message.split(",| "));
        String delim = "\n";
        StringBuilder test = new StringBuilder();
        int k;
        for(k=0;k<result.size();k++){
            int j = LCS(result.get(k));
            if(j!=-1){
                //Collections.replaceAll(result,result.get(k),Food.get(j));
                test.append(Food.get(j));
                test.append(delim);
            }
        }
        String ts = test.toString();
        return ts;
/*
        String delim = "\n";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < result.size() - 1) {
            sb.append(result.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(result.get(i));

        String res = sb.toString();

        return res;
 */
    }



/*
s1:Accurate result
s2:OCR result
 */
    private static void weightedLevenshtein(String s1, String s2, String message){

        WeightedLevenshtein wl = new WeightedLevenshtein(new CharacterSubstitutionInterface() {
            public double cost(char c1, char c2) {

                List<String> result = Arrays.asList(message.split(",| "));
                MetricLCS lcs = new MetricLCS();
                int i=0;
                for(i=0;i<s1.length();i++){
                    c1 = s1.charAt(i);
                    c2 = s2.charAt(i);
                }
                // The cost for substituting 't' and 'r' is considered
                // smaller as these 2 are located next to each other
                // on a keyboard
                switch(c2){
                    case '지':
                        if(c1 == '제')
                        return 0.5;
                    case '왕':
                        if(c1 == '황')
                        return 0.5;
                    case '운':
                        if(c1 == '우')
                        return 0.5;
                    case '둘':
                        if(c1 == '물')
                        return 0.5;
                    case '첩':
                        if(c1 == '찹')
                        return 0.5;
                    case '세':
                        if(c1 == '베')
                        return 0.5;
                    case '요':
                        if(c1 == '유')
                        return 0.5;
                    case '덕':
                        if(c1 == '덱')
                        return 0.5;
                    case '역':
                        if(c1 == '엑')
                        return 0.5;
                    case '사':
                        if(c1 == '새')
                        return 0.5;
                    case '소':
                        if(c1 == '스')
                        return 0.5;
                    case '여':
                        if(c1 == '야')
                        return 0.5;
                    case '제':
                        if(c1 == '채')
                        return 0.5;
                    case '유':
                        if(c1 == '육')
                        return 0.5;
                    case '재':
                        if(c1 == '제')
                        return 0.5;
                    case '항':
                        if(c1 == '향')
                        return 0.5;
                    case '기':
                        if(c1 == '개')
                        return 0.5;
                    case '내':
                        if(c1 == '매')
                        return 0.5;
                    case '공':
                        if(c1 == '농')
                        return 0.5;
                    case '로':
                        if(c1 == '료')
                        return 0.5;
                    case '한':
                        if(c1 == '합')
                        return 0.5;
                    case '행':
                        if(c1 == '향')
                        return 0.5;
                    case '배':
                        if(c1 == '베')
                        return 0.5;
                    case '이':
                        if(c1 == '인')
                        return 0.5;
                    case '서':
                        if(c1 == '산')
                        return 0.5;
                    case '청':
                        if(c1 == '참')
                        return 0.5;
                    case '처':
                        if(c1 == '치')
                        return 0.5;
                    case '험':
                        if(c1 == '한')
                        return 0.5;
                    case '영':
                        if(c1 == '량')
                        return 0.5;
                    case '장':
                        if(c1 == '정')
                        return 0.5;
                    case '아':
                        if(c1 == '이')
                        return 0.5;
                }

                // For most cases, the cost of substituting 2 characters
                // is 1.0
                return 1.0;
            }
        });

    }
}
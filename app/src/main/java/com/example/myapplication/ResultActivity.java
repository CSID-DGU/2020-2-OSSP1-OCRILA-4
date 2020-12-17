package com.example.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Allergy;
import com.example.myapplication.model.Disease;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.debatty.java.stringsimilarity.LongestCommonSubsequence;

public class ResultActivity extends AppCompatActivity {
    private static final String CLOUD_VISION_API_KEY = BuildConfig.API_KEY;
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_TEXT_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;
    private static final String TAG = ResultActivity.class.getSimpleName();

    ImageView result_iv;
    Uri uri;
    TextView result_allergy_detail;
    TextView result_disease_detail;

    DatabaseHelper mDb;
    List<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        uri = getIntent().getParcelableExtra("cropResult");
        result_iv = findViewById(R.id.result_image_view);
        result_iv.setImageURI(uri);
        result_allergy_detail = findViewById(R.id.result_allergy_details);
        result_disease_detail = findViewById(R.id.result_disease_details);
        mDb = new DatabaseHelper(this);
        uploadImage(uri);
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

    private class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<ResultActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(ResultActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                BatchAnnotateImagesResponse response = mRequest.execute();
                //list 받아오기
                info = Correction(convertResponseToString(response));
                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "인식 실패 다시 시도해주세요." + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "인식 실패 다시 시도해주세요." +
                        e.getMessage());
            }
            return "인식 실패 다시 시도해주세요.";
        }

        protected void onPostExecute(String result) { //출력
            ResultActivity activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                // TextView tv_ocrResult = activity.findViewById(R.id.result_image_details); 삭제
                // tv_ocrResult.setText(result);

                List<Disease> result_disease = mDb.checkDisease(info);
                List<String> result_allergy = mDb.checkAllergy(info);


                if (result_allergy.toString() != "[]") {
                    result_allergy_detail.setTextColor(Color.RED); //null 아니면 색깔 변경.
                    result_allergy_detail.setText(result_allergy.toString());
                } else {
                    result_allergy_detail.setTextColor(Color.GREEN);
                    result_allergy_detail.setText("검출된 유해한 성분이 없습니다.");
                }

                if (result_disease.size() > 0) {
                    result_disease_detail.setTextColor(Color.RED);
                    String text=null;
                    StringBuilder str = new StringBuilder();
                    for (Disease disease : result_disease) { //for문으로 해당 객체 하나씩 중괄호 작업 반복
                        str.append("질병이름 : " + disease.getDisease()+", "); // 이름 문자열 뒤에 추가
                        str.append("위험음식 : " + disease.getFood_name()+", "); // 음식 문자열 뒤에 추가
                        str.append("관련약물 : " + disease.getDrug()); // 약물 문자열 뒤에 추가
                        str.append("\n\n");
                        text = str.toString(); // string형으로 형변환
                    }
                    result_disease_detail.setText(text);
                } else if (result_disease.size() == 0) {
                    result_disease_detail.setTextColor(Color.GREEN);
                    result_disease_detail.setText("검출된 유해한 성분이 없습니다.");
                }
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

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message;
        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            message = labels.get(0).getDescription();
        } else {
            message = "nothing";
        }
        Log.d("test",message);
        return message;
    }

    //단어 사전

    private List<Integer> LCSprob(String ocr){

        // DB 부분작성

        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = new ArrayList<Allergy>(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = new ArrayList<Disease>(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for (Allergy allergy : aList) {
            if (allergy.getIsChecked() == 1) {
                aResultList.add(allergy);
                if (allergy.getAllergy().equals("소고기")) {
                    Allergy data1 = new Allergy();
                    data1.setAllergy("쇠고기");
                    aResultList.add(data1);
                } else if (allergy.getAllergy().equals("난류")) {
                    Allergy data1 = new Allergy();
                    Allergy data2 = new Allergy();
                    data1.setAllergy("계란");
                    data2.setAllergy("달걀");
                    aResultList.add(data1);
                    aResultList.add(data2);
                    Log.v("db검출", data1.getAllergy());
                }
            }
        }

        for (Disease disease : dList) {
            if (disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for (Allergy allergy : aResultList) {
            Food.add(allergy.getAllergy());
        }

        for (Disease disease : dResultList) {
            Food.add(disease.getFood_name());
        }
        int i;
        //int index = -1; // Index of Food list
        List<Integer> index = new ArrayList<>();
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        double two = 1.0; // Threshold for case of two characters
        double three = 1.0; // Threshold for case of three characters
        for(i=0;i<Food.size();i++) {
            //Unique Error case between 호주 and 호두
            if(ocr=="호주"){}
            //Case of vitamin
            //Exception because LCS can't tell the difference
            else if(Food.get(i)=="비타민A" || Food.get(i)=="비타민E"){
                if(lcs.length(ocr,Food.get(i))==4.0){
                    index.add(i);
                }
            }
            //Case of one character
            //Case that length of CommonSubsequence = 1
            else if(Food.get(i).length()==1){
                if(lcs.length(ocr,Food.get(i))==1.0){
                    index.add(i);
                }
            }
            //Case of two characters
            //Choose case that length of CommonSubsequence is maximum
            else if(Food.get(i).length()==2) {
                if (lcs.length(ocr, Food.get(i)) >= two) {
                    two = lcs.length(ocr, Food.get(i));
                    index.add(i);
                }
            }
            //Case of three characters
            //Choose case that length of CommonSubsequence is maximum
            else if(Food.get(i).length()>=3) {
                if (lcs.length(ocr, Food.get(i)) >= three) {
                    three = lcs.length(ocr, Food.get(i));
                    index.add(i);
                }
            }

        }

        return index;
    }

    /*
    param:
    ocr : String from OCR (seperateKOR)
    */
    private List<Integer> LCSkor(String ocr){

        // DB 부분작성

        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = new ArrayList<Allergy>(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = new ArrayList<Disease>(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for (Allergy allergy : aList) {
            if (allergy.getIsChecked() == 1) {
                aResultList.add(allergy);
                if (allergy.getAllergy().equals("소고기")) {
                    Allergy data1 = new Allergy();
                    data1.setAllergy("쇠고기");
                    aResultList.add(data1);
                } else if (allergy.getAllergy().equals("난류")) {
                    Allergy data1 = new Allergy();
                    Allergy data2 = new Allergy();
                    data1.setAllergy("계란");
                    data2.setAllergy("달걀");
                    aResultList.add(data1);
                    aResultList.add(data2);
                    Log.v("db검출", data1.getAllergy());
                }
            }
        }
        for (Disease disease : dList) {
            if (disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for (Allergy allergy : aResultList) {
            Food.add(allergy.getAllergy());
        }

        for (Disease disease : dResultList) {
            Food.add(disease.getFood_name());
        }
        int i;
        List<Integer> index = new ArrayList<>();
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        double temp = 50.0;
        for(i=0;i<Food.size();i++) {
            String Sfood = seperateKOR(Food.get(i));
            //Case of inclusion relationship
            if (lcs.length(ocr, Sfood) == (double) Sfood.length()) {
                index.add(i);
            }
            //Use korean consonant and vowel for getting score
            else if (ocr.length() <= Sfood.length()) {
                double score = (double) Sfood.length() - lcs.length(Sfood, ocr);
                if (score <= 1.0) {
                    if (score <= temp) {
                        temp = score;
                        index.add(i);
                    }
                }
            }
        }

        return index;
    }


    private String seperateKOR(String ocr) {
        int HANGEUL_BASE = 0xAC00;
        int HANGEUL_END = 0xD7AF;
        int CHO_BASE = 0x1100;
        int JUNG_BASE = 0x1161;
        int JONG_BASE = (int) 0x11A8 - 1;
        int JA_BASE = 0x3131;
        int MO_BASE = 0x314F;

        StringBuilder list = new StringBuilder();

        for (char c : ocr.toCharArray()) {

            if ((c <= 10 && c <= 13) || c == 32) {
                list.append(c);
                continue;
            } else if (c >= JA_BASE && c <= JA_BASE + 36) {
                list.append(c);
                continue;
            } else if (c >= MO_BASE && c <= MO_BASE + 58) {
                list.append((char) 0);
                continue;
            } else if (c >= HANGEUL_BASE && c <= HANGEUL_END) {
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
    private ArrayList<String> Correction(String message) {

        // DB 부분작성

        List<Allergy> aList = mDb.getAllAllergy(); // 검색할 질병 리스트
        List<Disease> dList = mDb.getAllDisease(); //검색할 알러지 리스트

        List<Allergy> aResultList = new ArrayList<Allergy>(); // 검색된 값을 저장할 알러지 리스트
        List<Disease> dResultList = new ArrayList<Disease>(); //검색된 값을 저장할 질병 리스트

        List<String> Food = new ArrayList<>(); //추출된 알러지 리스트


        for (Allergy allergy : aList) {
            if (allergy.getIsChecked() == 1) {
                aResultList.add(allergy);
                if (allergy.getAllergy().equals("소고기")) {
                    Allergy data1 = new Allergy();
                    data1.setAllergy("쇠고기");
                    aResultList.add(data1);
                } else if (allergy.getAllergy().equals("난류")) {
                    Allergy data1 = new Allergy();
                    Allergy data2 = new Allergy();
                    data1.setAllergy("계란");
                    data2.setAllergy("달걀");
                    aResultList.add(data1);
                    aResultList.add(data2);
                }
            }
        }

        for (Disease disease : dList) {
            if (disease.getIsChecked() == 1) // ischecked가 1이면
                dResultList.add(disease);
        }

        // 해당 객체 리스트에서 음식명만 꺼내서 나눠주기

        for (Allergy allergy : aResultList) {
            Food.add(allergy.getAllergy());
        }

        for (Disease disease : dResultList) {
            Food.add(disease.getFood_name());
        }


        //LCS 결과 리스트에 저장
        message.replaceAll("\n","");
        List<String> result = Arrays.asList(message.split(",|\\(|\\)| "));
        ArrayList<String> test = new ArrayList<>();
        ArrayList<String> real_result = new ArrayList<>();
        String temp = "";

        int k;
        for (k = 0; k < result.size(); k++) {
            List<Integer> r = LCSprob(result.get(k));
            //Check if the return value is same between result of LCSkor and result of LCSprob
            List<Integer> p = LCSkor(seperateKOR(result.get(k)));
            int h; int m;
            for(h=0;h<p.size();h++){
                for(m=0;m<r.size();m++){
                    if(p.get(h) == r.get(m)) {
                        if (r.get(m) != -1) {
                            test.add(Food.get(r.get(m)));
                            break;
                        }
                    }
                }
            }
        }

        //중복값 제거
        for (String data : test) {
            if (!real_result.contains(data))
                real_result.add(data);
        }


        return real_result;
    }

}

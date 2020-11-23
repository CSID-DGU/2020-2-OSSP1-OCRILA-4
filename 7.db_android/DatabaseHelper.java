package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "disease_allergy.db";

    public static final String TABLE_DISEASE = "table_disease";
    public static final String TABLE_ALLERGY = "table_allergy";

    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_disease = "CREATE TABLE " + TABLE_DISEASE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "disease text not null, " +
                "food_name text not null, " + // 음식, 약물 모두 not null 맞나? 둘중에 하나 null이 될 수 있을 것인가
                "drug text not null, " +
                "isChecked INTEGER DEFAULT 0 );"; // bool 값이 sqlite에 없기에 integer로 설정


        String table_allergy =  "CREATE TABLE " + TABLE_ALLERGY + "( allergy text PRIMARY KEY, " +
                "isChecked INTEGER DEFAULT 0 );";

        //테이블 생성
       db.execSQL(table_disease);
       db.execSQL(table_allergy);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 테이블이 존재하면 삭제
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGY);
        onCreate(db);
    }

    // 질병 튜플 insert문
    public long insertDisease(String disease, String food_name, String drug) { //id는 자동생성, isChecked는 Default 생성이기에 3개 파라미터만

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("disease", disease);
        contentValues.put("food_name", food_name);
        contentValues.put("drug", drug);

        long result = db.insert(TABLE_DISEASE, null, contentValues);

       return result;
    }

    // 질병 튜플 update문 (isChecked만 바꿔주는)
    public boolean updateDisease(String disease) { // 파라미터 -검색 단위? 질병이름으로? [확인 필요]
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 1);

        // updating row
        return db.update(TABLE_DISEASE, values, "disease=" + disease, null) >0;

        /*
        이 부분을 잘 모르겠는게, 디지즈 테이블의 최소성을 보장하는 것은 id임.
        disease 속성이 최소성을 보장하지 못한다면, 위 update문에서 에러가 발생하지 않을까 싶음
         */
    }

    // 알러지 튜플 insert문
    public long insertAllergy(String allergy) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("allergy", allergy);

        long result = db.insert(TABLE_ALLERGY, null, contentValues);

        return result;
    }

    // 알러지 튜플 update문
    public boolean updateAllergy(String allergy) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 1);

        // updating row
        return db.update(TABLE_DISEASE, values, "allergy=" + allergy, null) >0;
    }


    // 모든 질병을 List로 내놓으면 될까? hashMap 자료형 형태가 괜찮으려나? 잘 모르겄네
    // https://gist.github.com/fazlurr/6a4f7e99ab6aa9078903 참고

    자료형별로 에제에서는 클래스를 정의해서 만들었는데 우리도 그렇게 해야될 것 같기도 하고

    public List<String> getAllDisease(){
        List<String> diseaseList = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE;

    }
}
package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.model.Allergy;
import com.example.myapplication.model.Disease;

import java.lang.reflect.Array;
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

    /*
        이 부분을 잘 모르겠는게, 디지즈 테이블의 최소성을 보장하는 것은 id임.
        disease 속성이 최소성을 보장하지 못한다면, 위 update문에서 에러가 발생하지 않을까 싶음

        우리가 구현할 메소드의 목적 => select를 다 한다음에, 그 isCheck 바꿔주는 것.
        isCheck 바꿔주는 것 = updateDisease() 메소드가 필요한 이유!
     */

    // 디지즈 튜플 checked를 1으로 update
    public boolean updateDiseaseToTrue(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 1);

        // updating row
        return db.update(TABLE_DISEASE, values, "id=" + id, null) >0;
    }

    // 디지즈 튜플 checked를 0으로 update
    public boolean updateDiseaseToFalse(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 0);

        // updating row
        return db.update(TABLE_DISEASE, values, "id=" + id, null) >0;
    }

    // 알러지 튜플 insert문
    public long insertAllergy(String allergy) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("allergy", allergy);

        long result = db.insert(TABLE_ALLERGY, null, contentValues);

        return result;
    }

    // 알러지 튜플 checked를 1으로 update
    public boolean updateAllergyToTrue(String allergy) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 1);

        // updating row
        return db.update(TABLE_ALLERGY, values, "allergy=" +  "\"" + allergy + "\"" , null) >0;
    }

    // 알러지 튜플 checked를 0으로 update
    public boolean updateAllergyToFalse(String allergy) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("isChecked", 0);

        // updating row
        return db.update(TABLE_ALLERGY, values, "allergy=" +  "\"" + allergy + "\"" , null) >0;
    }


    // 모든 질병을 List로 내놓으면 될까? hashMap 자료형 형태가 괜찮으려나? 잘 모르겄네
    // https://gist.github.com/fazlurr/6a4f7e99ab6aa9078903 참고

    public List<Disease> getAllDisease() {
        List<Disease> diseases = new ArrayList<Disease>();

        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Disease td = new Disease();
                td.setId(c.getInt((c.getColumnIndex("id"))));
                td.setDisease((c.getString(c.getColumnIndex("disease"))));
                td.setFood_name(c.getString(c.getColumnIndex("food_name")));
                td.setDrug(c.getString(c.getColumnIndex("drug")));
                td.setIsChecked(c.getInt((c.getColumnIndex("isChecked"))));

                // adding to todo list
                diseases.add(td);
            } while (c.moveToNext());
        }

        return diseases;
    }

    public List<Allergy> getAllAllergy() {
        List<Allergy> allergys = new ArrayList<Allergy>();
        String selectQuery = "SELECT  * FROM " + TABLE_ALLERGY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Allergy t = new Allergy();
                t.setAllergy(c.getString(c.getColumnIndex("allergy")));
                t.setIsChecked(c.getInt(c.getColumnIndex("isChecked")));

                // adding to tags list
                allergys.add(t);
            } while (c.moveToNext());
        }
        return allergys;
    }

    //질병이름으로 해당 질병 객체를 검색해 가져오는 메소드
    public List<Disease> getDisease(String disease_name) {

        List<Disease> diseases = new ArrayList<Disease>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE + " WHERE "
                + "disease" + " = " + "\""  + disease_name + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Disease td = new Disease();
                td.setId(c.getInt((c.getColumnIndex("id"))));
                td.setDisease((c.getString(c.getColumnIndex("disease"))));
                td.setFood_name(c.getString(c.getColumnIndex("food_name")));
                td.setDrug(c.getString(c.getColumnIndex("drug")));
                td.setIsChecked(c.getInt((c.getColumnIndex("isChecked"))));

                // adding to
                diseases.add(td);
            } while (c.moveToNext());
        }

        return diseases;
    }


    // 단어사전에서 받아온 list에서 해당 질병의 이름으로 질병을 검색하고 해당 질병 객체를 list에 넣는다.
    public List<Disease> checkDisease(List<String> textList) {

        List<Disease> diseasesList = new ArrayList<Disease>();
        SQLiteDatabase db = this.getReadableDatabase();

        for (String food_name : textList) {

            String selectQuery = "SELECT  * FROM " + TABLE_DISEASE + " WHERE "
                    + "food_name" + " = " + "\"" + food_name + "\"" + " AND "
                    + "isChecked" + " = " + "\"" + "1" + "\"";

            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                do {
                    Disease td = new Disease();
                    td.setId(c.getInt((c.getColumnIndex("id"))));
                    td.setDisease((c.getString(c.getColumnIndex("disease"))));
                    td.setFood_name(c.getString(c.getColumnIndex("food_name")));
                    td.setDrug(c.getString(c.getColumnIndex("drug")));
                    td.setIsChecked(c.getInt((c.getColumnIndex("isChecked"))));

                    // adding to
                    diseasesList.add(td);
                } while (c.moveToNext());
            }
        }
        return diseasesList;
    }

    public  List<String> checkAllergy(List<String> textList) {

        String[] allAllergy = {"난류", "우유", "메밀", "땅콩", "대두", "밀", "고등어", "게", "새우",
                "돼지고기", "복숭아", "토마토", "아황산류", "호두", "닭고기", "잣", "오징어", "소고기", "쇠고기","계란","달걀", "조개류"};

        List<String> aList = new ArrayList<>();

        for(int i=0; i < textList.size(); i++) {
            for(int j=0; j < allAllergy.length; j++) {
                if(textList.get(i).equals(allAllergy[j])) {
                    aList.add(allAllergy[j]);
                    break;
                }
            }
        }
        return aList;
    }
}
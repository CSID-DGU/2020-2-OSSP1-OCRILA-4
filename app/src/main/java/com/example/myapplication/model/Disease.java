package com.example.myapplication.model;

public class Disease {

    int id;
    String disease;
    String food_name;
    String drug;
    int isChecked;

    public Disease() {
    }

    public Disease(String disease, String food_name, String drug, int isChecked) {
        this.disease = disease;
        this.food_name = food_name;
        this.drug = drug;
        this.isChecked = isChecked;
    }

    public Disease(int id, String disease, String food_name, String drug, int isChecked) {
        this.id = id;
        this.disease = disease;
        this.food_name = food_name;
        this.drug = drug;
        this.isChecked = isChecked;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}

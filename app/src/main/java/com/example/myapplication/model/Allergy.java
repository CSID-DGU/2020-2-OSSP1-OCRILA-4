package com.example.myapplication.model;

public class Allergy {

    String Allergy;
    int isChecked;

    public Allergy() {

    }

    public Allergy(String allergy, int isChecked) {
        Allergy = allergy;
        this.isChecked = isChecked;
    }

    public String getAllergy() {
        return Allergy;
    }

    public void setAllergy(String allergy) {
        Allergy = allergy;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}

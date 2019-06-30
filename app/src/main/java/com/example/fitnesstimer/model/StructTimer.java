package com.example.fitnesstimer.model;

import android.text.TextUtils;

public class StructTimer {

    private String nameStep;
    private int namberStep;
    private int newStep;

    public StructTimer() {
    }

    public StructTimer(String nameStep, int namberStep, int newStep) {
        this.nameStep = nameStep;
        this.namberStep = namberStep;
        this.newStep = newStep;
    }

    public String getNameStep() {
        return nameStep;
    }

    public void setNameStep(String nameStep) {
        this.nameStep = nameStep;
    }

    public int getNamberStep() {
        return namberStep;
    }

    public void setNamberStep(int namberStep) {
        this.namberStep = namberStep;
    }

    public int getNewStep() {
        return newStep;
    }

    public void setNewStep(int newStep) {
        this.newStep = newStep;
    }

    public void setNamberStepToString(String namberStep) {
        if(TextUtils.isEmpty(namberStep)){
            this.namberStep = Integer.parseInt(namberStep);
        }else {
            this.namberStep =0;
        }

    }
}

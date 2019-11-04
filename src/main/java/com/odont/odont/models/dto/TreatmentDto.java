package com.odont.odont.models.dto;

import com.odont.odont.models.entity.TreatmentEntity;

public class TreatmentDto {
    private int treatmentId;
    private String nameTreatment;
    private double costTreatment;
    private Object duration;

    public TreatmentDto() {
    }
    public TreatmentDto(TreatmentEntity treatmentEntity) {
        this.nameTreatment = treatmentEntity.getNameTreatment();
        this.costTreatment = treatmentEntity.getCostTreatment();
        this.duration = treatmentEntity.getDuration();
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getNameTreatment() {
        return nameTreatment;
    }

    public void setNameTreatment(String nameTreatment) {
        this.nameTreatment = nameTreatment;
    }

    public double getCostTreatment() {
        return costTreatment;
    }

    public void setCostTreatment(double costTreatment) {
        this.costTreatment = costTreatment;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }
}

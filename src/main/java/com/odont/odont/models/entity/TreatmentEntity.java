package com.odont.odont.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "treatment", schema = "db_odont", catalog = "")
public class TreatmentEntity {
    private int treatmentId;
    private String nameTreatment;
    private double costTreatment;
    private Object duration;

    @Id
    @Column(name = "treatment_id")
    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    @Basic
    @Column(name = "name_treatment")
    public String getNameTreatment() {
        return nameTreatment;
    }

    public void setNameTreatment(String nameTreatment) {
        this.nameTreatment = nameTreatment;
    }

    @Basic
    @Column(name = "cost_treatment")
    public double getCostTreatment() {
        return costTreatment;
    }

    public void setCostTreatment(double costTreatment) {
        this.costTreatment = costTreatment;
    }

    @Basic
    @Column(name = "duration")
    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentEntity that = (TreatmentEntity) o;
        return treatmentId == that.treatmentId &&
                Double.compare(that.costTreatment, costTreatment) == 0 &&
                Objects.equals(nameTreatment, that.nameTreatment) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatmentId, nameTreatment, costTreatment, duration);
    }
}

package com.odont.odont.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "plan_treatment", schema = "db_odont", catalog = "")
public class PlanTreatmentEntity {
    private int planTreatmentId;

    @Id
    @Column(name = "plan_treatment_id")
    public int getPlanTreatmentId() {
        return planTreatmentId;
    }

    public void setPlanTreatmentId(int planTreatmentId) {
        this.planTreatmentId = planTreatmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanTreatmentEntity that = (PlanTreatmentEntity) o;
        return planTreatmentId == that.planTreatmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planTreatmentId);
    }
}

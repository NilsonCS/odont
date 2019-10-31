package com.odont.odont.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient", schema = "db_odont", catalog = "")
public class PatientEntity {
    private int patientId;
    private String ci;
    private String name;
    private int age;
    private String sex;
    private String phone;
    private String refPhone;
    private String refFamily;

    @Id
    @Column(name = "patient_id")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "CI")
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "ref_phone")
    public String getRefPhone() {
        return refPhone;
    }

    public void setRefPhone(String refPhone) {
        this.refPhone = refPhone;
    }

    @Basic
    @Column(name = "ref_family")
    public String getRefFamily() {
        return refFamily;
    }

    public void setRefFamily(String refFamily) {
        this.refFamily = refFamily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return patientId == that.patientId &&
                age == that.age &&
                Objects.equals(ci, that.ci) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(refPhone, that.refPhone) &&
                Objects.equals(refFamily, that.refFamily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, ci, name, age, sex, phone, refPhone, refFamily);
    }
}

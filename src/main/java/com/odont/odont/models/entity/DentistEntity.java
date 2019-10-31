package com.odont.odont.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dentist", schema = "db_odont", catalog = "")
public class DentistEntity {
    private int dentistId;
    private String name;
    private String specialty;

    @Id
    @Column(name = "dentist_id")
    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
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
    @Column(name = "specialty")
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DentistEntity that = (DentistEntity) o;
        return dentistId == that.dentistId &&
                Objects.equals(name, that.name) &&
                Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dentistId, name, specialty);
    }
}

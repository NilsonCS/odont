package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "materials", schema = "db_odont", catalog = "")
public class MaterialsEntitya {
    private long idMaterials;
    private String materialName;
    private double priceIn;
    private double priceOut;
    private Date dateIn;
    private double dateOut;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Timestamp deleteAt;

    @Id
    @Column(name = "idMaterials")
    public long getIdMaterials() {
        return idMaterials;
    }

    public void setIdMaterials(long idMaterials) {
        this.idMaterials = idMaterials;
    }

    @Basic
    @Column(name = "material_name")
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Basic
    @Column(name = "Price_in")
    public double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(double priceIn) {
        this.priceIn = priceIn;
    }

    @Basic
    @Column(name = "Price_out")
    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    @Basic
    @Column(name = "Date_in")
    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    @Basic
    @Column(name = "Date_out")
    public double getDateOut() {
        return dateOut;
    }

    public void setDateOut(double dateOut) {
        this.dateOut = dateOut;
    }

    @Basic
    @Column(name = "Created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "Update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "Delete_at")
    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialsEntitya that = (MaterialsEntitya) o;
        return idMaterials == that.idMaterials &&
                Double.compare(that.priceIn, priceIn) == 0 &&
                Double.compare(that.priceOut, priceOut) == 0 &&
                Double.compare(that.dateOut, dateOut) == 0 &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updateAt, that.updateAt) &&
                Objects.equals(deleteAt, that.deleteAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterials, materialName, priceIn, priceOut, dateIn, dateOut, createdAt, updateAt, deleteAt);
    }
}

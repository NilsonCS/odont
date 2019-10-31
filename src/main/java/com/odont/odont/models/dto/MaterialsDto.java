package com.odont.odont.models.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MaterialsDto {
    private Integer idMaterials;
    private String materialName;
    private double priceIn;
    private double priceOut;
    private Date dateIn;
    private Date dateOut;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Timestamp deleteAt;

    public MaterialsDto(){

    }

    public Integer getIdMaterials() {
        return idMaterials;
    }

    public void setIdMaterials(Integer idMaterials) {
        this.idMaterials = idMaterials;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(double priceIn) {
        this.priceIn = priceIn;
    }

    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }
}

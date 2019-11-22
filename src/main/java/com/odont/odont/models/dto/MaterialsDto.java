package com.odont.odont.models.dto;

import com.odont.odont.models.entity.MaterialsEntity;

import java.util.Date;

public class MaterialsDto {

    private Long idmaterials;
    private String name;
    private double priceIn;
    private double priceOut;
    private Date dateIn;
    private Date dateOut;

    public MaterialsDto(Long idmaterials, String name, double priceIn, double priceOut, Date dateIn, Date dateOut) {
        this.idmaterials = idmaterials;
        this.name = name;
        this.priceIn = priceIn;
        this.priceOut = priceOut;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public MaterialsDto(MaterialsEntity materialsEntity) {
    }

    public Long getIdmaterials() {
        return idmaterials;
    }

    public void setIdmaterials(Long idmaterials) {
        this.idmaterials = idmaterials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

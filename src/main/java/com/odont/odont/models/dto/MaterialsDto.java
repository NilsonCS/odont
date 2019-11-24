package com.odont.odont.models.dto;



import java.sql.Date;

public class MaterialsDto {

    private long idmaterials;
    private String codeMaterials;
    private String name;
    private double priceIn;
    private double priceOut;
    private java.sql.Date dateIn;
    private java.sql.Date dateOut;

    public MaterialsDto(int idmaterials, String codeMaterials, String name, double priceIn, double priceOut, Date dateIn, Date dateOut) {
        this.idmaterials = idmaterials;
        this.codeMaterials = codeMaterials;
        this.name = name;
        this.priceIn = priceIn;
        this.priceOut = priceOut;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public long getIdmaterials() {
        return idmaterials;
    }

    public void setIdmaterials(int idmaterials) {
        this.idmaterials = idmaterials;
    }

    public String getCodeMaterials() {
        return codeMaterials;
    }

    public void setCodeMaterials(String codeMaterials) {
        this.codeMaterials = codeMaterials;
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
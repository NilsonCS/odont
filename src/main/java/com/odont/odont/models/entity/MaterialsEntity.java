package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "materials", schema = "db_odont", catalog = "")
public class MaterialsEntity {
    private long idmaterials;
    private String codeMaterials;
    private String name;
    private double priceIn;
    private double priceOut;
    private Date dateIn;
    private Date dateOut;

    @Id
    @Column(name = "idmaterials")
    public long getIdmaterials() {
        return idmaterials;
    }

    public void setIdmaterials(long idmaterials) {
        this.idmaterials = idmaterials;
    }

    @Basic
    @Column(name = "code_materials")
    public String getCodeMaterials() {
        return codeMaterials;
    }

    public void setCodeMaterials(String codeMaterials) {
        this.codeMaterials = codeMaterials;
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
    @Column(name = "price_in")
    public double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(double priceIn) {
        this.priceIn = priceIn;
    }

    @Basic
    @Column(name = "price_out")
    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    @Basic
    @Column(name = "date_in")
    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    @Basic
    @Column(name = "date_out")
    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialsEntity that = (MaterialsEntity) o;
        return idmaterials == that.idmaterials &&
                Double.compare(that.priceIn, priceIn) == 0 &&
                Double.compare(that.priceOut, priceOut) == 0 &&
                Objects.equals(codeMaterials, that.codeMaterials) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmaterials, codeMaterials, name, priceIn, priceOut, dateIn, dateOut);


    }


    @Override
    public String toString() {
        return "MaterialsEntity{" +
                "idmaterials=" + idmaterials +
                ", codeMaterials='" + codeMaterials + '\'' +
                ", name='" + name + '\'' +
                ", priceIn=" + priceIn +
                ", priceOut=" + priceOut +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                '}';
    }

    public MaterialsEntity() {
    }

    public MaterialsEntity(long idmaterials) {
        this.idmaterials = idmaterials;
    }
}

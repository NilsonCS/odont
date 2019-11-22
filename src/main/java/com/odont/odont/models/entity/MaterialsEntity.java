package com.odont.odont.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "materials", schema = "db_odont", catalog = "")
public class MaterialsEntity implements Serializable {
    private long idmaterials;
    private Date dateIn;
    private Date dateOut;
    private String name;
    private double priceIn;
    private double priceOut;

    @Id
    @Column(name = "idmaterials")
    public long getIdmaterials() {
        return idmaterials;
    }

    public void setIdmaterials(long idmaterials) {
        this.idmaterials = idmaterials;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialsEntity that = (MaterialsEntity) o;
        return idmaterials == that.idmaterials &&
                Double.compare(that.priceIn, priceIn) == 0 &&
                Double.compare(that.priceOut, priceOut) == 0 &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmaterials, dateIn, dateOut, name, priceIn, priceOut);
    }

    public MaterialsEntity(){

    }

    public MaterialsEntity(long idmaterials) {
        this.idmaterials = idmaterials;
    }

    @Override
    public String toString(){
        return "MaterialsEntity{"+
                "idmaterials = "+idmaterials+
                ", name = '" + name + '\'' +
                ", priceIn = '" + priceIn + '\'' +
                ", priceOut = '" + priceOut + '\'' +
                ", dateIn = '" + dateIn+ '\'' +
                ", dateOut = '" + dateOut +
                '}';
    }
}

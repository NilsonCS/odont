package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "h_person", schema = "db_odont", catalog = "")
public class HPersonEntity {
    private long hPersonId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String firstSurname;
    private String secondSurname;
    private String thirdSurname;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "h_person_id", nullable = false)
    public long gethPersonId() {
        return hPersonId;
    }

    public void sethPersonId(long hPersonId) {
        this.hPersonId = hPersonId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name", nullable = false, length = 50)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "third_name", nullable = false, length = 50)
    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    @Basic
    @Column(name = "first_surname", nullable = false, length = 50)
    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    @Basic
    @Column(name = "second_surname", nullable = false, length = 50)
    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    @Basic
    @Column(name = "third_surname", nullable = false, length = 50)
    public String getThirdSurname() {
        return thirdSurname;
    }

    public void setThirdSurname(String thirdSurname) {
        this.thirdSurname = thirdSurname;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tx_user", nullable = true, length = 50)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = true, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = true)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HPersonEntity that = (HPersonEntity) o;
        return hPersonId == that.hPersonId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(thirdName, that.thirdName) &&
                Objects.equals(firstSurname, that.firstSurname) &&
                Objects.equals(secondSurname, that.secondSurname) &&
                Objects.equals(thirdSurname, that.thirdSurname) &&
                Objects.equals(status, that.status) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hPersonId, firstName, secondName, thirdName, firstSurname, secondSurname, thirdSurname, status, txUser, txHost, txDate);
    }
}

package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "person_address_id", schema = "db_odont", catalog = "")
public class PersonAddressIdEntity {
    private long personAddressId;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private PersonEntity personByPersonId;
    private AddressEntity addressByAddressId;

    @Id
    @Column(name = "person_address_id", nullable = false)
    public long getPersonAddressId() {
        return personAddressId;
    }

    public void setPersonAddressId(long personAddressId) {
        this.personAddressId = personAddressId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tx_user", nullable = false, length = 50)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = false, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = false)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

//    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private AddressEntity addressId;
//    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private PersonEntity personId;

    public PersonAddressIdEntity(long personAddressId, int status, String txUser, String txHost, Date txDate, PersonEntity personByPersonId, AddressEntity addressByAddressId, AddressEntity addressId, PersonEntity personId) {
        this.personAddressId = personAddressId;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
        this.personByPersonId = personByPersonId;
        this.addressByAddressId = addressByAddressId;
//        this.addressId = addressId;
//        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAddressIdEntity that = (PersonAddressIdEntity) o;
        return personAddressId == that.personAddressId &&
                status == that.status &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

//    public void setAddressId(AddressEntity addressId) {
//        this.addressId = addressId;
//    }
//
//    public PersonEntity getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(PersonEntity personId) {
//        this.personId = personId;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(personAddressId, status, txUser, txHost, txDate);
    }


    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odont.odont.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cp_person_address")
@XmlRootElement
public class CpPersonAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_addresson_id")
    private Integer personAddressonId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AddressEntity addressId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonEntity personId;

    public CpPersonAddress() {
    }

    public CpPersonAddress(Integer personAddressonId) {
        this.personAddressonId = personAddressonId;
    }

    public CpPersonAddress(Integer personAddressonId, int status, String txUser, String txHost, Date txDate) {
        this.personAddressonId = personAddressonId;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getPersonAddressonId() {
        return personAddressonId;
    }

    public void setPersonAddressonId(Integer personAddressonId) {
        this.personAddressonId = personAddressonId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public AddressEntity getAddressId() {
        return addressId;
    }

    public void setAddressId(AddressEntity addressId) {
        this.addressId = addressId;
    }

//    public AddressEntity getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(AddressEntity personId) {
//        this.personId = personId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personAddressonId != null ? personAddressonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpPersonAddress)) {
            return false;
        }
        CpPersonAddress other = (CpPersonAddress) object;
        if ((this.personAddressonId == null && other.personAddressonId != null) || (this.personAddressonId != null && !this.personAddressonId.equals(other.personAddressonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.sis.carpool.chatbot.domain.CpPersonAddress[ personAddressonId=" + personAddressonId + " ]";
    }
    
}

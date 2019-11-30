package com.odont.odont.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "db_odont", catalog = "")
public class PersonEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "second_name")
    private String secondName;
    @Size(max = 50)
    @Column(name = "third_name")
    private String thirdName;
    @Size(max = 50)
    @Column(name = "first_surname")
    private String firstSurname;
    @Size(max = 50)
    @Column(name = "second_surname")
    private String secondSurname;
    @Size(max = 50)
    @Column(name = "third_surname")
    private String thirdSurname;
    @Size(max = 100)
    @Column(name = "document_id")
    private String documentId;
    @Size(max = 10)
    @Column(name = "document_type")
    private String documentType;
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
    private java.util.Date txDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private List<CpUserEntity> cpUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private List<CpPersonAddress> cpPersonAddressList;

    public PersonEntity() {
    }

    public PersonEntity(Integer personId) {
        this.personId = personId;
    }

    public PersonEntity(Integer personId, String firstName, int status, String txUser, String txHost, java.util.Date txDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getThirdSurname() {
        return thirdSurname;
    }

    public void setThirdSurname(String thirdSurname) {
        this.thirdSurname = thirdSurname;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public java.util.Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @XmlTransient
    public List<CpUserEntity> getCpUserList() {
        return cpUserList;
    }

    public void setCpUserList(List<CpUserEntity> cpUserList) {
        this.cpUserList = cpUserList;
    }

    @XmlTransient
    public List<CpPersonAddress> getCpPersonAddressList() {
        return cpPersonAddressList;
    }

    public void setCpPersonAddressList(List<CpPersonAddress> cpPersonAddressList) {
        this.cpPersonAddressList = cpPersonAddressList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonEntity)) {
            return false;
        }
        PersonEntity other = (PersonEntity) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.sis.carpool.chatbot.domain.CpPerson[ personId=" + personId + " ]";
    }

}
package com.odont.odont.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cp_user")
@XmlRootElement
public class CpUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bot_user_id")
    private String botUserId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpUserUserId", fetch = FetchType.LAZY)
    private List<CpChatEntity> cpChatList;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonEntity personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<CpUserFileEntity> cpUserFileList;

    public CpUserEntity() {
            }

    public CpUserEntity(Integer userId) {
        this.userId = userId;
    }

    public CpUserEntity(Integer userId, String botUserId, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.botUserId = botUserId;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
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

    public PersonEntity getPersonId() {
        return personId;
    }

    public void setPersonId(PersonEntity personId) {
        this.personId = personId;
    }

    @XmlTransient
    public List<CpUserFileEntity> getCpUserFileList() {
        return cpUserFileList;
    }

    public void setCpUserFileList(List<CpUserFileEntity> cpUserFileList) {
        this.cpUserFileList = cpUserFileList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpUserEntity)) {
            return false;
        }
        CpUserEntity other = (CpUserEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odont.odont.entity.CpUserEntity[ userId=" + userId + " ]";
    }

    @XmlTransient
    public List<CpChatEntity> getCpChatList() {
        return cpChatList;
    }

    public void setCpChatList(List<CpChatEntity> cpChatList) {
        this.cpChatList = cpChatList;
    }

}
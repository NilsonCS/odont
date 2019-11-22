package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_user", schema = "db_odont", catalog = "")
public class CpUserEntity {
    private long userId;
    private int personId;
    private String botUserId;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "person_id", nullable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "bot_user_id", nullable = false, length = 100)
    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
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

    public CpUserEntity() { }

    public CpUserEntity(long userId) {
        this.userId = userId;
    }

    public CpUserEntity(long userId, int personId, String botUserId, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.personId = personId;
        this.botUserId = botUserId;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpUserEntity that = (CpUserEntity) o;
        return userId == that.userId &&
                personId == that.personId &&
                Objects.equals(botUserId, that.botUserId) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public String toString() {
        return "CpUserEntity{" +
                "userId=" + userId +
                ", personId=" + personId +
                ", botUserId='" + botUserId + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                ", txDate=" + txDate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, personId, botUserId, txUser, txHost, txDate);
    }
}

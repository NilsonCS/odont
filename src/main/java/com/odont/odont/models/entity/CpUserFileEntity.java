package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_user_file", schema = "db_odont", catalog = "")
public class CpUserFileEntity {
    private long userFileId;
    private int userId;
    private int cpFileFileId;
    private String fileType;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "user_file_id", nullable = false)
    public long getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(long userFileId) {
        this.userFileId = userFileId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cp_file_file_id", nullable = false)
    public int getCpFileFileId() {
        return cpFileFileId;
    }

    public void setCpFileFileId(int cpFileFileId) {
        this.cpFileFileId = cpFileFileId;
    }

    @Basic
    @Column(name = "file_type", nullable = false, length = 10)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpUserFileEntity that = (CpUserFileEntity) o;
        return userFileId == that.userFileId &&
                userId == that.userId &&
                cpFileFileId == that.cpFileFileId &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFileId, userId, cpFileFileId, fileType, txUser, txHost, txDate);
    }
}

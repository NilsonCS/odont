package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_file", schema = "db_odont", catalog = "")
public class CpFileEntity {
    private long fileId;
    private String fileName;
    private String mimeType;
    private String path;
    private String storeType;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "file_id", nullable = false)
    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "file_name", nullable = false, length = 250)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "mime_type", nullable = true, length = 100)
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Basic
    @Column(name = "path", nullable = false, length = 400)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "store_type", nullable = false, length = 10)
    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
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
        CpFileEntity that = (CpFileEntity) o;
        return fileId == that.fileId &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(mimeType, that.mimeType) &&
                Objects.equals(path, that.path) &&
                Objects.equals(storeType, that.storeType) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, fileName, mimeType, path, storeType, txUser, txHost, txDate);
    }
}

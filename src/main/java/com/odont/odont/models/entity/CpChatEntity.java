package com.odont.odont.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_chat", schema = "db_odont", catalog = "")
public class CpChatEntity {
    private long chatId;
    private int cpUserUserId;
    private String inMessage;
    private String outMessage;
    private Date msgDate;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "chat_id", nullable = false)
    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    @Basic
    @Column(name = "cp_user_user_id", nullable = false)
    public int getCpUserUserId() {
        return cpUserUserId;
    }

    public void setCpUserUserId(int cpUserUserId) {
        this.cpUserUserId = cpUserUserId;
    }

    @Basic
    @Column(name = "in_message", nullable = false, length = 400)
    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    @Basic
    @Column(name = "out_message", nullable = true, length = 400)
    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    @Basic
    @Column(name = "msg_date", nullable = false)
    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
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
        CpChatEntity that = (CpChatEntity) o;
        return chatId == that.chatId &&
                cpUserUserId == that.cpUserUserId &&
                Objects.equals(inMessage, that.inMessage) &&
                Objects.equals(outMessage, that.outMessage) &&
                Objects.equals(msgDate, that.msgDate) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, cpUserUserId, inMessage, outMessage, msgDate, txUser, txHost, txDate);
    }
}

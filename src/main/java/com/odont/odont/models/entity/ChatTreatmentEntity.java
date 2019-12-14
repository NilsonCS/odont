package com.odont.odont.models.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "chat", schema = "db_odont", catalog = "")
public class ChatTreatmentEntity {
    private long chatId;

    @Id
    @javax.persistence.Column(name = "chat_id")
    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    private Long conversation;

    @Basic
    @javax.persistence.Column(name = "conversation")
    public Long getConversation() {
        return conversation;
    }

    public void setConversation(Long conversation) {
        this.conversation = conversation;
    }

    private Long messageId;

    @Basic
    @javax.persistence.Column(name = "message_id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    private Long inMessage;

    @Basic
    @javax.persistence.Column(name = "in_message")
    public Long getInMessage() {
        return inMessage;
    }

    public void setInMessage(Long inMessage) {
        this.inMessage = inMessage;
    }

    private Long outMessage;

    @Basic
    @javax.persistence.Column(name = "out_message")
    public Long getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(Long outMessage) {
        this.outMessage = outMessage;
    }

    private Date messageDate;

    @Basic
    @javax.persistence.Column(name = "message_date")
    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    private String txUser;

    @Basic
    @javax.persistence.Column(name = "tx_user")
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    private String txHost;

    @Basic
    @javax.persistence.Column(name = "tx_host")
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    private Date txDate;

    @Basic
    @javax.persistence.Column(name = "tx_date")
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
        ChatTreatmentEntity that = (ChatTreatmentEntity) o;
        return chatId == that.chatId &&
                Objects.equals(conversation, that.conversation) &&
                Objects.equals(messageId, that.messageId) &&
                Objects.equals(inMessage, that.inMessage) &&
                Objects.equals(outMessage, that.outMessage) &&
                Objects.equals(messageDate, that.messageDate) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, conversation, messageId, inMessage, outMessage, messageDate, txUser, txHost, txDate);
    }
}

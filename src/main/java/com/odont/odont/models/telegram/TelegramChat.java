package com.odont.odont.models.telegram;

import com.odont.odont.models.dao.TelegramUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

// model del chat de telegram posee informacion

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
public class TelegramChat {
    @Id
    Long id;
    LocalDateTime creationDate;
    Boolean userChat;
    Boolean groupChat;
    Boolean channelChat;
    Boolean superGroupChart;
    @ManyToOne
    TelegramUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getUserChat() {
        return userChat;
    }

    public void setUserChat(Boolean userChat) {
        this.userChat = userChat;
    }

    public Boolean getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(Boolean groupChat) {
        this.groupChat = groupChat;
    }

    public Boolean getChannelChat() {
        return channelChat;
    }

    public void setChannelChat(Boolean channelChat) {
        this.channelChat = channelChat;
    }

    public Boolean getSuperGroupChart() {
        return superGroupChart;
    }

    public void setSuperGroupChart(Boolean superGroupChart) {
        this.superGroupChart = superGroupChart;
    }

    public TelegramUser getUser() {
        return user;
    }

    public void setUser(TelegramUser user) {
        this.user = user;
    }

    public TelegramChat(Long id, LocalDateTime creationDate, Boolean userChat, Boolean groupChat, Boolean channelChat, Boolean superGroupChart, TelegramUser user) {
        this.id = id;
        this.creationDate = creationDate;
        this.userChat = userChat;
        this.groupChat = groupChat;
        this.channelChat = channelChat;
        this.superGroupChart = superGroupChart;
        this.user = user;
    }

}

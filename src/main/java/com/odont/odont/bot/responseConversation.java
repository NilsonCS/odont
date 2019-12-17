package com.odont.odont.bot;

import java.util.List;

public class responseConversation {
    String responses;
    int message;
    int conversation;




    public responseConversation() {}

    public responseConversation (String responses, int step) {
        this.responses = responses;
        this.message = step;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int step) {
        this.message = step;
    }




    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }



}

package com.odont.odont.bot;

import java.util.List;

public class responseConversation {
    String responses;
    int message;


    public responseConversation(String responses, int conversation) {
        this.responses = responses;
        this.conversation = conversation;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    int conversation;
    List<String> options;


    public responseConversation() {}

    public responseConversation( int conversation) {
    //public responseConversation(int conversation, List<String> options) {
        this.conversation = conversation;
        this.options = options;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int step) {
        this.message = step;
    }

}

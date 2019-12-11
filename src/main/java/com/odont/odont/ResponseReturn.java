package com.odont.odont;

public class ResponseReturn {
    String responses;
    int conversation;
    int message;

    public ResponseReturn(){

    }

    public ResponseReturn(String responses, int conversation, int message) {
        this.responses = responses;
        this.conversation = conversation;
        this.message = message;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}

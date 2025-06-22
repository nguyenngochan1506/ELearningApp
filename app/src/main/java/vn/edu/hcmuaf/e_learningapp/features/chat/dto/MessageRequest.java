package vn.edu.hcmuaf.e_learningapp.features.chat.dto;

import java.io.Serializable;

public class MessageRequest implements Serializable {
    private Long conversationId;
    private String message;

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

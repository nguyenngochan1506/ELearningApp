package vn.edu.hcmuaf.e_learningapp.features.chat;

import java.io.Serializable;

import vn.edu.hcmuaf.e_learningapp.features.user.dtos.UserResponse;

public class Conversation implements Serializable {
    private Long id;
    private String participantsHash;
    private String conversationAvatar;
    private String conversationName;
    private UserResponse participant;
    private String createdAt;
    private String updatedAt;
    private String lastMessage;

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipantsHash() {
        return participantsHash;
    }

    public void setParticipantsHash(String participantsHash) {
        this.participantsHash = participantsHash;
    }

    public String getConversationAvatar() {
        return conversationAvatar;
    }

    public void setConversationAvatar(String conversationAvatar) {
        this.conversationAvatar = conversationAvatar;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public UserResponse getParticipant() {
        return participant;
    }

    public void setParticipant(UserResponse participant) {
        this.participant = participant;
    }
}

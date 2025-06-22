package vn.edu.hcmuaf.e_learningapp.features.chat;

import lombok.Getter;

@Getter
public class Chat {
    private final String content;
    private final String senderId;
    private final String senderName;
    private final String time;

    public Chat(String content, String senderId, String senderName, String time) {
        this.content = content;
        this.senderId = senderId;
        this.senderName = senderName;
        this.time = time;
    }

}

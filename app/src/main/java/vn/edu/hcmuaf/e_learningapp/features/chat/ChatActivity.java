package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout messageContainer;
    private EditText etMessage;
    private ImageButton btnSend;
    private ImageView btnBack;
    private List<Chat> messages;
    private Long conversationId;
    private String conversationName;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageContainer = findViewById(R.id.message_container);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.iv_back);
         messages = new ArrayList<>();

        // Lấy conversationId từ Intent
        conversationId = getIntent().getLongExtra("conversationId", -1);
        conversationName = getIntent().getStringExtra("conversationName");
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(conversationName);

        // Lấy JWT token từ SharedPreferences
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        accessToken = prefs.getString("accessToken", null);

        // Load tin nhắn từ API
        loadMessages();


        // Gửi tin nhắn
        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                sendMessage(msg);
                etMessage.setText("");
            }
        });
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void loadMessages() {
        ChatRepository.getMessages(this, accessToken, conversationId, new ChatRepository.ChatCallback<List<Chat>>() {
            @Override
            public void onSuccess(List<Chat> data) {
                messages.clear();
                messages.addAll(data);
                messageContainer.removeAllViews();
                Collections.reverse(messages);
                for (Chat chat : messages) {
                    addMessageToLayout(chat);
                }

            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage(String message) {
        ChatRepository.sendMessage(this, accessToken, conversationId, message, new ChatRepository.ChatCallback<Chat>() {
            @Override
            public void onSuccess(Chat data) {
                messages.add(data);
                addMessageToLayout(data);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMessageToLayout(Chat chat) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View messageView;

        if (chat.isMe()) {
            // Tin nhắn của chính mình
            messageView = inflater.inflate(R.layout.item_message_self, messageContainer, false);
        } else {
            // Tin nhắn người khác
            messageView = inflater.inflate(R.layout.item_message_other, messageContainer, false);
        }

        TextView tvMessage = messageView.findViewById(R.id.tv_message);
        TextView tvTimestamp = messageView.findViewById(R.id.tv_timestamp);

        tvMessage.setText(chat.getMessage());
        tvTimestamp.setText(LocalDateTime.parse(chat.getCreatedAt()).format(DateTimeFormatter.ofPattern("HH:mm")));

        if (!chat.isMe()) {
            TextView tvSender = messageView.findViewById(R.id.tv_sender);
            ImageView ivAvatar = messageView.findViewById(R.id.iv_avatar);

            if (tvSender != null) {
                tvSender.setText(chat.getSender() != null && chat.getSender().getFullName() != null
                        ? chat.getSender().getFullName()
                        : "Người gửi không xác định");
            }

            if (ivAvatar != null && chat.getSender() != null && chat.getSender().getavatar() != null) {
                Glide.with(this)
                        .load(chat.getSender().getavatar())
                        .circleCrop()
                        .placeholder(R.drawable.avthehe)
                        .error(R.drawable.avthehe)
                        .into(ivAvatar);
            }
        }

        messageContainer.addView(messageView);
    }
}

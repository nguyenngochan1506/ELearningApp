package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout messageContainer;
    private EditText etMessage;
    private ImageButton btnSend;
    private ImageView btnBack;
    private String currentUserId = "123"; // Giả lập ID người dùng hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageContainer = findViewById(R.id.message_container);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.iv_back);
        List<Chat> messages = new ArrayList<>();
        messages.add(new Chat("Hi, bạn khỏe không?", "user_1", "Nguyễn Văn A", "14:59"));
        messages.add(new Chat("Chào bạn, tôi ổn!", "me", "Tôi", "15:00"));
        messages.add(new Chat("Vậy thì tốt quá!", "user_1", "Nguyễn Văn A", "15:01"));
        messages.add(new Chat("Cảm ơn nhé!", "me", "Tôi", "15:02"));

        // Hiển thị tin nhắn demo
        for (Chat chat : messages) {
            addMessageToLayout(chat);
        }

        // Gửi tin nhắn
        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                Chat newMessage = new Chat(msg, currentUserId, "Bạn", time);
                addMessageToLayout(newMessage);
                etMessage.setText("");
            }
        });
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void addMessageToLayout(Chat chat) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View messageView;

        if (chat.getSenderId().equals(currentUserId)) {
            // Tin nhắn của chính mình
            messageView = inflater.inflate(R.layout.item_message_self, messageContainer, false);
        } else {
            // Tin nhắn người khác
            messageView = inflater.inflate(R.layout.item_message_other, messageContainer, false);
        }

        TextView tvMessage = messageView.findViewById(R.id.tv_message);
        TextView tvTimestamp = messageView.findViewById(R.id.tv_timestamp);

        tvMessage.setText(chat.getContent());
        tvTimestamp.setText(chat.getTime());

        messageContainer.addView(messageView);
    }
}

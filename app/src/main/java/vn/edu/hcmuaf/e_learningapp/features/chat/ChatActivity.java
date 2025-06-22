package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvMessages;
    private EditText etMessage;
    private ImageButton btnSend;
    private ChatAdapter adapter;
    private List<Chat> messageList;
    private String currentUserId = "123"; // giả lập người dùng hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvMessages = findViewById(R.id.rv_messages);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);

        messageList = new ArrayList<>();

        // Dữ liệu demo
        List<Chat> messages = new ArrayList<>();

// Người khác
        messages.add(new Chat("Hi, bạn khỏe không?", "user_1", "Nguyễn Văn A", "14:59"));
// Bản thân
        messages.add(new Chat("Chào bạn, tôi ổn!", "me", "Tôi", "15:00"));
// Người khác
        messages.add(new Chat("Vậy thì tốt quá!", "user_1", "Nguyễn Văn A", "15:01"));
// Bản thân
        messages.add(new Chat("Cảm ơn nhé!", "me", "Tôi", "15:02"));

        adapter = new ChatAdapter(this, messageList, "me");
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                messageList.add(new Chat(msg, currentUserId, "Bạn", time));
                adapter.notifyItemInserted(messageList.size() - 1);
                rvMessages.scrollToPosition(messageList.size() - 1);
                etMessage.setText("");
            }
        });
    }
}


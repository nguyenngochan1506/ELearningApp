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
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.chat.dto.MessageRequest;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout messageContainer;
    private EditText etMessage;
    private ImageButton btnSend;
    private ImageView btnBack;
    private List<Chat> messages;
    private Long conversationId;
    private String conversationName;
    private String accessToken;
    private OkHttpClient client;
    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageContainer = findViewById(R.id.message_container);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.iv_back);
        messages = new ArrayList<>();

        // Lấy conversationId và tên cuộc trò chuyện
        conversationId = getIntent().getLongExtra("conversationId", -1);
        conversationName = getIntent().getStringExtra("conversationName");
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(conversationName);

        // Lấy JWT token từ SharedPreferences
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        accessToken = prefs.getString("accessToken", null);

        // Khởi tạo OkHttpClient
        client = new OkHttpClient();

        // Kết nối WebSocket
        connectWebSocket();

        // Load tin nhắn ban đầu từ API
        loadMessages();

        // Gửi tin nhắn
        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                sendMessage(msg);
                etMessage.setText("");
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void connectWebSocket() {
        // Tạo request với header Authorization
        Request request = new Request.Builder()
                .url("ws://192.168.1.96:8080/ws") // Thay bằng URL WebSocket của bạn
                .header("Authorization", "Bearer " + accessToken)
                .build();

        // Tạo WebSocket listener
        WebSocketListener listener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "WebSocket Connected", Toast.LENGTH_SHORT).show());
                // Đăng ký topic để nhận tin nhắn
                String subscribeMessage = "{\"command\":\"SUBSCRIBE\",\"destination\":\"/topic/conversation/" + conversationId + "\",\"id\":\"sub-0\"}";
                webSocket.send(subscribeMessage);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                runOnUiThread(() -> {
                    try {
                        // Parse JSON từ server
                        Gson gson = new Gson();
                        // Giả sử server trả về JSON chứa tin nhắn trong trường "body"
                        Chat chat = gson.fromJson(text, Chat.class);
                        messages.add(chat);
                        addMessageToLayout(chat);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ChatActivity.this, "Error parsing message", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                // Không xử lý dữ liệu nhị phân trong trường hợp này
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                webSocket.close(1000, null);
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "WebSocket Closing: " + reason, Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "WebSocket Error: " + t.getMessage(), Toast.LENGTH_SHORT).show());
            }
        };

        // Kết nối WebSocket
        webSocket = client.newWebSocket(request, listener);
    }

    private void sendMessage(String message) {
        // Tạo request gửi tin nhắn
        MessageRequest request = new MessageRequest();
        request.setConversationId(conversationId);
        request.setMessage(message);

        // Chuyển request thành JSON
        String jsonMessage = new Gson().toJson(request);

        // Gửi tin nhắn qua WebSocket
        String sendMessage = "{\"command\":\"SEND\",\"destination\":\"/app/chat.sendMessage\",\"body\":" + jsonMessage + "}";
        if (webSocket != null) {
            webSocket.send(sendMessage);
        }

        // Gọi API REST để lưu tin nhắn vào cơ sở dữ liệu
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

    private void addMessageToLayout(Chat chat) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View messageView;

        if (chat.isMe()) {
            messageView = inflater.inflate(R.layout.item_message_self, messageContainer, false);
        } else {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.close(1000, "Activity destroyed");
        }
        if (client != null) {
            client.dispatcher().executorService().shutdown();
        }
    }
}
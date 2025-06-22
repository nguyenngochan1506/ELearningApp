package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatList extends Fragment {
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private List<Conversation> conversations;
    private String accessToken;

    public ChatList() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_chats, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        conversations = new ArrayList<>();

        adapter = new ConversationAdapter(getContext(), conversations, conversation -> {
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("conversationId", conversation.getId());
            intent.putExtra("conversationName", conversation.getConversationName());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        //lay jwt tu sharedrefre
        SharedPreferences prefs = getContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("accessToken", null);

        // Load danh sách cuộc trò chuyện
        loadConversations();
        return view;
    }

    private void loadConversations() {
        ChatRepository.getConversations(getContext(), accessToken, new ChatRepository.ChatCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> data) {
                conversations.clear();
                conversations.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                android.widget.Toast.makeText(getContext(), errorMessage, android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}


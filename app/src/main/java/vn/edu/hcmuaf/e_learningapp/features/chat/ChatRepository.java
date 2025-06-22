package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.chat.dto.MessageRequest;

public class ChatRepository {
    public interface ChatCallback<T> {
        void onSuccess(T data);
        void onError(String errorMessage);
    }
    public static void getConversations(Context context, String jwtToken, ChatCallback<List<Conversation>> callback) {
        ChatApi api = ApiClient.getClient(jwtToken).create(ChatApi.class);
        retrofit2.Call<ResponseDto<List<Conversation>>> call = api.getMyConversations();
        call.enqueue(new Callback<ResponseDto<List<Conversation>>>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseDto<List<Conversation>>> call, Response<ResponseDto<List<Conversation>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError("Không thể lấy danh sách cuộc trò chuyện");
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<List<Conversation>>> call, Throwable t) {
                callback.onError(t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void getMessages(Context context, String jwtToken, Long conversationId, ChatCallback<List<Chat>> callback) {
        ChatApi api = ApiClient.getClient(jwtToken).create(ChatApi.class);
        Call<ResponseDto<List<Chat>>> call = api.getMessages(conversationId);
        call.enqueue(new Callback<ResponseDto<List<Chat>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<Chat>>> call, Response<ResponseDto<List<Chat>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError("Không thể lấy tin nhắn");
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<List<Chat>>> call, Throwable t) {
                callback.onError(t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void sendMessage(Context context, String jwtToken, Long conversationId, String message, ChatCallback<Chat> callback) {
        ChatApi api = ApiClient.getClient(jwtToken).create(ChatApi.class);
        MessageRequest request = new MessageRequest();
        request.setMessage(message);
        request.setConversationId(conversationId);

        Call<ResponseDto<Chat>> call = api.sendMessage(request);
        call.enqueue(new Callback<ResponseDto<Chat>>() {
            @Override
            public void onResponse(Call<ResponseDto<Chat>> call, Response<ResponseDto<Chat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError("Không thể gửi tin nhắn");
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<Chat>> call, Throwable t) {
                callback.onError(t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

package vn.edu.hcmuaf.e_learningapp.features.chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.chat.dto.ConversationRequest;
import vn.edu.hcmuaf.e_learningapp.features.chat.dto.MessageRequest;

public interface ChatApi {
    @POST("v1/conversations")
    Call<ResponseDto<Conversation>> createConversation(@Body ConversationRequest request);

    @GET("v1/conversations/my")
    Call<ResponseDto<List<Conversation>>> getMyConversations();

    @POST("v1/chat")
    Call<ResponseDto<Chat>> sendMessage(@Body MessageRequest request);

    @GET("v1/chat")
    Call<ResponseDto<List<Chat>>> getMessages(@Query("conversationId") Long conversationId);
}

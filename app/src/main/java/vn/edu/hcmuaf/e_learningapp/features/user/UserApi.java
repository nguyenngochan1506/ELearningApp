package vn.edu.hcmuaf.e_learningapp.features.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.AuthResponse;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.LoginRequest;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.UserResponse;

public interface UserApi {
    @POST("auth/login")
    Call<ResponseDto<AuthResponse>> login(@Body LoginRequest request);
    @GET("auth/me")
    Call<ResponseDto<UserResponse>> getProfile();
}

package vn.edu.hcmuaf.e_learningapp.features.courses;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CoursePageResponseDto;

public class CourseRepository {
    public interface CourseCallback {
        void onSuccess(CoursePageResponseDto data);
        void onError(String errorMessage);
    }

    public static void getCourses(Context context, CourseCallback callback) {
        CoursePageResponseDto courses = null;
        //lấy jwt từ sharedpreference
        SharedPreferences prefs = context.getSharedPreferences("app_prefs", MODE_PRIVATE);
        String accessToken = prefs.getString("accessToken", null);

        CourseApi api = ApiClient.getClient(accessToken).create(CourseApi.class);
        Call<ResponseDto<CoursePageResponseDto>> call = api.getAllCourses();
        call.enqueue(new Callback<ResponseDto<CoursePageResponseDto>>() {
            @Override
            public void onResponse(Call<ResponseDto<CoursePageResponseDto>> call, Response<ResponseDto<CoursePageResponseDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError("Lỗi khi lấy danh sách khóa học: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<CoursePageResponseDto>> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
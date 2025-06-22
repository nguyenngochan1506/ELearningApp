package vn.edu.hcmuaf.e_learningapp.features.courses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CoursePageResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CourseResponseDto;

public interface CourseApi {
    @GET("v1/courses")
    Call<ResponseDto<CoursePageResponseDto>> getAllCourses();
    @GET("v1/courses/{id}")
    Call<ResponseDto<CourseResponseDto>> getCourseById(@Path("id") Long id);
}

package vn.edu.hcmuaf.e_learningapp.features.courses.dto;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.common.PageResponseDto;

public class CoursePageResponseDto extends PageResponseDto {
    private List<CourseResponseDto> courses;

    public CoursePageResponseDto(List<CourseResponseDto> courses) {
        this.courses = courses;
    }

    public List<CourseResponseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponseDto> courses) {
        this.courses = courses;
    }
}

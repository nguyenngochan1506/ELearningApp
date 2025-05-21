package vn.edu.hcmuaf.e_learningapp.features.courses;

import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.R;

public class CourseRepository {

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Lập trình nâng cao - DH22DTB", "Nguyễn Đức Công Song", 50, R.drawable.course_image1, 4.9f, "Miễn phí"));
        courses.add(new Course("Celebrating Cultures", "Giảng Viên A", 70, R.drawable.course_image2, 4.7f, "500,000 VNĐ"));
        return courses;
    }
}
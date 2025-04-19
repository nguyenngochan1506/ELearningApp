package vn.edu.hcmuaf.e_learningapp.features.courses;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    public static List<Course> getCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "Lập trình Java cơ bản", "Nguyễn Văn A", 4.5f, 1200, "https://i.imgur.com/3G3K5Yy.jpg"));
        courseList.add(new Course(2, "Thiết kế Web với HTML/CSS", "Trần Thị B", 4.7f, 950, "https://i.imgur.com/dM7Thhn.jpg"));
        courseList.add(new Course(3, "ReactJS từ cơ bản đến nâng cao", "Lê Văn C", 4.8f, 2100, "https://i.imgur.com/OtP2fGX.jpg"));
        courseList.add(new Course(4, "Lập trình Android với Java", "Phạm Minh D", 4.6f, 1500, "https://i.imgur.com/H5Zjs9N.jpg"));
        return courseList;
    }
}

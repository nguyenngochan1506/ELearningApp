package vn.edu.hcmuaf.e_learningapp.features.courses;

import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;
import vn.edu.hcmuaf.e_learningapp.features.module.Module;

public class CourseRepository {

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
//        courses.add(new Course("Lập trình nâng cao - DH22DTB", "Nguyễn Đức Công Song", 50, R.drawable.course_image1, 4.9f, "Miễn phí"));
//        courses.add(new Course("Celebrating Cultures", "Giảng Viên A", 70, R.drawable.course_image2, 4.7f, "500,000 VNĐ"));

        Course c1 = new Course(1, "Lập trình nâng cao - DH22DTB", "Nguyễn Đức Công Song", "Lập trình Android cơ bản", 50, R.drawable.course_image1, 4.9f, "Miễn phí");
        Course c2 = new Course(2, "Toán Cao Cấp", "Giảng Viên A", "Toán cao cấp", 70, R.drawable.course_image2, 4.7f, "500,000 VNĐ");

        Module c1_m1  = new Module();
        c1_m1.setId(1L);
        c1_m1.setName("Chương 1");
        c1_m1.setDescription("Mô tả chương 1");
        c1_m1.setNumber(1);
        c1_m1.setLessons(List.of(new Lesson(1, "Bài 1", "Nội dung bài 1", 1, 10, "https://www.youtube.com/watch?v=dQw4w9WgXcQ"), new Lesson(2, "Bài 2", "Nội dung bài 2", 2, 10, "https://www.youtube.com/watch?v=dQw4w9WgXcQ")));

        Module c1_m2  = new Module();
        c1_m2.setId(2L);
        c1_m2.setName("Chương 2");
        c1_m2.setDescription("Mô tả chương 2");
        c1_m2.setNumber(2);
        c1_m2.setLessons(List.of(new Lesson(1, "Bài 1", "Nội dung bài 1", 1, 10, "https://www.youtube.com/watch?v=dQw4w9WgXcQ"), new Lesson(2, "Bài 2", "Nội dung bài 2", 2, 10, "https://www.youtube.com/watch?v=dQw4w9WgXcQ")));

        c1.setModules(List.of(c1_m1, c1_m2));


        courses.add(c1);
        courses.add(c2);

        return courses;
    }
}
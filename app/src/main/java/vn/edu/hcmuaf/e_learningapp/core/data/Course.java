package vn.edu.hcmuaf.e_learningapp.core.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private String title;
    private String instructor;
    private String rating;
    private int studentCount;
    private int imageResId;

    public Course(String title, String instructor, String rating, int studentCount, int imageResId) {
        this.title = title;
        this.instructor = instructor;
        this.rating = rating;
        this.studentCount = studentCount;
        this.imageResId = imageResId;
    }
}
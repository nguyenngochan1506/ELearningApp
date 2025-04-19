package vn.edu.hcmuaf.e_learningapp.features.courses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private int id;
    private String title;
    private String instructor;
    private float rating;
    private int numStudents;
    private String imageUrl;

    public Course(int id, String title, String instructor, float rating, int numStudents, String imageUrl) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.rating = rating;
        this.numStudents = numStudents;
        this.imageUrl = imageUrl;
    }


}

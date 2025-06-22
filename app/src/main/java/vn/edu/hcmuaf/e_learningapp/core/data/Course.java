package vn.edu.hcmuaf.e_learningapp.core.data;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
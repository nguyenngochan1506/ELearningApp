package vn.edu.hcmuaf.e_learningapp.features.courses;

public class Course {
    private String title;
    private String instructor;
    private int progress;
    private int imageResId;

    public Course(String title, String instructor, int progress, int imageResId) {
        this.title = title;
        this.instructor = instructor;
        this.progress = progress;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public int getProgress() { return progress; }
    public int getImageResId() { return imageResId; }
}

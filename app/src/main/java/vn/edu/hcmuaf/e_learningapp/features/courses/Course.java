package vn.edu.hcmuaf.e_learningapp.features.courses;

public class Course {
    private String title;
    private String instructor;
    private int progress;
    private int imageResId;
    private float rating;
    private String price;

    public Course(String title, String instructor, int progress, int imageResId, float rating, String price) {
        this.title = title;
        this.instructor = instructor;
        this.progress = progress;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
    }

    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public int getProgress() { return progress; }
    public int getImageResId() { return imageResId; }
    public float getRating() { return rating; }
    public String getPrice() { return price; }
}
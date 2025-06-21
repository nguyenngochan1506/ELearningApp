package vn.edu.hcmuaf.e_learningapp.features.courses;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.module.Module;

public class Course {
    private int id;
    private String title;
    private String instructor;
    private String description;
    private int progress;
    private int imageResId;
    private float rating;
    private String price;
    private List<Module> modules;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor='" + instructor + '\'' +
                ", description='" + description + '\'' +
                ", progress=" + progress +
                ", imageResId=" + imageResId +
                ", rating=" + rating +
                ", price='" + price + '\'' +
                ", modules=" + modules +
                '}';
    }

    public Course(String title, String instructor, int progress, int imageResId, float rating, String price) {
        this.title = title;
        this.instructor = instructor;
        this.progress = progress;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
    }

    public Course(int id, String title, String instructor, String description, int progress, int imageResId, float rating, String price) {
        this.title = title;
        this.instructor = instructor;
        this.description = description;
        this.progress = progress;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
        this.id = id;
    }

    public Course(int id, String title, String instructor, String description, int progress, int imageResId, float rating, String price, List<Module> modules) {
        this.title = title;
        this.instructor = instructor;
        this.description = description;
        this.progress = progress;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
        this.modules = modules;
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
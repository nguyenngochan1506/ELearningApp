package vn.edu.hcmuaf.e_learningapp.features.instructors;

public class Instructor {
    private String name;
    private int studentCount;
    private int courseCount;
    private int avatarResId;

    public Instructor(String name, int studentCount, int courseCount, int avatarResId) {
        this.name = name;
        this.studentCount = studentCount;
        this.courseCount = courseCount;
        this.avatarResId = avatarResId;
    }

    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}
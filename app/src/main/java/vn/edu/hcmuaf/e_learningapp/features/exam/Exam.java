package vn.edu.hcmuaf.e_learningapp.features.exam;

public class Exam {
    private String name;
    private String className;
    private String date;
    private String status;

    public Exam(String name, String className, String date, String status) {
        this.name = name;
        this.className = className;
        this.date = date;
        this.status = status;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getClassName() { return className; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}
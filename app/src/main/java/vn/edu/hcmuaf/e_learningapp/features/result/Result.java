package vn.edu.hcmuaf.e_learningapp.features.result;

public class Result {
    private String studentName;
    private String score;
    private String date;

    public Result(String studentName, String score, String date) {
        this.studentName = studentName;
        this.score = score;
        this.date = date;
    }

    // Getters
    public String getStudentName() { return studentName; }
    public String getScore() { return score; }
    public String getDate() { return date; }
}

package vn.edu.hcmuaf.e_learningapp.features.submission;

public class Submission {
    private String studentName;
    private String submissionTime;
    private String status;

    public Submission(String studentName, String submissionTime, String status) {
        this.studentName = studentName;
        this.submissionTime = submissionTime;
        this.status = status;
    }

    // Getters
    public String getStudentName() { return studentName; }
    public String getSubmissionTime() { return submissionTime; }
    public String getStatus() { return status; }
}
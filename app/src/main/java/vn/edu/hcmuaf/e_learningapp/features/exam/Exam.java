package vn.edu.hcmuaf.e_learningapp.features.exam;

public class Exam {
    public String title;
    public String course;
    public String duration;
    public String startTime;
    public String endTime;
    public int participants;
    public String remainingTime;
    public String status; // "đang diễn ra" or "sắp diễn ra"
    public String id;

    public Exam(String id, String title, String course, String duration, String startTime,
                String endTime, int participants, String remainingTime, String status) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.remainingTime = remainingTime;
        this.status = status;
    }
}


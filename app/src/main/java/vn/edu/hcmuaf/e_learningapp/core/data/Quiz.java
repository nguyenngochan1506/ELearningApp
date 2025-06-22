package vn.edu.hcmuaf.e_learningapp.core.data;

public class Quiz {
    public int id;
    public String type; // "multiple-choice" or "short-answer"
    public String question;
    public String[] options;
    public int correctAnswer;

    public Quiz(int id, String type, String question, String[] options, int correctAnswer) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

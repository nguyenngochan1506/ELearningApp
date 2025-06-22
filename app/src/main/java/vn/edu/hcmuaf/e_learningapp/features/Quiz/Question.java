package vn.edu.hcmuaf.e_learningapp.features.Quiz;

public class Question {
    private String text;
    private QuestionType type;
    private String[] options;
    private boolean fileUploadAllowed;
    private String correctAnswer; // Optional for scoring

    public enum QuestionType {
        MULTIPLE_CHOICE,
        SHORT_ANSWER
    }

    public Question(String text, QuestionType type, String[] options, String correctAnswer, boolean fileUploadAllowed) {
        this.text = text;
        this.type = type;
        this.options = options != null ? options : new String[0];
        this.correctAnswer = correctAnswer;
        this.fileUploadAllowed = fileUploadAllowed;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isFileUploadAllowed() {
        return fileUploadAllowed;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
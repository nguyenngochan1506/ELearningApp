package vn.edu.hcmuaf.e_learningapp.features.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.Quiz.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView timerText, questionIndicator, questionText;
    private RadioGroup optionsGroup;
    private EditText shortAnswerInput;
    private Button uploadFileButton, previousButton, nextButton;
    private LinearLayout quizLayout;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 300000; // 5 minutes default
    private String[] studentAnswers;
    private String quizId; // To identify the quiz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        quizLayout = findViewById(R.id.quizLayout);
        timerText = findViewById(R.id.timerText);
        questionIndicator = findViewById(R.id.questionIndicator);
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        shortAnswerInput = findViewById(R.id.shortAnswerInput);
        uploadFileButton = findViewById(R.id.uploadFileButton);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        // Get quiz ID from intent
        quizId = getIntent().getStringExtra("QUIZ_ID");

        // Initialize data
        initializeQuestions();
        studentAnswers = new String[questions.size()];

        // Start timer
        startTimer();

        // Load first question
        displayQuestion(currentQuestionIndex);

        // Set button listeners
        previousButton.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                saveAnswer();
                currentQuestionIndex--;
                displayQuestion(currentQuestionIndex);
            }
        });

        nextButton.setOnClickListener(v -> {
            saveAnswer();
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                displayQuestion(currentQuestionIndex);
            } else {
                submitQuiz();
            }
        });

        uploadFileButton.setOnClickListener(v -> {
            // Placeholder for file upload
            Toast.makeText(this, "File upload not implemented yet", Toast.LENGTH_SHORT).show();
            // Implement file picker logic here if needed
        });
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        // Sample questions (replace with real data from server or database)
        questions.add(new Question("What is the capital of France?", Question.QuestionType.MULTIPLE_CHOICE,
                new String[]{"Paris", "London", "Berlin", "Madrid"}, null, false));
        questions.add(new Question("Explain the concept of OOP.", Question.QuestionType.SHORT_ANSWER,
                null, null, true));
        questions.add(new Question("Which is a programming language?", Question.QuestionType.MULTIPLE_CHOICE,
                new String[]{"HTML", "CSS", "Java", "XML"}, null, false));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00");
                submitQuiz();
            }
        }.start();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timeFormatted);
    }

    private void displayQuestion(int index) {
        Question question = questions.get(index);
        questionIndicator.setText(String.format("Question %d/%d", index + 1, questions.size()));
        questionText.setText(question.getText());

        // Reset views
        optionsGroup.removeAllViews();
        shortAnswerInput.setVisibility(View.GONE);
        uploadFileButton.setVisibility(View.GONE);

        if (question.getType() == Question.QuestionType.MULTIPLE_CHOICE) {
            optionsGroup.setVisibility(View.VISIBLE);
            for (String option : question.getOptions()) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                radioButton.setTextSize(14);
                radioButton.setPadding(8, 8, 8, 8);
                optionsGroup.addView(radioButton);
            }
            // Restore previous answer
            if (studentAnswers[index] != null) {
                for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) optionsGroup.getChildAt(i);
                    if (rb.getText().toString().equals(studentAnswers[index])) {
                        rb.setChecked(true);
                    }
                }
            }
        } else if (question.getType() == Question.QuestionType.SHORT_ANSWER) {
            shortAnswerInput.setVisibility(View.VISIBLE);
            if (question.isFileUploadAllowed()) {
                uploadFileButton.setVisibility(View.VISIBLE);
            }
            if (studentAnswers[index] != null) {
                shortAnswerInput.setText(studentAnswers[index]);
            }
        }

        // Update navigation buttons
        previousButton.setEnabled(currentQuestionIndex > 0);
        nextButton.setText(currentQuestionIndex == questions.size() - 1 ? "Submit" : "Next →");
    }

    private void saveAnswer() {
        Question question = questions.get(currentQuestionIndex);
        if (question.getType() == Question.QuestionType.MULTIPLE_CHOICE) {
            int checkedId = optionsGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selected = findViewById(checkedId);
                studentAnswers[currentQuestionIndex] = selected.getText().toString();
            }
        } else if (question.getType() == Question.QuestionType.SHORT_ANSWER) {
            studentAnswers[currentQuestionIndex] = shortAnswerInput.getText().toString();
        }
    }

    private void submitQuiz() {
        saveAnswer();
        countDownTimer.cancel();
        // Placeholder for submission logic
        Intent resultIntent = new Intent();
        resultIntent.putExtra("QUIZ_ID", quizId);
        resultIntent.putExtra("ANSWERS", studentAnswers);
        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Quiz submitted!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
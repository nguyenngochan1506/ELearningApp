package vn.edu.hcmuaf.e_learningapp.features.Quiz;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.core.data.Quiz;

public class QuizActivity extends AppCompatActivity {

    private List<Quiz> questionList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private TextView questionText, questionIndicator, timerText;
    private RadioGroup optionsGroup;
    private EditText shortAnswerInput;
    private Button previousButton, nextButton, uploadFileButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 5 * 60 * 1000; // 5 phút
    private final String[] LETTERS = {"A", "B", "C", "D", "E", "F"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String examId = getIntent().getStringExtra("exam_id");
        Toast.makeText(this, "Làm bài kiểm tra: " + examId, Toast.LENGTH_SHORT).show();

        questionText = findViewById(R.id.questionText);
        questionIndicator = findViewById(R.id.questionIndicator);
        timerText = findViewById(R.id.timerText);
        optionsGroup = findViewById(R.id.optionsGroup);
        shortAnswerInput = findViewById(R.id.shortAnswerInput);
        uploadFileButton = findViewById(R.id.uploadFileButton);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        initSampleQuestions();
        displayQuestion();
        startTimer();

        previousButton.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                displayQuestion();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (currentQuestionIndex < questionList.size() - 1) {
                currentQuestionIndex++;
                displayQuestion();
            } else {
                Toast.makeText(this, "Đã hoàn thành bài kiểm tra!", Toast.LENGTH_LONG).show();
                countDownTimer.cancel();
            }
        });

        uploadFileButton.setOnClickListener(v -> {
            Toast.makeText(this, "Đã chọn file ", Toast.LENGTH_SHORT).show();
        });
    }

    private void initSampleQuestions() {
        questionList.add(new Quiz(1, "multiple-choice", "Mua bao nhiêu căn nhà",
                new String[]{"Đếm được", "Đếm được", "Không đếm được", "Không đếm được"}, 0));
        questionList.add(new Quiz(2, "multiple-choice", "Năm bao nhiêu show",
                new String[]{"Đếm được", "Đếm được", "Không đếm được", "Không đếm được"}, 1));
        questionList.add(new Quiz(3, "short-answer", "Bao nhiêu là hit",
                null, -1));
        questionList.add(new Quiz(4, "short-answer", "Có bao nhiêu fan",
                null, -1));
    }

    private void displayQuestion() {
        Quiz question = questionList.get(currentQuestionIndex);
        questionIndicator.setText(String.format(Locale.getDefault(), "Câu %d/%d", currentQuestionIndex + 1, questionList.size()));
        questionText.setText(question.question);

        if ("multiple-choice".equals(question.type)) {
            optionsGroup.setVisibility(View.VISIBLE);
            shortAnswerInput.setVisibility(View.GONE);
            uploadFileButton.setVisibility(View.GONE);
            optionsGroup.removeAllViews();

            for (int i = 0; i < question.options.length; i++) {
                RadioButton option = new RadioButton(this);
                String label = (i < LETTERS.length ? LETTERS[i] : "") + ". " + question.options[i];
                option.setText(label);
                option.setId(i);
                optionsGroup.addView(option);
            }
        } else {
            optionsGroup.setVisibility(View.GONE);
            shortAnswerInput.setVisibility(View.VISIBLE);
            uploadFileButton.setVisibility(View.VISIBLE);
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timerText.setText(time);
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00");
                Toast.makeText(QuizActivity.this, "Hết thời gian làm bài!", Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}
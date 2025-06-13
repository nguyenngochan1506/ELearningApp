package vn.edu.hcmuaf.e_learningapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExamTakingActivity extends AppCompatActivity {

    private TextView tvExamTitle, tvTimer, tvQuestionNumber, tvQuestionContent, tvWordCount;
    private Button btnSubmit, btnPrevious, btnNext, btnFlag;
    private LinearLayout questionsContainer;
    private ProgressBar progressBar;
    private ImageView ivQuestionMedia;
    private RadioGroup rgAnswers;
    private EditText etEssayAnswer, etFillInTheBlank;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 20; // Ví dụ
    private long timeRemainingInSeconds = 15 * 60; // 15 phút

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_taking);

        // Khởi tạo các view
        tvExamTitle = findViewById(R.id.tv_exam_title);
        tvTimer = findViewById(R.id.tv_timer);
        tvQuestionNumber = findViewById(R.id.tv_question_number);
        tvQuestionContent = findViewById(R.id.tv_question_content);
        tvWordCount = findViewById(R.id.tv_word_count);
        btnSubmit = findViewById(R.id.btn_submit);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        btnFlag = findViewById(R.id.btn_flag);
        questionsContainer = findViewById(R.id.questions_container);
        progressBar = findViewById(R.id.progress_bar);
        ivQuestionMedia = findViewById(R.id.iv_question_media);
        rgAnswers = findViewById(R.id.rg_answers);
        etEssayAnswer = findViewById(R.id.et_essay_answer);
        etFillInTheBlank = findViewById(R.id.et_fill_in_the_blank);

        // Thiết lập danh sách câu hỏi
        setupQuestionTabs();

        // Bắt đầu đồng hồ đếm ngược
        startTimer();

        // Xử lý sự kiện nút nộp bài
        btnSubmit.setOnClickListener(v -> showSubmitConfirmationDialog());

        // Xử lý nút điều hướng
        btnPrevious.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                updateQuestionContent();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentQuestionIndex < totalQuestions - 1) {
                currentQuestionIndex++;
                updateQuestionContent();
            }
        });

        btnFlag.setOnClickListener(v -> flagQuestion(currentQuestionIndex));

        // Đếm số từ cho câu tự luận
        etEssayAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int wordCount = s.toString().trim().split("\\s+").length;
                tvWordCount.setText(wordCount + "/500 từ");
                tvWordCount.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setupQuestionTabs() {
        for (int i = 1; i <= totalQuestions; i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dpToPx(50), dpToPx(50));
            params.setMargins(dpToPx(4), 0, dpToPx(4), 0);
            button.setLayoutParams(params);
            //button.setBackgroundResource(getQuestionBackground(i));
            final int index = i - 1;
            button.setOnClickListener(v -> switchToQuestion(index));
            questionsContainer.addView(button);
        }
    }

    private void startTimer() {
        new CountDownTimer(timeRemainingInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemainingInSeconds = millisUntilFinished / 1000;
                tvTimer.setText(String.format("%02d:%02d:%02d",
                        timeRemainingInSeconds / 3600,
                        (timeRemainingInSeconds % 3600) / 60,
                        timeRemainingInSeconds % 60));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00:00");
                showSubmitConfirmationDialog();
            }
        }.start();
    }

    private void showSubmitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận nộp bài");
        builder.setMessage("Bạn có chắc chắn muốn nộp bài không?");
        builder.setPositiveButton("Nộp", (dialog, which) -> submitExam());
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void submitExam() {
        // xử lý nộp bài, có thể gửi dữ liệu lên server
        finish();
    }

    private void switchToQuestion(int index) {
        currentQuestionIndex = index;
        updateQuestionContent();
    }

    private void updateQuestionContent() {
        // Cập nhật nội dung câu hỏi
        //tvQuestionContent.setTextSize(String.format("Câu hỏi: %d/%d/%d", currentQuestionContent + 1, totalQuestions));
        //tvQuestionContent.setText("Câu hỏi: Ví dụ câu hỏi " + String.format(currentQuestionIndex + 1)); // Thay bằng dữ liệu thực tế
        // Ẩn/hiện các view tùy theo loại câu hỏi
        ivQuestionMedia.setVisibility(View.GONE);
        rgAnswers.setVisibility(View.GONE);
        etEssayAnswer.setVisibility(View.GONE);
        etFillInTheBlank.setVisibility(View.GONE);
        tvWordCount.setVisibility(View.GONE);

        // Giả lập hiển thị câu hỏi (thay bằng logic thực tế của be)
        if (currentQuestionIndex % 3 == 0) { // Trắc nghiệm
            rgAnswers.setVisibility(View.VISIBLE);
            // Thêm RadioButton động
        } else if (currentQuestionIndex % 3 == 1) { // Tự luận
            etEssayAnswer.setVisibility(View.VISIBLE);
            tvWordCount.setVisibility(View.VISIBLE);
        } else { // Điền khuyết
            etFillInTheBlank.setVisibility(View.VISIBLE);
        }
        updateProgressBar();
    }

    private void updateProgressBar() {
        // Xử lý cập nhật thanh tiến độ
    }

    private void flagQuestion(int index) {
        // Xử lý đánh dấu câu hỏi để xem lại
        // Cập nhật trạng thái và giao diện
    }

    private boolean isAnswered(int index) {
        // Xử lý kiểm tra câu hỏi đã trả lời
        return false;
    }

    private boolean isInProgress(int index) {
        // Xử lý kiểm tra câu hỏi đang làm dở
        return false;
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
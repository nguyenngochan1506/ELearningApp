package vn.edu.hcmuaf.e_learningapp.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvCourseTitle, tvInstructor, tvPrice;
    private RatingBar ratingBar;
    private ProgressBar progressBar;
    private CheckBox checkBoxDone;
    private EditText editTextQuestion;
    private ImageView imgCover;
    private TabLayout tabLayout;
    private Button btnEnroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);

        initViews();
        loadCourseData();
        setupTabs();
    }

    private void initViews() {
        tvCourseTitle = findViewById(R.id.tvCourseTitle);
        tvInstructor = findViewById(R.id.tvInstructor);
        tvPrice = findViewById(R.id.tvPrice);
        ratingBar = findViewById(R.id.ratingBar);
        progressBar = findViewById(R.id.progressBar);
        checkBoxDone = findViewById(R.id.checkBox);
        editTextQuestion = findViewById(R.id.editTextQuestion);
        imgCover = findViewById(R.id.imgCover);
        tabLayout = findViewById(R.id.tabLayout);
        btnEnroll = findViewById(R.id.btnEnroll);

        // Handle checkbox for lesson completion
        checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Bạn đã hoàn thành bài học!", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle enroll button
        btnEnroll.setOnClickListener(v -> {
            Toast.makeText(this, "Đã đăng ký khóa học!", Toast.LENGTH_SHORT).show();
            // TODO: Implement enrollment logic (e.g., API call)
        });
    }

    private void loadCourseData() {
        // Get course title from Intent
        Intent intent = getIntent();
        String courseTitle = intent.getStringExtra("course_title");

        // Find course in repository
        Course course = null;
        for (Course c : CourseRepository.getCourses()) {
            if (c.getTitle().equals(courseTitle)) {
                course = c;
                break;
            }
        }

        if (course != null) {
            tvCourseTitle.setText(course.getTitle());
            tvInstructor.setText("Giảng viên: " + course.getInstructor());
            tvPrice.setText("Giá: " + course.getPrice());
            ratingBar.setRating(course.getRating());
            progressBar.setProgress(course.getProgress());
            imgCover.setImageResource(course.getImageResId());
        } else {
            Toast.makeText(this, "Không tìm thấy khóa học", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupTabs() {
        // Add tabs
        tabLayout.addTab(tabLayout.newTab().setText("Tổng quan"));
        tabLayout.addTab(tabLayout.newTab().setText("Nội dung khóa học"));
        tabLayout.addTab(tabLayout.newTab().setText("Hỏi đáp"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(CourseDetailActivity.this, "Chuyển sang: " + tab.getText(), Toast.LENGTH_SHORT).show();
                // TODO: Implement fragment switching for tab content
                switch (tab.getPosition()) {
                    case 0:
                        // Show Overview content
                        break;
                    case 1:
                        // Show Course Content
                        break;
                    case 2:
                        // Show Q&A
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}
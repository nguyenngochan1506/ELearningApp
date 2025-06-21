package vn.edu.hcmuaf.e_learningapp.features.courses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.hcmuaf.e_learningapp.R;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvCourseTitle, tvInstructor;
    private ProgressBar progressBar;
    private ImageView imgCover;
    private TabLayout tabLayout;
    private Button btnEnroll;
    private Course course;

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
        progressBar = findViewById(R.id.progressBar);
        imgCover = findViewById(R.id.imgCover);
        tabLayout = findViewById(R.id.tabLayout);
        btnEnroll = findViewById(R.id.btnEnroll);



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
        for (Course c : CourseRepository.getCourses()) {
            if (c.getTitle().equals(courseTitle)) {
                this.course = c;
                break;
            }
        }

        if (course != null) {
            tvCourseTitle.setText(course.getTitle());
            tvInstructor.setText("Giảng viên: " + course.getInstructor());
            progressBar.setProgress(course.getProgress());
            imgCover.setImageResource(course.getImageResId());
        } else {
            Toast.makeText(this, "Không tìm thấy khóa học", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void setupTabs() {
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        if (course == null) return;

        CoursePagerAdapter adapter = new CoursePagerAdapter(this, course);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tổng quan");
                    break;
                case 1:
                    tab.setText("Nội dung khóa học");
                    break;
                case 2:
                    tab.setText("Hỏi đáp");
                    break;
            }
        }).attach();
    }
}
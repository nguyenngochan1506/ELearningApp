package vn.edu.hcmuaf.e_learningapp.core.base;

import android.os.Bundle;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

import vn.edu.hcmuaf.e_learningapp.R;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvCourseTitle, tvInstructor, tvRating;
    private ProgressBar progressBar;
    private CheckBox checkBoxDone;
    private EditText editTextQuestion;
    private ImageView imgCover;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);

        initViews();
        setupTabs();
        loadDataFromIntent();
    }

    private void initViews() {
        tvCourseTitle = findViewById(R.id.tvCourseTitle);
        tvInstructor = findViewById(R.id.tvInstructor);
        tvRating = findViewById(R.id.tvRating);
        progressBar = findViewById(R.id.progressBar);
        checkBoxDone = findViewById(R.id.checkBox);
        editTextQuestion = findViewById(R.id.editTextQuestion);
        imgCover = findViewById(R.id.imgCover);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setupTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(CourseDetailActivity.this, "Đã chọn: " + tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                Toast.makeText(this, "Đã hoàn thành bài học!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadDataFromIntent() {
        String title = getIntent().getStringExtra("title");
        String instructor = getIntent().getStringExtra("instructor");
        String rating = getIntent().getStringExtra("rating");
        int students = getIntent().getIntExtra("students", 0);
        int imageResId = getIntent().getIntExtra("image", R.drawable.course_cover);

        tvCourseTitle.setText(title);
        tvInstructor.setText(instructor);
        tvRating.setText("⭐ " + rating + " • " + students + " học viên");
        imgCover.setImageResource(imageResId);
    }
}

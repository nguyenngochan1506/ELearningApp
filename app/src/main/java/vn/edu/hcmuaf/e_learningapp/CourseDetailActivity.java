package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

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

        // Sample dynamic logic
        checkBoxDone.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(this, "Bạn đã hoàn thành bài học!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(CourseDetailActivity.this, "Chuyển sang: " + tab.getText(), Toast.LENGTH_SHORT).show();
                // TODO: Hiển thị nội dung phù hợp tùy theo tab được chọn
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}

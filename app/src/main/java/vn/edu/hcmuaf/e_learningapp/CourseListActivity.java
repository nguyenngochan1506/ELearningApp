package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CoursePageResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CourseResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CourseAdapter adapter;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        // Setup Toolbar
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Danh sách khóa học");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiện nút back
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);  // Đúng ID theo XML
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load courses from API
        loadCourses();
    }
    private void loadCourses(){
        CourseRepository.getCourses(this, new CourseRepository.CourseCallback() {
            @Override
            public void onSuccess(CoursePageResponseDto data) {
                // Chuyển đổi dữ liệu từ DTO sang model Course
                List<Course> courseList = new ArrayList<>();
                for (CourseResponseDto dto : data.getCourses()) {
                    Course c = new Course();
                    c.setId(dto.getId());
                    c.setTitle(dto.getName());
                    c.setInstructor(dto.getTeacher().getFullName());
                    c.setDescription(dto.getDescription());
                    c.setCategory(dto.getCategory().getName());
                    c.setImageUrl(dto.getThumbnail());
                    c.setPrice("0");
                    courseList.add(c);
                }
                // Cập nhật adapter
                adapter = new CourseAdapter(courseList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CourseListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Đóng activity khi bấm back trên toolbar
        return true;
    }
}

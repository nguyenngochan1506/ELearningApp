package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<Course> courseList;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load courses from repository
        courseList = CourseRepository.getCourses();
        adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Đóng activity khi bấm back trên toolbar
        return true;
    }
}

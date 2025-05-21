package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rvCourseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load courses from repository
        courseList = CourseRepository.getCourses();
        adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);
    }
}
package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<Course> courseList;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        menuButton = findViewById(R.id.menuButton);
        recyclerView = findViewById(R.id.recyclerViewCourses);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseList = CourseRepository.getCourses();
        adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);

        // Set up menu button click listener
        menuButton.setOnClickListener(v -> {

        });
    }
}
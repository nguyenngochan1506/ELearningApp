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
import android.content.Intent;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<Course> courseList;
    private ImageView profileIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imgicon ảnh đại diện
        ImageView profileIcon = findViewById(R.id.profileIcon);
        recyclerView = findViewById(R.id.recyclerViewCourses);

        //sự kiện click ảnh đại diện -> login.xml
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseList = CourseRepository.getCourses();
        adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);

    }
  
}
package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseListActivity;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCourses;
    private CourseAdapter courseAdapter;
    private List<Course> courseList;
    private ImageButton menuButton;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gán view từ layout
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        menuButton = findViewById(R.id.menuButton);
        profileImage = findViewById(R.id.profileImage);

        // Sự kiện click vào ảnh đại diện để sang LoginActivity
        profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Thiết lập RecyclerView hiển thị danh sách khóa học
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));
        courseList = CourseRepository.getCourses(); // dữ liệu tạm thời
        courseAdapter = new CourseAdapter(courseList);
        recyclerViewCourses.setAdapter(courseAdapter);

        // Sự kiện click menu (nếu cần sau này)
        menuButton.setOnClickListener(v -> {
            // TODO: Mở navigation drawer hoặc menu
        });
    }
}

package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.chat.ChatActivity;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CoursePageResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CourseResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.exam.ExamListActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCourses;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private List<Course> courseList;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        rvCourses = findViewById(R.id.rvCourses);
        progressBar = findViewById(R.id.progressBar);

        // Setup Toolbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Setup NavigationView
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Already on home screen
            } else if (id == R.id.nav_courses) {
                Intent intent = new Intent(MainActivity.this, CourseListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_tests) {
                // Navigate to tests activity (placeholder)
                Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_chat) {
                // Navigate to chat activity (placeholder)
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_forum) {
                // Navigate to forum activity (placeholder)
            } else if (id == R.id.nav_profile) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Setup Courses RecyclerView
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        courseList = new ArrayList<>();
        courseAdapter = new CourseAdapter(courseList);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setHasFixedSize(true);

        // Setup FloatingActionButton
        FloatingActionButton fabAddCourse = findViewById(R.id.fabAddCourse);
        fabAddCourse.setOnClickListener(v -> {
            // Add logic to add a new course (placeholder)
            Toast.makeText(MainActivity.this, "Tính năng thêm khóa học chưa được triển khai", Toast.LENGTH_SHORT).show();
        });
        // Load courses from API
        loadCourses();
    }
    private void loadCourses() {
        progressBar.setVisibility(View.VISIBLE);
        CourseRepository.getCourses(this, new CourseRepository.CourseCallback() {
            @Override
            public void onSuccess(CoursePageResponseDto data) {
                progressBar.setVisibility(View.GONE);
                courseList.clear();
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
                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
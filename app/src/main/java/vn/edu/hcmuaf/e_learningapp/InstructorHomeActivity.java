package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class InstructorHomeActivity extends AppCompatActivity {

    private ImageView profileIcon;
    private TextView InstructorName;
    private ViewPager2 vpBanner;
    private RecyclerView rvManagedCourses, rvExams;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_home);

        // Initialize views
        profileIcon = findViewById(R.id.profileIcon);
        InstructorName = findViewById(R.id.tvInstructorName);
        vpBanner = findViewById(R.id.vpBanner);
        // rvManagedCourses = findViewById(R.id.rvManagedCourses);
        // rvExams = findViewById(R.id.rvExams);
        // fabAdd = findViewById(R.id.fabAdd);

        // Setup profile icon click
        profileIcon.setOnClickListener(v -> {
            // Navigate to profile or logout
        });

        // Setup banner
        setupBanner();

        // Setup Managed Courses RecyclerView
        rvManagedCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // Add adapter for managed courses (to be implemented)

        // Setup Exams RecyclerView
        rvExams.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // Add adapter for exams (to be implemented)

        // Setup FAB for adding new course or exam
        fabAdd.setOnClickListener(v -> {
            // Navigate to add course or exam activity
        });
    }

    private void setupBanner() {
        // Setup ViewPager2 for banner (to be implemented)
    }
}
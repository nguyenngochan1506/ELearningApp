package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;
import vn.edu.hcmuaf.e_learningapp.features.instructors.Instructor;
import vn.edu.hcmuaf.e_learningapp.features.instructors.InstructorAdapter;
import vn.edu.hcmuaf.e_learningapp.features.categories.Category;
import vn.edu.hcmuaf.e_learningapp.features.categories.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategories, rvFeaturedCourses, rvInstructors;
    private ViewPager2 vpBanner;
    private Button btnStartNow, btnViewCourses;
    private ImageView profileIcon;
    private List<Course> courseList;
    private List<Category> categoryList;
    private List<Instructor> instructorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        profileIcon = findViewById(R.id.profileIcon);
        vpBanner = findViewById(R.id.vpBanner);
        btnStartNow = findViewById(R.id.btnStartNow);
        btnViewCourses = findViewById(R.id.btnViewCourses);
        rvCategories = findViewById(R.id.rvCategories);
        rvFeaturedCourses = findViewById(R.id.rvFeaturedCourses);
        rvInstructors = findViewById(R.id.rvInstructors);

        // Profile icon click -> Navigate to LoginActivity
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Setup banner (using ViewPager2 for carousel)
        setupBanner();

        // Setup buttons
        btnStartNow.setOnClickListener(v -> {
            // Navigate to a signup or onboarding activity
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        btnViewCourses.setOnClickListener(v -> {
            // Navigate to courses list activity
            Intent intent = new Intent(MainActivity.this, CourseListActivity.class);
            startActivity(intent);
        });

        // Setup Categories RecyclerView
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        categoryList = getCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        rvCategories.setAdapter(categoryAdapter);

        // Setup Featured Courses RecyclerView
        rvFeaturedCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        courseList = CourseRepository.getCourses();
        CourseAdapter courseAdapter = new CourseAdapter(courseList);
        rvFeaturedCourses.setAdapter(courseAdapter);

        // Setup Instructors RecyclerView
        rvInstructors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        instructorList = getInstructors();
        InstructorAdapter instructorAdapter = new InstructorAdapter(instructorList);
        rvInstructors.setAdapter(instructorAdapter);
    }

    private void setupBanner() {
        // For simplicity, use a static banner image
        // You can extend this to a ViewPager2 adapter for multiple images/videos
        vpBanner.setAdapter(new BannerAdapter(getBannerImages()));
    }

    private List<Integer> getBannerImages() {
        List<Integer> banners = new ArrayList<>();
        banners.add(R.drawable.banner_image);
        return banners;
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Lập trình", R.drawable.ic_programming));
        categories.add(new Category("Kinh doanh", R.drawable.ic_business));
        categories.add(new Category("Thiết kế", R.drawable.ic_design));
        categories.add(new Category("Ngôn ngữ", R.drawable.ic_language));
        return categories;
    }

    private List<Instructor> getInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor("Giảng viên A", 5000, 10, R.drawable.instructor_image1));
        instructors.add(new Instructor("Giảng Viên B", 3000, 5, R.drawable.instructor_image2));
        return instructors;
    }
}
package vn.edu.hcmuaf.e_learningapp.core.base;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.core.adapter.CourseAdapter;
import vn.edu.hcmuaf.e_learningapp.core.data.Course;


public class CourseListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list);

        recyclerView = findViewById(R.id.recyclerView);

        List<Course> courseList = Arrays.asList(
                new Course("Lập trình Java", "Nguyễn Văn A", "4.5", 1200, R.drawable.course_cover),
                new Course("Android cơ bản", "Trần Thị B", "4.7", 950, R.drawable.course_image1),
                new Course("Web Fullstack", "Lê Văn C", "4.8", 1800, R.drawable.course_image2)
        );

        CourseAdapter adapter = new CourseAdapter(this, courseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}


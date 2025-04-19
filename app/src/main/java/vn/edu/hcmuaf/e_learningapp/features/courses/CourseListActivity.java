package vn.edu.hcmuaf.e_learningapp.features.courses;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.R;


public class CourseListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // SET CỨNG DỮ LIỆU DEMO
        courseList = new ArrayList<>();
        courseList.add(new Course(1, "Lập trình Java cơ bản", "Nguyễn Văn A", 4.5f, 1200, "https://i.imgur.com/3G3K5Yy.jpg"));
        courseList.add(new Course(2, "Thiết kế Web với HTML/CSS", "Trần Thị B", 4.7f, 950, "https://i.imgur.com/dM7Thhn.jpg"));
        courseList.add(new Course(3, "ReactJS từ cơ bản đến nâng cao", "Lê Văn C", 4.8f, 2100, "https://i.imgur.com/OtP2fGX.jpg"));
        courseList.add(new Course(4, "Lập trình Android với Java", "Phạm Minh D", 4.6f, 1500, "https://i.imgur.com/H5Zjs9N.jpg"));

        courseAdapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(courseAdapter);
    }
}





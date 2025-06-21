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
        courseList.add(new Course( "Lập trình Java cơ bản", "Nguyễn Văn A", 50, 1200,4.5f,  "https://i.imgur.com/3G3K5Yy.jpg"));
        courseList.add(new Course("Thiết kế Web với HTML/CSS", "Trần Thị B", 40, 950, 4.5f,"https://i.imgur.com/dM7Thhn.jpg"));
        courseList.add(new Course("ReactJS từ cơ bản đến nâng cao", "Lê Văn C", 40, 2100,4.5f, "https://i.imgur.com/OtP2fGX.jpg"));
        courseList.add(new Course( "Lập trình Android với Java", "Phạm Minh D", 40, 1500,4.5f, "https://i.imgur.com/H5Zjs9N.jpg"));

        courseAdapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(courseAdapter);
    }
}





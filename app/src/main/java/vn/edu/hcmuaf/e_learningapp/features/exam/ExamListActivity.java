package vn.edu.hcmuaf.e_learningapp.features.exam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.exam.Exam;
import vn.edu.hcmuaf.e_learningapp.features.exam.ExamListAdapter;

public class ExamListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExamListAdapter adapter;
    List<Exam> examList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        recyclerView = findViewById(R.id.examRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        examList = new ArrayList<>();
        examList.add(new Exam("1", "Kiểm tra tiến độ - Lập trình Web", "Lập trình Web Fullstack",
                "60 phút", "14:00 25/05/2024", "15:00 25/05/2024", 45, "32 phút", "in_progress"));
        examList.add(new Exam("2", "Bài test JavaScript nâng cao", "JavaScript Chuyên sâu",
                "45 phút", "09:00 28/05/2024", "09:45 28/05/2024", 32, "2 ngày 18 giờ", "upcoming"));

        adapter = new ExamListAdapter(examList);
        recyclerView.setAdapter(adapter);
    }
}
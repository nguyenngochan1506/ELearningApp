package vn.edu.hcmuaf.e_learningapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.exam.Exam;
import vn.edu.hcmuaf.e_learningapp.features.exam.ExamListAdapter;

public class ExamListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.examRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data - replace with real data
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam("Kiểm tra giữa kỳ", "Lớp 10A", "01/01/2023", "Đang mở"));
        exams.add(new Exam("Bài tập chương 2", "Lớp 11B", "15/01/2023", "Đã đóng"));

        ExamListAdapter adapter = new ExamListAdapter(exams);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
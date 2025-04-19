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

public class GradingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grading, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.submissionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data - replace with real data
        List<Submission> submissions = new ArrayList<>();
        submissions.add(new Submission("Nguyễn Văn A", "01/01/2023 14:30", "Đã chấm"));
        submissions.add(new Submission("Trần Thị B", "01/01/2023 15:15", "Chờ chấm"));

        SubmissionAdapter adapter = new SubmissionAdapter(submissions);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
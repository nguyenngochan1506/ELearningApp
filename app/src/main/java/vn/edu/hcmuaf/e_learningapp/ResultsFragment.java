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

public class ResultsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.resultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data - replace with real data
        List<Result> results = new ArrayList<>();
        results.add(new Result("Nguyễn Văn A", "8.5", "01/01/2023"));
        results.add(new Result("Trần Thị B", "7.0", "01/01/2023"));

        ResultsAdapter adapter = new ResultsAdapter(results);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
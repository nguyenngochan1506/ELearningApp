package vn.edu.hcmuaf.e_learningapp.features.result;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {

    private List<Result> resultList;

    public ResultsAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.studentNameTextView.setText(result.getStudentName());
        holder.scoreTextView.setText(result.getScore());
        holder.dateTextView.setText(result.getDate());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameTextView, scoreTextView, dateTextView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameTextView = itemView.findViewById(R.id.studentName);
            scoreTextView = itemView.findViewById(R.id.score);
            dateTextView = itemView.findViewById(R.id.resultDate);
        }
    }
}
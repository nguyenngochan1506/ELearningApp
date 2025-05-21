package vn.edu.hcmuaf.e_learningapp.features.exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ExamViewHolder> {

    private List<Exam> examList;

    public ExamListAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exam, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.nameTextView.setText(exam.getName());
        holder.classTextView.setText(exam.getClassName());
        holder.dateTextView.setText(exam.getDate());
        holder.statusTextView.setText(exam.getStatus());
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, classTextView, dateTextView, statusTextView;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.examName);
            classTextView = itemView.findViewById(R.id.examClass);
            dateTextView = itemView.findViewById(R.id.examDate);
            statusTextView = itemView.findViewById(R.id.examStatus);
        }
    }
}
package vn.edu.hcmuaf.e_learningapp.features.submission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    private List<Submission> submissionList;

    public SubmissionAdapter(List<Submission> submissionList) {
        this.submissionList = submissionList;
    }

    @NonNull
    @Override
    public SubmissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_submission, parent, false);
        return new SubmissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionViewHolder holder, int position) {
        Submission submission = submissionList.get(position);
        holder.studentNameTextView.setText(submission.getStudentName());
        holder.submissionTimeTextView.setText(submission.getSubmissionTime());
        holder.statusTextView.setText(submission.getStatus());
    }

    @Override
    public int getItemCount() {
        return submissionList.size();
    }

    static class SubmissionViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameTextView, submissionTimeTextView, statusTextView;

        public SubmissionViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameTextView = itemView.findViewById(R.id.studentName);
            submissionTimeTextView = itemView.findViewById(R.id.submissionTime);
            statusTextView = itemView.findViewById(R.id.submissionStatus);
        }
    }
}
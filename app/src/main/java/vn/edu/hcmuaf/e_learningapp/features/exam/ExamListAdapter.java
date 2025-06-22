package vn.edu.hcmuaf.e_learningapp.features.exam;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.Quiz.QuizActivity;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ExamViewHolder> {
    private List<Exam> examList;

    public ExamListAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    @Override
    public int getItemViewType(int position) {
        String status = examList.get(position).status;
        return "in_progress".equalsIgnoreCase(status) ? 0 : 1;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exam_card_in_progress, parent, false);
            return new InProgressExamViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exam_card_upcoming, parent, false);
            return new UpcomingExamViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);

        holder.examTitle.setText(exam.title);
        holder.courseName.setText("Môn học: " + exam.course);
        holder.examDuration.setText("⏱ " + exam.duration);
        holder.examTime.setText("📅 " + exam.startTime + " - " + exam.endTime);
        holder.examParticipants.setText("👥 " + exam.participants + " thí sinh");
        if (holder.remainingTime != null) {
            holder.remainingTime.setText("⏳ " + exam.remainingTime);
        }

        if (holder instanceof InProgressExamViewHolder) {
            InProgressExamViewHolder inHolder = (InProgressExamViewHolder) holder;
            inHolder.statusBadge.setText("Đang diễn ra");
            inHolder.statusBadge.setVisibility(View.VISIBLE);
            inHolder.actionButton.setText("Vào làm bài →");
            inHolder.actionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4F46E5")));
            inHolder.actionButton.setOnClickListener(v -> {
                if (exam.id != null) {
                    Intent intent = new Intent(v.getContext(), QuizActivity.class);
                    intent.putExtra("QUIZ_ID", exam.id);
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "Invalid exam ID", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof UpcomingExamViewHolder) {
            UpcomingExamViewHolder upHolder = (UpcomingExamViewHolder) holder;
            upHolder.statusBadge.setVisibility(View.GONE);
            upHolder.reminderButton.setVisibility(View.VISIBLE);
            upHolder.reminderButton.setOnClickListener(v ->
                    Toast.makeText(v.getContext(), "Đã đặt nhắc nhở tham gia", Toast.LENGTH_SHORT).show()
            );
        }
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    // Base ViewHolder
    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView examTitle, courseName, examDuration, examTime, examParticipants, remainingTime, statusBadge;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examTitle = itemView.findViewById(R.id.examTitle);
            courseName = itemView.findViewById(R.id.examSubject);
            examDuration = itemView.findViewById(R.id.examDuration);
            examTime = itemView.findViewById(R.id.examTime);
            examParticipants = itemView.findViewById(R.id.examParticipants);
            statusBadge = itemView.findViewById(R.id.statusBadge);
        }
    }

    public static class InProgressExamViewHolder extends ExamViewHolder {
        Button actionButton;

        public InProgressExamViewHolder(@NonNull View itemView) {
            super(itemView);
            remainingTime = itemView.findViewById(R.id.remainingTime);
            actionButton = itemView.findViewById(R.id.actionButton);
        }
    }

    public static class UpcomingExamViewHolder extends ExamViewHolder {
        Button reminderButton;

        public UpcomingExamViewHolder(@NonNull View itemView) {
            super(itemView);
            remainingTime = itemView.findViewById(R.id.countdownText);
            reminderButton = itemView.findViewById(R.id.reminderButton);
        }
    }
}

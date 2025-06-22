package vn.edu.hcmuaf.e_learningapp.core.adapter;

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
import vn.edu.hcmuaf.e_learningapp.core.data.Exam;
import vn.edu.hcmuaf.e_learningapp.features.Quiz.QuizActivity;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private List<Exam> examList;

    public ExamAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_card_in_progress, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_card_upcoming, parent, false);
        }
        return new ExamViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        String status = examList.get(position).status;
        if ("in_progress".equalsIgnoreCase(status)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);

        holder.actionButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), QuizActivity.class);
            intent.putExtra("exam_id", exam.id);
            v.getContext().startActivity(intent);
        });

        holder.examTitle.setText(exam.title);
        holder.courseName.setText("Môn học: " + exam.course);
        holder.examDuration.setText("⏱ " + exam.duration + " phút");
        holder.examTime.setText("📅 " + exam.startTime + " - " + exam.endTime);
        holder.examParticipants.setText("👥 " + exam.participants + " thí sinh");
        holder.remainingTime.setText("⏳ " + exam.remainingTime + " phút");

        if ("in_progress".equalsIgnoreCase(exam.status)) {
            if (holder.statusBadge != null) {
                holder.statusBadge.setText("Đang diễn ra");
                holder.statusBadge.setVisibility(View.VISIBLE);
            }
            holder.actionButton.setText("Vào làm bài →");
            holder.reminderButton.setVisibility(View.GONE);
            holder.actionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4F46E5")));
        } else {
            if (holder.statusBadge != null) {
                holder.statusBadge.setVisibility(View.GONE);
            }
            holder.actionButton.setText("Vào xem trước");
            holder.actionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E5E7EB")));

            holder.reminderButton.setVisibility(View.VISIBLE);
            holder.reminderButton.setOnClickListener(v ->
                    Toast.makeText(v.getContext(), "Đã đặt nhắc nhở tham gia", Toast.LENGTH_SHORT).show()
            );
        }
    }


    @Override
    public int getItemCount() {
        return examList.size();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView examTitle, courseName, examDuration, examTime, examParticipants, remainingTime, statusBadge;
        Button actionButton, reminderButton;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examTitle = itemView.findViewById(R.id.examTitle);
            courseName = itemView.findViewById(R.id.examSubject);
            examDuration = itemView.findViewById(R.id.examDuration);
            examTime = itemView.findViewById(R.id.examTime);
            examParticipants = itemView.findViewById(R.id.examParticipants);
            remainingTime = itemView.findViewById(R.id.remainingTime);
            statusBadge = itemView.findViewById(R.id.statusBadge); // Có thể null nếu layout không có
            actionButton = itemView.findViewById(R.id.actionButton);
            reminderButton = itemView.findViewById(R.id.reminderButton);
        }
    }

}
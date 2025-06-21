package vn.edu.hcmuaf.e_learningapp.features.lesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    private final List<Lesson> lessonList;

    public LessonAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.tvLessonNumber.setText(String.valueOf(lesson.getNumber()) + ".");
        holder.tvLessonTitle.setText(lesson.getName());
        holder.tvLessonDuration.setText("Thời lượng: " + lesson.getDuration() + " phút");
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonNumber, tvLessonTitle, tvLessonDuration;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLessonNumber = itemView.findViewById(R.id.tvLessonNumber);
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvLessonDuration = itemView.findViewById(R.id.tvLessonDuration);
        }
    }
}

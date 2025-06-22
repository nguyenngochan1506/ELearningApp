package vn.edu.hcmuaf.e_learningapp.features.lesson;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.R;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    private OnItemClickListener onItemClickListener;
    private int selectedPosition = -1; // Vị trí bài học được chọn

    public interface OnItemClickListener {
        void onItemClick(int position, Lesson lesson);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public LessonAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.tvLessonNumber.setText((position + 1) + ".");
        holder.tvLessonTitle.setText(lesson.getName());
        holder.tvLessonDuration.setText("Thời lượng: " + lesson.getDuration() + " phút");

        // Đặt màu chữ mặc định khi không active
        holder.tvLessonNumber.setTextColor(selectedPosition == position ? R.color.white : R.color.black);
        holder.tvLessonTitle.setTextColor(selectedPosition == position ? R.color.white : R.color.black);
        holder.tvLessonDuration.setTextColor(selectedPosition == position ? R.color.white : R.color.black);

        holder.itemView.setSelected(selectedPosition == position);

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, lesson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonList != null ? lessonList.size() : 0;
    }

    public void setSelectedPosition(int position) {
        int previousPosition = selectedPosition;
        selectedPosition = position;
        notifyItemChanged(previousPosition);
        notifyItemChanged(selectedPosition);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonNumber, tvLessonTitle, tvLessonDuration;

        ViewHolder(View itemView) {
            super(itemView);
            tvLessonNumber = itemView.findViewById(R.id.tvLessonNumber);
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvLessonDuration = itemView.findViewById(R.id.tvLessonDuration);
        }
    }
}
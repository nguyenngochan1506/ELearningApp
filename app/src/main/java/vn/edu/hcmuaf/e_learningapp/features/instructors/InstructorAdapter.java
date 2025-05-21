package vn.edu.hcmuaf.e_learningapp.features.instructors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.R;
import java.util.List;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.ViewHolder> {

    private List<Instructor> instructorList;

    public InstructorAdapter(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instructor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Instructor instructor = instructorList.get(position);
        holder.tvInstructorName.setText(instructor.getName());
        holder.tvStudentCount.setText(instructor.getStudentCount() + " học viên");
        holder.tvCourseCount.setText(instructor.getCourseCount() + " khóa học");
        holder.ivInstructorAvatar.setImageResource(instructor.getAvatarResId());
    }

    @Override
    public int getItemCount() {
        return instructorList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInstructorName, tvStudentCount, tvCourseCount;
        ImageView ivInstructorAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            tvInstructorName = itemView.findViewById(R.id.tvInstructorName);
            tvStudentCount = itemView.findViewById(R.id.tvStudentCount);
            tvCourseCount = itemView.findViewById(R.id.tvCourseCount);
            ivInstructorAvatar = itemView.findViewById(R.id.ivInstructorAvatar);
        }
    }
}
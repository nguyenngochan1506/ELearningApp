package vn.edu.hcmuaf.e_learningapp.features.courses;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.courseTitle.setText(course.getTitle());
        holder.courseInstructor.setText(course.getInstructor());
        holder.courseImage.setImageResource(course.getImageResId());
        holder.btnViewDetails.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), CourseDetailActivity.class);
            intent.putExtra("course_title", course.getTitle());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle, courseInstructor, progressText, tvPrice;
        ProgressBar progressBar;
        ImageView courseImage;
        RatingBar ratingBar;
        Button btnViewDetails;

        ViewHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseInstructor = itemView.findViewById(R.id.courseInstructor);
            progressBar = itemView.findViewById(R.id.progressBar);
            courseImage = itemView.findViewById(R.id.courseImage);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
        }
    }
}
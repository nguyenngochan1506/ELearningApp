package vn.edu.hcmuaf.e_learningapp.core.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.core.base.CourseDetailActivity;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.core.data.Course;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private final Context context;
    private final List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvTitle.setText(course.getTitle());
        holder.tvInstructor.setText(course.getInstructor());
        holder.tvRating.setText("⭐ " + course.getRating() + " • " + course.getStudentCount() + " học viên");
        holder.imgCourseImage.setImageResource(course.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseDetailActivity.class);
            intent.putExtra("title", course.getTitle());
            intent.putExtra("instructor", course.getInstructor());
            intent.putExtra("rating", course.getRating());
            intent.putExtra("students", course.getStudentCount());
            intent.putExtra("image", course.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCourseImage;
        TextView tvTitle, tvInstructor, tvRating;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCourseImage = itemView.findViewById(R.id.courseImage);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvInstructor = itemView.findViewById(R.id.tvInstructor);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}

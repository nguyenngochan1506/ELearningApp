package vn.edu.hcmuaf.e_learningapp.features.courses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import vn.edu.hcmuaf.e_learningapp.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvTitle.setText(course.getTitle());
        holder.tvInstructor.setText(course.getInstructor());

        // Định dạng số học viên và đánh giá
        String ratingInfo = String.format("⭐ %.1f (%d học viên)", course.getRating(), course.getNumStudents());
        holder.tvRating.setText(ratingInfo);

        // Set ảnh bìa khóa học
        Picasso.get().load(course.getImageUrl()).into(holder.imgCourseImage);

        Log.d("CourseAdapter", "onBindViewHolder: " + course.getTitle());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvInstructor, tvRating;
        ImageView imgCourseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvInstructor = itemView.findViewById(R.id.tvInstructor);
            tvRating = itemView.findViewById(R.id.tvRating);
            imgCourseImage = itemView.findViewById(R.id.imgCourseImage);
        }
    }
}

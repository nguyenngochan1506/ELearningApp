package vn.edu.hcmuaf.e_learningapp.features.module;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;
import vn.edu.hcmuaf.e_learningapp.features.lesson.LessonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private List<Module> moduleList;
    private OnItemClickListener onItemClickListener;
    private int selectedModulePosition = 0;
    private int selectedLessonPosition = 0;

    public interface OnItemClickListener {
        void onItemClick(int modulePosition, int lessonPosition, Lesson lesson);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ModuleAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Module module = moduleList.get(position);
        holder.tvModuleTitle.setText(module.getName());
        holder.tvModuleDesc.setText(module.getDescription());

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
        holder.rvLessons.setLayoutManager(layoutManager);
        List<Lesson> lessons = module.getLessons();
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        LessonAdapter lessonAdapter = new LessonAdapter(lessons);
        holder.rvLessons.setAdapter(lessonAdapter);

        holder.rvLessons.setMinimumHeight(100);

        if (position == selectedModulePosition) {
            lessonAdapter.setSelectedPosition(selectedLessonPosition);
        }

        lessonAdapter.setOnItemClickListener((lessonPosition, lesson) -> {
            selectedModulePosition = position;
            selectedLessonPosition = lessonPosition;
            notifyDataSetChanged();
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, lessonPosition, lesson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList != null ? moduleList.size() : 0;
    }

    public void setSelectedPosition(int modulePosition, int lessonPosition) {
        selectedModulePosition = modulePosition;
        selectedLessonPosition = lessonPosition;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvModuleTitle, tvModuleDesc;
        RecyclerView rvLessons;

        ViewHolder(View itemView) {
            super(itemView);
            tvModuleTitle = itemView.findViewById(R.id.tvModuleTitle);
            tvModuleDesc = itemView.findViewById(R.id.tvModuleDesc);
            rvLessons = itemView.findViewById(R.id.rvLessons);
        }
    }
}
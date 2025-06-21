package vn.edu.hcmuaf.e_learningapp.features.module;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;
import vn.edu.hcmuaf.e_learningapp.features.lesson.LessonAdapter;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    private final List<Module> moduleList;

    public ModuleAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(vn.edu.hcmuaf.e_learningapp.R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module module = moduleList.get(position);
        holder.tvModuleTitle.setText(module.getName());
        holder.tvModuleDesc.setText(module.getDescription());

        // setup child lessons
        List<Lesson> sortedLessons = new ArrayList<>(module.getLessons());
        Collections.sort(sortedLessons, (l1, l2) -> Integer.compare(l1.getNumber(), l2.getNumber()));

        LessonAdapter lessonAdapter = new LessonAdapter(sortedLessons);
        holder.rvLessons.setAdapter(lessonAdapter);
        holder.rvLessons.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        // toggle expand
        holder.itemView.setOnClickListener(v -> {
            if (holder.rvLessons.getVisibility() == View.VISIBLE) {
                holder.rvLessons.setVisibility(View.GONE);
            } else {
                holder.rvLessons.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        TextView tvModuleTitle, tvModuleDesc;
        RecyclerView rvLessons;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvModuleTitle = itemView.findViewById(R.id.tvModuleTitle);
            tvModuleDesc = itemView.findViewById(R.id.tvModuleDesc);
            rvLessons = itemView.findViewById(R.id.rvLessons);
        }
    }
}


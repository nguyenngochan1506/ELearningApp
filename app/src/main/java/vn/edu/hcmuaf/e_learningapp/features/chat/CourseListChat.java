package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.core.adapter.CourseAdapter;

public class CourseListChat extends Fragment {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    public CourseListChat() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_courses, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CourseAdapter(getContext(), null) {
            @Override
            public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setOnClickListener(v -> {
                    // Mở ChatActivity với giảng viên của khóa học
//                    startChatWithInstructor(course.getInstructor());
                });
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }
}


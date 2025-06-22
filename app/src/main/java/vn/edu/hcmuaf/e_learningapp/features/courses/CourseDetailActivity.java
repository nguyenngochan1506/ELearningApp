package vn.edu.hcmuaf.e_learningapp.features.courses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.courses.dto.CourseResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.file.FileResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;
import vn.edu.hcmuaf.e_learningapp.features.lesson.LessonActivity;
import vn.edu.hcmuaf.e_learningapp.features.lesson.dto.LessonResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.module.Module;
import vn.edu.hcmuaf.e_learningapp.features.module.dto.ModuleResponseDto;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvCourseTitle, tvInstructor;
    private ProgressBar progressBar;
    private ImageView imgCover;
    private TabLayout tabLayout;
    private Button btnEnroll;
    private Course course;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);

        initViews();
        loadCourseData();
    }

    private void initViews() {
        tvCourseTitle = findViewById(R.id.tvCourseTitle);
        tvInstructor = findViewById(R.id.tvInstructor);
        progressBar = findViewById(R.id.progressBar);
        imgCover = findViewById(R.id.imgCover);
        tabLayout = findViewById(R.id.tabLayout);
        btnEnroll = findViewById(R.id.btnEnroll);



        // Handle enroll button
        btnEnroll.setOnClickListener(v -> {
            Intent intent = new Intent(this, LessonActivity.class);
            intent.putExtra("course_id", course.getId());
            if (course.getModules() != null && !course.getModules().isEmpty()) {
                Module firstModule = course.getModules().get(0);
                if (firstModule.getLessons() != null && !firstModule.getLessons().isEmpty()) {
                    intent.putExtra("video_url", firstModule.getLessons().get(0).getVideoUrl());
                    intent.putExtra("modules", (Serializable) course.getModules());
                }
            }
            startActivity(intent);
        });
    }

    private void loadCourseData() {
        // Get course title from Intent
        Intent intent = getIntent();
        Long courseId = intent.getLongExtra("course_id", 0);

        //lay jwt tu sharedrefre
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String accessToken = prefs.getString("accessToken", null);
        CourseApi api = ApiClient.getClient(accessToken).create(CourseApi.class);
        Call<ResponseDto<CourseResponseDto>> call = api.getCourseById(courseId);
        call.enqueue(new Callback<ResponseDto<CourseResponseDto>>() {

            @Override
            public void onResponse(Call<ResponseDto<CourseResponseDto>> call, Response<ResponseDto<CourseResponseDto>> response) {
//                if(!response.isSuccessful() || response.code() !=200){
//                    Toast.makeText(CourseDetailActivity.this, "Lỗi khi lấy thông tin khóa học", Toast.LENGTH_SHORT).show();
//                }
                CourseResponseDto dto = response.body().getData();

                Course c = new Course();
                c.setId(dto.getId());
                c.setTitle(dto.getName());
                c.setInstructor(dto.getTeacher().getFullName());
                c.setDescription(dto.getDescription());
                c.setImageUrl(dto.getThumbnail());
                c.setPrice("0");
                c.setCategory(dto.getCategory().getName());

                List<Module> modules = new ArrayList<>();
                for(ModuleResponseDto m : dto.getModules()){
                    Module module = new Module();
                    module.setId(m.getId());
                    module.setName(m.getName());
                    module.setDescription(m.getDescription());
                    module.setNumber(m.getNumber());
                    List<Lesson> lessons = new ArrayList<>();
                    for(LessonResponseDto l : m.getLessons()){
                        Lesson lesson = new Lesson();
                        lesson.setId(Math.toIntExact(l.getId()));
                        lesson.setName(l.getName());
                        lesson.setContent(l.getContent());
                        lesson.setNumber(l.getNumber());
                        lesson.setDuration(l.getDuration());
                        lesson.setVideoUrl(l.getVideoUrl());
                        lesson.setFiles(l.getFiles());
                        lessons.add(lesson);
                    }
                    module.setLessons(lessons);
                    modules.add(module);
                }
                //sort by number
                modules.sort( (Module m1, Module m2) -> Integer.compare(m1.getNumber(), m2.getNumber()));
                c.setModules(modules);
                course = c;
                tvCourseTitle.setText(course.getTitle());
                tvInstructor.setText("Giảng viên: " + course.getInstructor());
                progressBar.setProgress(course.getProgress());
                Glide.with(CourseDetailActivity.this)
                        .load(course.getImageUrl())
                        .into(imgCover);

                setupTabs();
            }

            @Override
            public void onFailure(Call<ResponseDto<CourseResponseDto>> call, Throwable throwable) {
                Log.e("CourseDetailActivity", "API Failure: " + throwable.getMessage());
                Toast.makeText(CourseDetailActivity.this, "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupTabs() {
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        if (course == null) return;

        CoursePagerAdapter adapter = new CoursePagerAdapter(this, course);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tổng quan");
                    break;
                case 1:
                    tab.setText("Nội dung khóa học");
                    break;
                case 2:
                    tab.setText("Hỏi đáp");
                    break;
            }
        }).attach();
    }
}
package vn.edu.hcmuaf.e_learningapp.features.lesson;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.courses.Course;
import vn.edu.hcmuaf.e_learningapp.features.courses.CourseRepository;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;
import vn.edu.hcmuaf.e_learningapp.features.lesson.LessonAdapter;
import vn.edu.hcmuaf.e_learningapp.features.module.Module;

public class LessonActivity extends AppCompatActivity {

    private WebView webView;
    private RecyclerView rvLessons;
    private LessonAdapter lessonAdapter;
    private List<Lesson> lessonList;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Initialize views
        webView = findViewById(vn.edu.hcmuaf.e_learningapp.R.id.webViewVideo);
        rvLessons = findViewById(R.id.rvLessons);

        // Setup WebView for video playback
        setupWebView();

        // Get course ID from intent
        int courseId = getIntent().getIntExtra("course_id", -1);
        course = findCourseById(courseId);

        if (course != null) {
            // Load lessons from course modules
            loadLessons();
            setupRecyclerView();

            // Play the first lesson video by default if available
            if (!lessonList.isEmpty()) {
                playVideo(lessonList.get(0).getVideoUrl());
            }
        } else {
            showToast("Không tìm thấy khóa học");
            finish();
        }
    }

    private void setupWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }

    private Course findCourseById(int courseId) {
        for (Course c : CourseRepository.getCourses()) {
            if (c.getId() == courseId) {
                return c;
            }
        }
        return null;
    }

    private void loadLessons() {
        lessonList = new java.util.ArrayList<>();
        for (Module module : course.getModules()) {
            lessonList.addAll(module.getLessons());
        }
    }

    private void setupRecyclerView() {
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        lessonAdapter = new LessonAdapter(lessonList, lesson -> playVideo(lesson.getVideoUrl()));
        rvLessons.setAdapter(lessonAdapter);
    }

    private void playVideo(String videoUrl) {
        if (videoUrl != null && !videoUrl.isEmpty()) {
            // Load YouTube video or other video URL
            webView.loadUrl(videoUrl);
        } else {
            showToast("Không có video cho bài học này");
        }
    }

    private void showToast(String message) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
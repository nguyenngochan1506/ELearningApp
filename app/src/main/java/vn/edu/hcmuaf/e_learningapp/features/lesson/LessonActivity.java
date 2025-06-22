package vn.edu.hcmuaf.e_learningapp.features.lesson;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.module.Module;
import vn.edu.hcmuaf.e_learningapp.features.module.ModuleAdapter;

import java.util.ArrayList;
import java.util.List;

public class LessonActivity extends AppCompatActivity {

    private WebView videoPlayer;
    private RecyclerView rvLessons;
    private TextView tvEmpty;
    private Button btnPrevious, btnNext;
    private List<Module> moduleList;
    private int currentModuleIndex = 0;
    private int currentLessonIndex = 0;
    private ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Khởi tạo các view
        videoPlayer = findViewById(R.id.videoPlayer);
        rvLessons = findViewById(R.id.rvLessons);
        tvEmpty = findViewById(R.id.tvEmpty);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Cấu hình WebView
        configureWebView();

        // Lấy danh sách module
        moduleList = (List<Module>) getIntent().getSerializableExtra("modules");
        if (moduleList == null) {
            moduleList = new ArrayList<>();
        }
        // Đảm bảo mỗi module có danh sách bài học
        for (Module module : moduleList) {
            if (module.getLessons() == null) {
                module.setLessons(new ArrayList<>());
            }
        }

        // Debug: Kiểm tra dữ liệu
        Log.d("LessonActivity", "Number of modules: " + moduleList.size());
        for (int i = 0; i < moduleList.size(); i++) {
            Log.d("LessonActivity", "Module " + i + ": " + moduleList.get(i).getName() + ", Lessons: " + moduleList.get(i).getLessons().size());
        }

        // Kiểm tra và tải bài học đầu tiên
        if (!moduleList.isEmpty() && !moduleList.get(0).getLessons().isEmpty()) {
            loadInitialLesson();
            setupRecyclerView();
            updateNavigationButtons();
        } else {
            tvEmpty.setText("Không có module hoặc bài học nào");
            tvEmpty.setVisibility(View.VISIBLE);
            videoPlayer.setVisibility(View.GONE);
            rvLessons.setVisibility(View.GONE);
        }

        // Xử lý sự kiện nút Previous
        btnPrevious.setOnClickListener(v -> {
            if (currentLessonIndex > 0) {
                currentLessonIndex--;
            } else if (currentModuleIndex > 0) {
                currentModuleIndex--;
                currentLessonIndex = moduleList.get(currentModuleIndex).getLessons().size() - 1;
            }
            loadCurrentLesson();
            updateNavigationButtons();
            updateAdapterSelection();
        });

        // Xử lý sự kiện nút Next
        btnNext.setOnClickListener(v -> {
            List<Lesson> currentLessons = moduleList.get(currentModuleIndex).getLessons();
            if (currentLessonIndex < currentLessons.size() - 1) {
                currentLessonIndex++;
            } else if (currentModuleIndex < moduleList.size() - 1) {
                currentModuleIndex++;
                currentLessonIndex = 0;
            }
            loadCurrentLesson();
            updateNavigationButtons();
            updateAdapterSelection();
        });
    }

    private void configureWebView() {
        WebSettings webSettings = videoPlayer.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        videoPlayer.setWebChromeClient(new WebChromeClient());
    }

    private void loadInitialLesson() {
        loadLesson(0, 0);
    }

    private void loadCurrentLesson() {
        loadLesson(currentModuleIndex, currentLessonIndex);
    }

    private void loadLesson(int moduleIndex, int lessonIndex) {
        Module module = moduleList.get(moduleIndex);
        if (module != null && !module.getLessons().isEmpty()) {
            Lesson lesson = module.getLessons().get(lessonIndex);
            String videoUrl = lesson.getVideoUrl();
            String videoId = extractVideoId(videoUrl);
            if (videoId != null) {
                String embedUrl = "https://www.youtube.com/embed/" + videoId + "?rel=0&autoplay=1";
                videoPlayer.loadUrl(embedUrl);
                tvEmpty.setVisibility(View.GONE);
                videoPlayer.setVisibility(View.VISIBLE);
            } else {
                tvEmpty.setText("Không thể tải video: " + videoUrl);
                tvEmpty.setVisibility(View.VISIBLE);
                videoPlayer.setVisibility(View.GONE);
            }
        }
    }

    private String extractVideoId(String url) {
        if (url == null) return null;
        String videoId = null;
        if (url.contains("youtu.be/")) {
            int startIndex = url.indexOf("youtu.be/") + 9;
            int endIndex = url.indexOf("?", startIndex);
            if (endIndex == -1) endIndex = url.length();
            videoId = url.substring(startIndex, endIndex);
        } else if (url.contains("v=")) {
            int startIndex = url.indexOf("v=") + 2;
            int endIndex = url.indexOf("&", startIndex);
            if (endIndex == -1) endIndex = url.length();
            videoId = url.substring(startIndex, endIndex);
        }
        return videoId;
    }

    private void setupRecyclerView() {
        if (moduleList != null && !moduleList.isEmpty()) {
            rvLessons.setLayoutManager(new LinearLayoutManager(this));
            moduleAdapter = new ModuleAdapter(moduleList);
            rvLessons.setAdapter(moduleAdapter);

            // Xử lý khi chọn module hoặc lesson
            moduleAdapter.setOnItemClickListener((modulePosition, lessonPosition, lesson) -> {
                currentModuleIndex = modulePosition;
                currentLessonIndex = lessonPosition;
                loadCurrentLesson();
                updateNavigationButtons();
                updateAdapterSelection();
            });
            tvEmpty.setVisibility(View.GONE);
        } else {
            rvLessons.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void updateNavigationButtons() {
        List<Lesson> currentLessons = moduleList.get(currentModuleIndex).getLessons();
        btnPrevious.setEnabled(currentLessonIndex > 0 || currentModuleIndex > 0);
        btnNext.setEnabled(currentLessonIndex < currentLessons.size() - 1 || currentModuleIndex < moduleList.size() - 1);
    }

    private void updateAdapterSelection() {
        if (moduleAdapter != null) {
            moduleAdapter.setSelectedPosition(currentModuleIndex, currentLessonIndex);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.destroy();
    }
}
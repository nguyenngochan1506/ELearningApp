package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import vn.edu.hcmuaf.e_learningapp.R;

public class ChatOverviewActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Khởi tạo view
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        btnBack = findViewById(R.id.iv_back);

        // Thiết lập ViewPager2 với ViewPagerAdapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Liên kết TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Tất cả tin nhắn");
            } else {
                tab.setText("Khóa học");
            }
        }).attach();

        // Nút back
        btnBack.setOnClickListener(v -> finish());

        // Đặt tab mặc định là "Tất cả tin nhắn"
        tabLayout.getTabAt(0).select();
    }
}
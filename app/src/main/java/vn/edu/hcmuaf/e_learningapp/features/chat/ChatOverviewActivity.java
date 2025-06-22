package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.android.material.tabs.TabLayout;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatOverviewActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private View chatLayout, courseLayout;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        frameLayout = findViewById(R.id.frame_container);
        chatLayout = findViewById(R.id.layout_chats);
        courseLayout = findViewById(R.id.layout_courses);

        btnBack = findViewById(R.id.iv_back);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    chatLayout.setVisibility(View.VISIBLE);
                    courseLayout.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    chatLayout.setVisibility(View.GONE);
                    courseLayout.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


        // Đặt tab mặc định là "Tất cả tin nhắn"
        tabLayout.getTabAt(0).select();
    }
    public void openChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);

        startActivity(intent);
    }


}
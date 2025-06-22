package vn.edu.hcmuaf.e_learningapp.features.chat;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new ChatList();
        return new CourseListChat();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


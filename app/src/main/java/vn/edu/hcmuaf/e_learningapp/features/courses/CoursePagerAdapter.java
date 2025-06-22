package vn.edu.hcmuaf.e_learningapp.features.courses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.Serializable;

import vn.edu.hcmuaf.e_learningapp.features.courses.fragment.ContentFragment;
import vn.edu.hcmuaf.e_learningapp.features.courses.fragment.OverviewFragment;
import vn.edu.hcmuaf.e_learningapp.features.courses.fragment.QnAFragment;

public class CoursePagerAdapter extends FragmentStateAdapter {
    private final Course course;
    public CoursePagerAdapter(FragmentActivity fa, Course course) {
        super(fa);
        this.course = course;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                ContentFragment contentFragment = new ContentFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("modules", (Serializable) course.getModules());
                contentFragment.setArguments(bundle);
                return contentFragment;
            case 2:
                return new QnAFragment();
            case 0:
            default:
                return new OverviewFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

package vn.edu.hcmuaf.e_learningapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExamPagerAdapter extends FragmentPagerAdapter {

    public ExamPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new CreateExamFragment();
            case 1: return new ExamListFragment();
            case 2: return new GradingFragment();
            case 3: return new ResultsFragment();
            default: return new CreateExamFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Soạn đề";
            case 1: return "Danh sách";
            case 2: return "Đang chấm";
            case 3: return "Kết quả";
            default: return "";
        }
    }
}
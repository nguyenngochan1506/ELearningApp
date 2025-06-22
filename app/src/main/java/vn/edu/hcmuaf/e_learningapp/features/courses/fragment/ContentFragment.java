package vn.edu.hcmuaf.e_learningapp.features.courses.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.module.Module;
import vn.edu.hcmuaf.e_learningapp.features.module.ModuleAdapter;

public class ContentFragment extends Fragment {

    private RecyclerView rvModules;
    private List<Module> moduleList;

    public ContentFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(vn.edu.hcmuaf.e_learningapp.R.layout.fragment_content, container, false);
        rvModules = view.findViewById(R.id.rvModules);

        Bundle args = getArguments();
        if (args != null) {
            moduleList = (List<Module>) args.getSerializable("modules");
        }
        rvModules.setLayoutManager(new LinearLayoutManager(getContext()));
        rvModules.setAdapter(new ModuleAdapter(moduleList));

        return view;
    }

}

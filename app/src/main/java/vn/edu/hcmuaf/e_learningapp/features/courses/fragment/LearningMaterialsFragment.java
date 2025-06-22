package vn.edu.hcmuaf.e_learningapp.features.courses.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.R;
import vn.edu.hcmuaf.e_learningapp.features.file.FileResponseDto;
import androidx.cardview.widget.CardView;


public class LearningMaterialsFragment extends Fragment {

    private List<FileResponseDto> files;

    public LearningMaterialsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_materials, container, false);

        if (getArguments() != null) {
            files = (List<FileResponseDto>) getArguments().getSerializable("files");
        }

        LinearLayout containerLayout = view.findViewById(R.id.fileContainer);
        if (files != null && !files.isEmpty()) {
            for (FileResponseDto file : files) {
                View fileItem = createFileItem(file);
                containerLayout.addView(fileItem);
            }
        } else {
            TextView tv = new TextView(requireContext());
            tv.setText("Không có tài liệu.");
            tv.setTextSize(16f);
            containerLayout.addView(tv);
        }

        return view;
    }

    private View createFileItem(FileResponseDto file) {
        CardView cardView = new CardView(requireContext());
        cardView.setCardElevation(4f);
        cardView.setRadius(12f);
        cardView.setUseCompatPadding(true);

        LinearLayout itemLayout = new LinearLayout(requireContext());
        itemLayout.setOrientation(LinearLayout.VERTICAL);
        itemLayout.setPadding(16, 16, 16, 16);

        TextView tvName = new TextView(requireContext());
        tvName.setText("Tên: " + file.getFileName());
        tvName.setTextSize(16f);

        TextView tvLink = new TextView(requireContext());
        tvLink.setText(file.getFileUrl());
        tvLink.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        tvLink.setTextSize(14f);
        tvLink.setAutoLinkMask(Linkify.WEB_URLS);
        tvLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(file.getFileUrl()));
            startActivity(intent);
        });

        itemLayout.addView(tvName);
        itemLayout.addView(tvLink);

        cardView.addView(itemLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 16);
        cardView.setLayoutParams(params);

        return cardView;
    }

}

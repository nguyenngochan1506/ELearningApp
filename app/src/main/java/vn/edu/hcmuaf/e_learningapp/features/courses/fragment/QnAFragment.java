package vn.edu.hcmuaf.e_learningapp.features.courses.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.hcmuaf.e_learningapp.R;

public class QnAFragment extends Fragment {

    private EditText edtQuestion;
    private Button btnSubmit;

    public QnAFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qna, container, false);

        edtQuestion = view.findViewById(R.id.edtQuestion);
        btnSubmit = view.findViewById(R.id.btnSubmitQuestion);

        btnSubmit.setOnClickListener(v -> {
            String question = edtQuestion.getText().toString().trim();
            if (!question.isEmpty()) {
                Toast.makeText(getContext(), "Đã gửi câu hỏi: " + question, Toast.LENGTH_SHORT).show();
                edtQuestion.setText("");
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập câu hỏi!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

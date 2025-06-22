package vn.edu.hcmuaf.e_learningapp.features.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.hcmuaf.e_learningapp.R;

public class ForumActivity extends AppCompatActivity {

    private LinearLayout forumContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_activity);

        forumContainer = findViewById(R.id.forum_container);

        // Demo 3 bài viết
        for (int i = 0; i < 3; i++) {
            addPost("Nguyễn Văn A", "1 giờ trước", "Học Android như thế nào?",
                    "Mình đang học Android mà chưa biết bắt đầu từ đâu. Có ai gợi ý không?");
        }
    }

    private void addPost(String name, String time, String title, String content) {
        View postView = LayoutInflater.from(this).inflate(R.layout.item_forum_post, forumContainer, false);

        ((TextView) postView.findViewById(R.id.tv_name)).setText(name);
        ((TextView) postView.findViewById(R.id.tv_time)).setText(time);
        ((TextView) postView.findViewById(R.id.tv_title)).setText(title);
        ((TextView) postView.findViewById(R.id.tv_content)).setText(content);

        TextView tvLike = postView.findViewById(R.id.tv_like);
        ImageView ivLike = postView.findViewById(R.id.iv_like);
        View btnLike = postView.findViewById(R.id.btn_like);
        View btnComment = postView.findViewById(R.id.btn_comment);
        TextView tvCommentSection = postView.findViewById(R.id.tv_comment_section);

        final boolean[] isLiked = {false};

        btnLike.setOnClickListener(v -> {
            if (!isLiked[0]) {
                ivLike.setVisibility(View.GONE);
                tvLike.setText("❤️ Đã thích");
                isLiked[0] = true;
            } else {
                ivLike.setVisibility(View.VISIBLE);
                tvLike.setText("Thích");
                isLiked[0] = false;
            }
        });

        btnComment.setOnClickListener(v -> showCommentDialog(tvCommentSection));

        forumContainer.addView(postView);
    }

    private void showCommentDialog(TextView commentView) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_comment, null);
        EditText edtComment = dialogView.findViewById(R.id.edt_comment);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nhập bình luận")
                .setView(dialogView)
                .setPositiveButton("Gửi", (dialogInterface, i) -> {
                    String comment = edtComment.getText().toString().trim();
                    if (!comment.isEmpty()) {
                        commentView.setText("Bạn: " + comment);
                        commentView.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(this, "Vui lòng nhập nội dung bình luận", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Huỷ", null)
                .create();

        dialog.show();
    }
}
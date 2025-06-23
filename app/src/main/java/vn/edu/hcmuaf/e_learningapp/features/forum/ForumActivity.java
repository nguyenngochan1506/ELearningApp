package vn.edu.hcmuaf.e_learningapp.features.forum;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        findViewById(R.id.btn_add_post).setOnClickListener(v -> showCreatePostDialog());
    }

    private void addPost(String name, String time, String title, String content) {
        View postView = LayoutInflater.from(this).inflate(R.layout.item_forum_post, forumContainer, false);

        TextView tvName    = postView.findViewById(R.id.tv_name);
        TextView tvTime    = postView.findViewById(R.id.tv_time);
        TextView tvTitle   = postView.findViewById(R.id.tv_title);
        TextView tvContent = postView.findViewById(R.id.tv_content);
        TextView tvLike    = postView.findViewById(R.id.tv_like);
        ImageView ivLike   = postView.findViewById(R.id.iv_like);
        View btnLike       = postView.findViewById(R.id.btn_like);
        View btnComment    = postView.findViewById(R.id.btn_comment);
        View btnShare      = postView.findViewById(R.id.btn_share);
        TextView tvCommentSection = postView.findViewById(R.id.tv_comment_section);
        TextView tvSharedInfo     = postView.findViewById(R.id.tv_shared_info);

        tvName.setText(name);
        tvTime.setText(time);
        tvTitle.setText(title);
        tvContent.setText(content);

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

        btnShare.setOnClickListener(v -> {
            // Thời gian chia sẻ
            String currentTime = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).format(new Date());
            // Clone postView
            View newPost = LayoutInflater.from(this).inflate(R.layout.item_forum_post, forumContainer, false);

            TextView cName    = newPost.findViewById(R.id.tv_name);
            TextView cTime    = newPost.findViewById(R.id.tv_time);
            TextView cTitle   = newPost.findViewById(R.id.tv_title);
            TextView cContent = newPost.findViewById(R.id.tv_content);
            TextView cSharedInfo = newPost.findViewById(R.id.tv_shared_info);

            cName.setText(name);
            cTime.setText(currentTime);
            cTitle.setText(title);
            cContent.setText(content);
            cSharedInfo.setText("Bạn đã chia sẻ bài viết của " + name);
            cSharedInfo.setVisibility(View.VISIBLE);

            // Thêm lên đầu danh sách
            forumContainer.addView(newPost, 0);
            Toast.makeText(this, "Bạn đã chia sẻ vào diễn đàn", Toast.LENGTH_SHORT).show();
        });

        forumContainer.addView(postView);
    }

    private void showCommentDialog(TextView commentView) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_comment, null, false);
        EditText edtComment = dialogView.findViewById(R.id.edt_comment);

        new AlertDialog.Builder(this)
                .setTitle("Nhập bình luận")
                .setView(dialogView)
                .setPositiveButton("Gửi", (dialogInterface, which) -> {
                    String comment = edtComment.getText().toString().trim();
                    if (!comment.isEmpty()) {
                        commentView.setText("Bạn: " + comment);
                        commentView.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ForumActivity.this, "Vui lòng nhập nội dung bình luận", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Huỷ", null)
                .show();
    }

    private void showCreatePostDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_new_post, null, false);
        EditText edtTitle   = view.findViewById(R.id.edt_post_title);
        EditText edtContent = view.findViewById(R.id.edt_post_content);

        new AlertDialog.Builder(this)
                .setTitle("Tạo bài viết mới")
                .setView(view)
                .setPositiveButton("Đăng", (dialog, which) -> {
                    String title = edtTitle.getText().toString().trim();
                    String content = edtContent.getText().toString().trim();
                    if (!title.isEmpty() && !content.isEmpty()) {
                        String currentTime = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        addPost("Bạn", currentTime, title, content);
                        Toast.makeText(ForumActivity.this, "Đăng bài viết thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ForumActivity.this, "Vui lòng nhập đầy đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}

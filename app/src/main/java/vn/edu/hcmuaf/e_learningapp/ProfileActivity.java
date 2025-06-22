package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView ivUserAvatar;
    private TextView tvUserName;
    private Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_profile);
        } catch (Exception e) {
            Toast.makeText(this, "Error loading profile layout: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        btnBack = findViewById(R.id.btnBack);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        tvUserName = findViewById(R.id.tvUserName);
        btnEditProfile = findViewById(R.id.btnEditProfile);

        if (btnBack == null || ivUserAvatar == null || tvUserName == null || btnEditProfile == null) {
            Toast.makeText(this, "Error: One or more views not found", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvUserName.setText("Anna Avetisyan");

        btnBack.setOnClickListener(v -> onBackPressed());
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(ProfileActivity.this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
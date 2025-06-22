package vn.edu.hcmuaf.e_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.user.UserApi;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.UserResponse;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView ivUserAvatar;
    private TextView tvUserId, tvGender, tvDateOfBirth, tvPhone, tvEmail, tvLastLogin,tvFullName;
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
        tvUserId = findViewById(R.id.tvUserId);
        tvGender = findViewById(R.id.tvGender);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvLastLogin = findViewById(R.id.tvLastLogin);
        btnEditProfile = findViewById(R.id.btnLogout);
        tvFullName = findViewById(R.id.tvFullName);

        //lấy accesstoken
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String accessToken = prefs.getString("accessToken", null);

        UserApi userApi = ApiClient.getClient(accessToken).create(UserApi.class);
        userApi.getProfile().enqueue(new Callback<ResponseDto<UserResponse>>() {
            @Override
            public void onResponse(Call<ResponseDto<UserResponse>> call, Response<ResponseDto<UserResponse>> response) {
                ResponseDto<UserResponse> responseDto = response.body();
                if(responseDto == null){
                    Toast.makeText(ProfileActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserResponse data = responseDto.getData();
                tvUserId.setText("Id: "+String.valueOf(data.getId()));
                tvGender.setText("Giới tính: "+data.getGender().toString());
                tvDateOfBirth.setText("Ngày sinh: "+data.getDateOfBirth()+"");
                tvPhone.setText("Số điện thoại: "+data.getPhoneNumber()+"");
                tvEmail.setText("Email: "+data.getEmail()+"");
                tvLastLogin.setText("Lần đăng nhập cuối: "+data.getLastLogin()+"");
                tvFullName.setText("Họ và tên: "+data.getFullName()+"");

                Glide.with(ProfileActivity.this)
                        .load(data.getavatar())
                        .placeholder(R.drawable.ic_avatar_placeholder)
                        .error(R.drawable.ic_avatar_placeholder)
                        .circleCrop()
                        .into(ivUserAvatar);

            }

            @Override
            public void onFailure(Call<ResponseDto<UserResponse>> call, Throwable throwable) {
                Log.e("API_ERROR", "Lỗi kết nối: " + throwable.getMessage(), throwable);
                Toast.makeText(ProfileActivity.this, "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
        btnEditProfile.setOnClickListener(v -> {
            // Xoá token
            prefs.edit().clear().apply();
            // Chuyển về LoginActivity và xoá mọi activity trước đó
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Toast.makeText(ProfileActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        });
    }
}
package vn.edu.hcmuaf.e_learningapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.user.UserApi;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.AuthResponse;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.LoginRequest;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView signupText, forgotPasswordText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);
        forgotPasswordText = findViewById(R.id.forgotPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Đặt sự kiện cho quên mật khẩu (Có thể chưa xử lý trong demo này)
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thực hiện hành động khi quên mật khẩu, ví dụ như mở màn hình khôi phục mật khẩu
                Toast.makeText(LoginActivity.this, "Quên mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleLogin() {
        if (!isNetworkAvailable()) {
            Toast.makeText(LoginActivity.this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Gọi API đăng nhập
        UserApi userApi = ApiClient.getClientWithoutToken().create(UserApi.class);
        LoginRequest request = new LoginRequest(email, password, "ANDROID", "device_token");
        userApi.login(request).enqueue(new Callback<ResponseDto<AuthResponse>>() {
            @Override
            public void onResponse(Call<ResponseDto<AuthResponse>> call, Response<ResponseDto<AuthResponse>> response) {
                ResponseDto<AuthResponse> authResponse = response.body();
                if(authResponse == null){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }

                AuthResponse data = authResponse.getData();

//                 Lưu token vào SharedPreferences
                SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("accessToken", data.getAccessToken());
                editor.putString("refreshToken", data.getRefreshToken());
                editor.putLong("userId", data.getUserId());
                editor.apply();
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                 Chuyển sang MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseDto<AuthResponse>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}

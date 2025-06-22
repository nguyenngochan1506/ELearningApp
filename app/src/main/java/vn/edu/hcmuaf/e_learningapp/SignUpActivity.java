package vn.edu.hcmuaf.e_learningapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.e_learningapp.common.ResponseDto;
import vn.edu.hcmuaf.e_learningapp.core.network.ApiClient;
import vn.edu.hcmuaf.e_learningapp.features.user.UserApi;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.AuthResponse;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.RegisterRequest;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameInput, emailInput, passwordInput, confirmPasswordInput, phoneInput;
    private Spinner genderSpinner;
    private Button dobButton, signupButton;
    private CheckBox termsCheckbox;
    private TextView loginText;
    private ImageButton returnButton;
    private String selectedDob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        fullNameInput = findViewById(R.id.fullNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        phoneInput = findViewById(R.id.phoneInput);
        genderSpinner = findViewById(R.id.genderSpinner);
        dobButton = findViewById(R.id.dobButton);
        signupButton = findViewById(R.id.signupButton);
        termsCheckbox = findViewById(R.id.termsCheckbox);
        loginText = findViewById(R.id.loginText);
        returnButton = findViewById(R.id.returnIButton);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"MALE", "FEMALE", "OTHER"});
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        dobButton.setOnClickListener(v -> showDatePicker());

        signupButton.setOnClickListener(v -> handleSignUp());

        loginText.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        returnButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            selectedDob = String.format("%02d/%02d/%04d", day, month + 1, year);
            dobButton.setText(selectedDob);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void handleSignUp() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
            return;
        }

        String fullName = fullNameInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        if (fullName.isEmpty() || phone.isEmpty() || selectedDob.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!termsCheckbox.isChecked()) {
            Toast.makeText(this, "Bạn cần đồng ý với Điều khoản dịch vụ!", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest request = new RegisterRequest(fullName, gender, selectedDob, phone, email, password, "ANDROID", "device_token");

        UserApi userApi = ApiClient.getClientWithoutToken().create(UserApi.class);
        userApi.register(request).enqueue(new Callback<ResponseDto<Integer>>() {
            @Override
            public void onResponse(Call<ResponseDto<Integer>> call, Response<ResponseDto<Integer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else {
                    String errorMsg = "Đăng ký thất bại!";
                    try {
                        if (response.errorBody() != null) {
                            errorMsg = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        errorMsg = "Đăng ký thất bại (không đọc được lỗi)";
                    }
                    Toast.makeText(SignUpActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<Integer>> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}

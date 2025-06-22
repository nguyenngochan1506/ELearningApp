package vn.edu.hcmuaf.e_learningapp.features.user.dtos;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String email;
    private String password;
    private String platform;
    private String deviceToken;

    public LoginRequest(String email, String password, String platform, String deviceToken) {
        this.email = email;
        this.password = password;
        this.platform = platform;
        this.deviceToken = deviceToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}

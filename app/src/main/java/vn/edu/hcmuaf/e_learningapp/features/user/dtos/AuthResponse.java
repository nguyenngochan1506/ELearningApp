package vn.edu.hcmuaf.e_learningapp.features.user.dtos;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private long userId;

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userId=" + userId +
                '}';
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

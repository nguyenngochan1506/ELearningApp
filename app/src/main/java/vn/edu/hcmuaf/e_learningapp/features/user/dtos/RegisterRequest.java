package vn.edu.hcmuaf.e_learningapp.features.user.dtos;

public class RegisterRequest {
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;

    public RegisterRequest(String fullName, String gender, String dateOfBirth, String phoneNumber, String email, String password, String platform, String deviceToken) {
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}


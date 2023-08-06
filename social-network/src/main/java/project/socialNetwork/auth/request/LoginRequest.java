package project.socialNetwork.auth.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "not valid format")
    private String email;
    @NotEmpty(message = "email cannot be empty")
    @Size(min = 6)
    private String password;

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
}

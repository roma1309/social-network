package project.socialNetwork.auth.response;

import java.util.List;

public class JWTTokenResponse {
    private String token;
    private List<String> role;

    public JWTTokenResponse(String token, List<String> role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}

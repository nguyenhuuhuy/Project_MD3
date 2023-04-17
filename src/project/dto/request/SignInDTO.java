package project.dto.request;

public class SignInDTO {
    private String username;
    private String password;
    private boolean status;

    public SignInDTO() {
    }

    public SignInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignInDTO(String username, String password, boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignInDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

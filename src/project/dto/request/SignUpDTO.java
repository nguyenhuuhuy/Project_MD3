package project.dto.request;

import java.util.HashSet;
import java.util.Set;

public class SignUpDTO {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private boolean status;
    private Set<String> strRole = new HashSet<>();

    public SignUpDTO() {
    }

    public SignUpDTO(int id, String name, String userName, String email, String password,boolean status, Set<String> strRole) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.strRole = strRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<String> getStrRole() {
        return strRole;
    }

    public void setStrRole(Set<String> strRole) {
        this.strRole = strRole;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", strRole=" + strRole +
                '}';
    }
}

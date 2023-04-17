package project.model;


import project.model.oderList.Oder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private int phoneNumber;
    private boolean status;
    private List<Oder> listOder = new ArrayList<>(); // giỏ hàng
    private Set<Role> roles;

    public User(int id, String name, String userName, String email, String password,boolean status, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roleSet;

    }

    public User(int id, String name, String userName, String email, String password, int phoneNumber, boolean status, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.roles = roles;
    }

    public User(int id, String name, String userName, String email, String password, int phoneNumber, boolean status, List<Oder> listOder, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.listOder = listOder;
        this.roles = roles;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Oder> getListOder() {
        return listOder;
    }

    public void setListOder(List<Oder> listOder) {
        this.listOder = listOder;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", status=" + status +
                ", listOder=" + listOder +
                ", roles=" + roles +
                '}';
    }
}

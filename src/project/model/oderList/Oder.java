package project.model.oderList;

import project.model.User;
import project.model.food.FastFood;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Oder implements Serializable {
    private int id;
    private List<FastFood> ListOderUser = new ArrayList<>();
    private User user;
    private boolean status = false;

    public Oder() {
    }

    public Oder(int id, List<FastFood> listOderUser, User user, boolean status) {
        this.id = id;
        ListOderUser = listOderUser;
        this.user = user;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<FastFood> getListOderUser() {
        return ListOderUser;
    }

    public void setListOderUser(List<FastFood> listOderUser) {
        ListOderUser = listOderUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OderList{" +
                "id=" + id +
                ", ListOderUser=" + ListOderUser +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}

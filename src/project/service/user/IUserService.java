package project.service.user;

import project.model.User;

import project.model.food.FastFood;
import project.service.ICrud;

import java.util.List;


public interface IUserService extends ICrud<User> {
    boolean existedByUserName(String userName);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password,boolean status);
    User getCurentUser();

}

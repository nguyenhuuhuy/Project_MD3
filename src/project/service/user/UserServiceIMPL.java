package project.service.user;

import project.config.Config;
import project.model.User;
import project.model.food.FastFood;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);

    @Override
    public List<User> findAll() {
        return userList;
    }
    // lưu thông tin user
    @Override
    public void save(User user) {
        if (findById(user.getId()) != null) {
            int index = userList.indexOf(findById(user.getId()));
            userList.set(index, user);
        } else {
            userList.add(user);
        }
        new Config<User>().writeToFile(Config.PATH_USER, userList);

    }
    // sửa thông tin user
    public void synchronizeLoginUser(User user){
        List<User> loginUser = new ArrayList<>();
        loginUser.add(user);
        new Config<User>().writeToFile(Config.PATH_USER_LOGIN,loginUser);
    }
    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
            }
        }
        new Config<User>().writeToFile(Config.PATH_USER, userList);
    }

    @Override
    public boolean existedByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password,boolean status) {
        List<User> userLogin = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(username) && userList.get(i).getPassword().equals(password) && userList.get(i).isStatus()) {
                userLogin.add(userList.get(i));
                new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if(new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size()!=0){
            User user = new Config<User>().readFromFile(Config.PATH_USER_LOGIN).get(0);
            return user;
        }
        return null;
    }


}

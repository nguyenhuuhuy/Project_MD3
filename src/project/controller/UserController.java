package project.controller;

import project.config.Config;
import project.dto.request.SignInDTO;
import project.dto.request.SignUpDTO;
import project.dto.response.ResponseMessage;
import project.model.Role;
import project.model.RoleName;
import project.model.User;

import project.model.food.FastFood;
import project.service.flasFoodService.FastFoodServiceIMPL;

import project.service.role.RoleServiceIMPL;

import project.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private UserServiceIMPL userService = new UserServiceIMPL();
    private RoleServiceIMPL roleService = new RoleServiceIMPL();


    // hiển thị tất user
    public List<User> getListUser() {
        return userService.findAll();
    }
    // tìm kiếm user
    public User detailUser(int id) {
        return userService.findById(id);
    }

    // khóa tài khoản
    public void blockUser(User user) {
        userService.save(user);
    }

    // thăng cấp tài khoản
    public void changeRole(User user) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findByName(RoleName.PM));
        user.setRoles(roleSet);
        userService.save(user);
    }
    public void EditShopAndComment(User user){
        userService.save(user);
    }
    // đăng ký (Thêm)
    public ResponseMessage register(SignUpDTO sign) {
        if (userService.existedByUserName(sign.getUserName())) {
            return new ResponseMessage("user_existed");
        }
        if (userService.existedByEmail(sign.getEmail())) {
            return new ResponseMessage("email_existed");
        }
//        Set<String> strRole = sign.getStrRole();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findByName(RoleName.USER));
//        strRole.forEach(role->{
//            switch (role){
//                case "admin":
//                    roleSet.add(roleService.findByName(RoleName.ADMIN));
//                    break;
//                case "pm":
//                    roleSet.add(roleService.findByName(RoleName.PM));
//                    break;
//                default:
//                    roleSet.add(roleService.findByName(RoleName.USER));
//            }
//        });
        User user = new User(sign.getId(), sign.getName(), sign.getUserName(), sign.getEmail(), sign.getPassword(), sign.isStatus(), roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }


    // đăng nhập
    public ResponseMessage login(SignInDTO signInDTO) {
        if (userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword(),signInDTO.isStatus())) {
            return new ResponseMessage("login_success");
        } else {
            return new ResponseMessage("login_failed");
        }
    }

    // hiện thằng đăng nhập
    public User getUserLogin() {
        return userService.getCurentUser();
    }

    public void synchronizeLoginUser(User user) {
        userService.synchronizeLoginUser(user);
    }

    // sửa thông tin và tìm kiếm xem có trùng tên đăng nhập vs email không
    public boolean updateUser(User user) {
        List<User> listUser = getListUser();
        boolean check = true;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getId() != user.getId()) {
                if (listUser.get(i).getUserName().equalsIgnoreCase(user.getUserName()) || listUser.get(i).getEmail().equalsIgnoreCase(user.getEmail())) {
                    check = false;
                }
            }
        }
        if (check) {
            userService.save(user); // ghi đè vào vị trí cũ của user

            synchronizeLoginUser(user); // ghi đè vào phần đăng nhập
        }
        return check;
    }

    // đăng xuất
    public void logoutUser() {
        new Config<User>().clearFile(Config.PATH_USER_LOGIN);
    }
    // xóa user
    public void deleteUser(int id){
        userService.deleteById(id);
    }
}

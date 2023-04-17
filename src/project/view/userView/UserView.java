package project.view.userView;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.FastFoodController;
import project.controller.OderController;
import project.controller.UserController;
import project.dto.request.SignInDTO;
import project.dto.request.SignUpDTO;
import project.dto.response.ResponseMessage;
import project.menu.NavBar;
import project.menu.ProfileMenu;
import project.menu.UserMenuAdm;
import project.model.Role;
import project.model.RoleName;
import project.model.User;
import project.model.commentfood.CommentFood;
import project.model.food.FastFood;
import project.model.oderList.Oder;

import java.util.*;

public class UserView {
    OderController oderController = new OderController();
    List<Oder> userOder = oderController.showListOder();
    FastFoodController fastFoodController = new FastFoodController();
    List<FastFood> fastFoodList = fastFoodController.showListFood();
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    User user = userController.getUserLogin();

    // danh sách user ok
    public void showListUser() {
        System.out.println(" ☺.---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |          Tên Người dùng           |        Tên Đăng Nhập         |              Email             |        Mật khẩu        |          Số Điện Thoại           |      Trạng thái       |      Dạng Tài Khoản      |");
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf(" |  %-4s|    %-30s |    %-25s |    %-25s   |    %-16s    |     %-18s           |         %-10s    |   %-17s      |\n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getPhoneNumber(), userList.get(i).isStatus() ? "Mở" : "Khóa", userList.get(i).getRoles());
        }
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        Config.backUserMenu();
    }

    // khóa tài khoản user ok ---> chưa tô màu  ADMIN
    public void blockUser() {
        Set<Role> roleSet = user.getRoles();
        List<Role> roles = new ArrayList<>(roleSet);
        System.out.println(" ☺.---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |          Tên Người dùng           |        Tên Đăng Nhập         |              Email             |        Mật khẩu        |          Số Điện Thoại           |      Trạng thái       |      Dạng Tài Khoản      |");
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf(" |  %-4s|    %-30s |    %-25s |    %-25s   |    %-16s    |     %-18s           |         %-10s    |   %-17s      |\n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getPhoneNumber(), userList.get(i).isStatus() ? "Mở" : "Khóa", userList.get(i).getRoles());
        }
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        int targetId;
        while (true) {
            do {
                System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                        "|                                                      |" + ColorConfig.RESET);
                System.out.print("      Nhập tài khoản muốn khóa theo" + ColorConfig.RED + " id" + ColorConfig.RESET + ": ");
                targetId = Config.getInteger();
                System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                        "'------------------------------------------------------'" + ColorConfig.RESET);
                if (targetId < 1) {
                    System.out.println("        Xin đấy nhập đúng theo " + ColorConfig.RED + "id" + ColorConfig.RESET + " hộ mình cái !!! ");
                }
            } while (targetId < 1);
            userController.detailUser(targetId);
            Set<Role> roleSetUser = userController.detailUser(targetId).getRoles();
            List<Role> rolesUser = new ArrayList<>(roleSetUser);
            if (userController.detailUser(targetId) == null) {
                System.out.println(ColorConfig.RED + "Không tìm thấy tài khoản!!" + ColorConfig.RESET);
            } else if (rolesUser.get(0).getName() == roles.get(0).getName()) {
                System.out.println(ColorConfig.RED + "Tài khoản ngang cấp không được khóa !!!" + ColorConfig.RESET);
            } else if (userController.detailUser(targetId).getId() == 1) {
                System.out.println("Bạn không có quyền khóa tài khoản " + ColorConfig.RED + "ADMIN" + ColorConfig.RESET + ", " + ColorConfig.RED + "ADMIN" + ColorConfig.RESET + " đã biết bạn cố tình khóa tài khoản của họ," + ColorConfig.RED + " tk của bạn sắp bị bay màu" + ColorConfig.RESET + " !!!");
            } else if (userController.detailUser(targetId).getId() == user.getId()) {
                System.out.println("Đây là tài khoản của " + ColorConfig.RED + "Bạn " + ColorConfig.RESET + "đang đăng nhập bạn khóa chính bạn làm gì," + ColorConfig.BLUE + " xin đấy" + ColorConfig.RESET + "," + ColorConfig.RED + " quỳ !!! \uD83D\uDE12\uD83D\uDE12\uD83D\uDE12" + ColorConfig.RESET);
            } else {
                while (true) {
                    try {
                        System.out.println(ColorConfig.BLUE + "\n                Bạn có muốn Khóa tài khoản này không!!!" + ColorConfig.RESET);
                        System.out.println("           .---------------------------------------------.");
                        System.out.println("           |        1." + ColorConfig.GREEN + "có" + ColorConfig.RESET + "                " + "2." + ColorConfig.RED + "Không" + ColorConfig.RESET + "          |");
                        System.out.println("           '---------------------------------------------'");
                        int number;
                        while (true) {
                            do {
                                System.out.print("Mời Chọn: ");
                                number = Config.getInteger();
                                if (number > 2) {
                                    System.out.println("Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83D\uDE05\uD83D\uDE05\uD83D\uDE05");
                                }
                            } while (number > 2);
                            switch (number) {
                                case 1:
                                    User user = userController.detailUser(targetId);
                                    if (user.isStatus()) {
                                        user.setStatus(false);
                                        userController.blockUser(user);
                                        System.out.println(ColorConfig.BLUE + "Khóa Thành Công!!!" + ColorConfig.RESET);
                                        if (roles.get(0).getName() == RoleName.ADMIN) {
                                            new UserMenuAdm();
                                        } else if (roles.get(0).getName() == RoleName.PM) {
                                            new ProfileMenu();
                                        }
                                    } else {
                                        System.out.println(ColorConfig.BLUE + "\n   Tài khoản này đã bị " + ColorConfig.RED + "khóa" + ColorConfig.RESET + "," + ColorConfig.BLUE + " bạn có muốn " + ColorConfig.RESET + ColorConfig.RED + "Mở" + ColorConfig.RESET + ColorConfig.BLUE + " khóa tài khoản này không!!! " + ColorConfig.RESET);
                                        System.out.println("           .---------------------------------------------.");
                                        System.out.println("           |        1." + ColorConfig.GREEN + "có" + ColorConfig.RESET + "                " + "2." + ColorConfig.RED + "Không" + ColorConfig.RESET + "          |");
                                        System.out.println("           '---------------------------------------------'");
                                        int choice;
                                        while (true) {
                                            do {
                                                System.out.print("Mời chọn: ");
                                                choice = Config.getInteger();
                                                if (choice > 2) {
                                                    System.out.println("Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83D\uDE05\uD83D\uDE05\uD83D\uDE05");
                                                }
                                            } while (choice > 2);
                                            switch (choice) {
                                                case 1:
                                                    user.setStatus(true);
                                                    userController.blockUser(user);
                                                    System.out.println(ColorConfig.BLUE + "Mở khóa Thành Công!!! \uD83D\uDE0D\uD83D\uDE0D\uD83D\uDE0D" + ColorConfig.RESET);
                                                    if (roles.get(0).getName() == RoleName.ADMIN) {
                                                        new UserMenuAdm();
                                                    } else if (roles.get(0).getName() == RoleName.PM) {
                                                        new ProfileMenu();
                                                    }
                                                    break;
                                                case 2:
                                                    if (roles.get(0).getName() == RoleName.ADMIN) {
                                                        new UserMenuAdm();
                                                    } else if (roles.get(0).getName() == RoleName.PM) {
                                                        new ProfileMenu();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("Bạn nhập dưới " + ColorConfig.RED + "0 " + ColorConfig.RESET + "làm gì, quỳ xin đạn đấy đọc lại đề bài đi !!! \uD83D\uDE11\uD83D\uDE11\uD83D\uDE11");
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    new UserMenuAdm();
                                    break;
                                default:
                                    System.out.println("Bạn nhập dưới " + ColorConfig.RED + "0 " + ColorConfig.RESET + "làm gì, quỳ xin đạn đấy đọc lại đề bài đi !!! \uD83D\uDE11\uD83D\uDE11\uD83D\uDE11");
                            }
                        }
                    } catch (NumberFormatException err) {
                        System.out.println(err);
                    }
                }
            }
        }
    }

    // thăng cấp cho role  ok ----> chưa tô màu ADMIN
    public void changeRole() {
        System.out.println(" ☺.---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |          Tên Người dùng           |        Tên Đăng Nhập         |              Email             |        Mật khẩu        |          Số Điện Thoại           |      Trạng thái       |      Dạng Tài Khoản      |");
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf(" |  %-4s|    %-30s |    %-25s |    %-25s   |    %-16s    |     %-18s           |         %-10s    |   %-17s      |\n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getPhoneNumber(), userList.get(i).isStatus() ? "Mở" : "Khóa", userList.get(i).getRoles());
        }
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        int targetId;
        while (true) {
            do {
                System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                        "|                                                      |" + ColorConfig.RESET);
                System.out.print("  Nhập id tài khoản muốn nâng cấp lên " + ColorConfig.RED + "Vip" + ColorConfig.RESET + ": ");
                targetId = Config.getInteger();
                System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                        "'------------------------------------------------------'" + ColorConfig.RESET);
                if (targetId < 1) {
                    System.out.println("        Xin đấy nhập đúng theo " + ColorConfig.RED + "id" + ColorConfig.RESET + " hộ mình cái !!! ");
                }
            } while (targetId < 1);

            if (userController.detailUser(targetId) == null) {
                System.out.println("           không tìm thấy " + ColorConfig.RED + "user" + ColorConfig.RESET + " !!!");
            } else {
                System.out.println("\n            Bạn chắc có muốn nâp cấp tài khoản lên " + ColorConfig.RED + "PM" + ColorConfig.RESET + " !!!");
                System.out.println("           .---------------------------------------------.");
                System.out.println("           |        1." + ColorConfig.GREEN + "có" + ColorConfig.RESET + "                " + "2." + ColorConfig.RED + "Không" + ColorConfig.RESET + "          |");
                System.out.println("           '---------------------------------------------'");
                while (true) {
                    try {

                        int numberUp;
                        do {
                            System.out.print("Mời chọn: ");
                            numberUp = Config.getInteger();
                            if (numberUp > 2) {
                                System.out.println("Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83D\uDE05\uD83D\uDE05\uD83D\uDE05");
                            }
                        } while (numberUp > 2);
                        switch (numberUp) {
                            case 1:
                                User user = userController.detailUser(targetId);
                                Set<Role> rolesUser = user.getRoles();
                                List<Role> roleList = new ArrayList<>(rolesUser);
                                if (roleList.get(0).getName() == RoleName.PM) {
                                    System.out.println("Tài khoản này đã được nâng cấp lên " + ColorConfig.RED + "Vip " + ColorConfig.RESET);
                                    Config.backUserMenu();
                                } else if (roleList.get(0).getName() == RoleName.ADMIN) {
                                    System.out.println("Tài khoản của bạn là " + ColorConfig.RED + "VipPro" + ColorConfig.RESET + " nhất rồi không thể nâng cấp thêm được nữa !!!");
                                } else {
                                    userController.changeRole(user);
                                    System.out.println(ColorConfig.BLUE + "              Thăng cấp thành công!!!" + ColorConfig.RESET);
                                }
                                Config.backUserMenu();
                            case 2:
                                Config.backUserMenu();
                            default:
                                System.out.println(" Nhập " + ColorConfig.RED + "1 " + ColorConfig.RESET + "và " + ColorConfig.RED + "2 " + ColorConfig.RESET + "thôi bạn ơi, xin đó nhập lại đi !!!\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");
                        }
                    } catch (NumberFormatException err) {
                        System.out.println(err);
                    }
                }
            }
        }
    }

    // đăng ký (chưa validate) ----> USER
    public void formRegister() {
        int id;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        boolean status = true;
        String name;
        boolean checkName;
        do {
            System.out.print("Nhập Tên Hiển Thị: ");
            name = Config.getString();
            if (name.trim().equals("")) {
                System.out.print(ColorConfig.RED + "Không được để trống." + "\n" + ColorConfig.RESET);
                checkName = false;
            } else if (name.length() < 5 || name.length() > 40) {
                System.out.print(ColorConfig.RED + "Tên học sinh không được dưới 5 ký tự.\n" + ColorConfig.RESET);
                checkName = false;
            } else if (!name.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                System.out.print(ColorConfig.RED + "Không được nhập số.\n" + ColorConfig.RESET);
                checkName = false;
            } else {
                checkName = true;
            }
        } while (!checkName);
        String userName;
        do {
            System.out.print("Nhập Tên Đăng Nhập: ");
            userName = Config.getString();
            if (!Config.validateUserName(userName)) {
                System.out.println("cccccccc");
            }
        } while (!Config.validateUserName(userName));
        String email;
        while (true) {
            System.out.print("Nhập email mới: ");
            email = Config.getString();
            if (email.matches("^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
                break;
            }
            System.out.println("Nhập sai định dạng vui lòng nhập lại !!!");
        }
        System.out.print("Nhập mật khẩu: ");
        String password = Config.getString();
        String role = "user";
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO sign = new SignUpDTO(id, name, userName, email, password, status, strRole);
        while (true) {
            ResponseMessage responseMessage = userController.register(sign);
            if (responseMessage.getMessage().equals("user_existed")) {
                System.err.println("Tài Khoản đã tồn tại!");
                System.out.print("Nhập lại tên đăng nhập: ");
                userName = Config.getString();
                sign.setUserName(userName);

            } else if (responseMessage.getMessage().equals("email_existed")) {
                System.err.println("email đã tồn lại!");
                System.out.print("Nhập lại email: ");
                email = Config.getString();
                sign = new SignUpDTO(id, name, userName, email, password, status, strRole);
            } else if (responseMessage.getMessage().equals("create_success")) {
                new NavBar();
                break;
            }
        }
    }

    //đăng nhập (chưa vadidate) ----> USER / PM /ADMIN
    public void formLogin() {
        System.out.println("                                      .--------------------------------------------------------------------.");
        System.out.println("                                      |                                                                    |");
        System.out.println("                                      '---------------------------" + ColorConfig.BLUE + "  FORM LOGIN  " + ColorConfig.RESET + "---------------------------'");
        System.out.print("Nhập Tên Đăng Nhập: ");
        String username = Config.getString();
        System.out.print("Nhập Mật Khẩu: ");
        String password = Config.getString();
        boolean statusLogin = true;
        SignInDTO signInDTO = new SignInDTO(username, password, statusLogin);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if (responseMessage.getMessage().equals("login_failed")) {
                System.out.println("Đăng nhập thất bại!! Mời Nhập lại!!!");
                System.out.print("Nhập lại Tên Đăng Nhập: ");
                username = Config.getString();
                System.out.print("Nhập Lại Mật Khẩu: ");
                password = Config.getString();
                signInDTO.setUsername(username);
                signInDTO.setPassword(password);
            } else {
                user = userController.getUserLogin();
                System.out.println("                                        ----------------------------" + ColorConfig.RED + " ĐĂNG NHẬP THÀNH CÔNG!" + ColorConfig.RESET + "  ----------------------------");
                new NavBar();
                break;
            }
        }
    }

    // đổi mật khẩu    ----> USER / PM / ADMIN
    public void changePassword() {
        User user = userController.getUserLogin();
        System.out.println(" .-----------------------------------------------------------------.");
        System.out.println(" |        " + ColorConfig.GREEN + " Mật khẩu hiện tại" + ColorConfig.RESET + "        |       " + ColorConfig.BLUE + "Dạng tài khoản" + ColorConfig.RESET + "         |");
        System.out.println(" '-----------------------------------------------------------------'");
        System.out.printf(" |            %-15s       |            %-5s            |\n", user.getPassword(), user.getRoles());
        System.out.println(" '-----------------------------------------------------------------'\n");
        System.out.println(ColorConfig.BLUE + "                Bạn có muốn thay đổi mật khẩu không!!!" + ColorConfig.RESET);
        System.out.println("           .---------------------------------------------.");
        System.out.println("           |        1." + ColorConfig.GREEN + "có" + ColorConfig.RESET + "                " + "2." + ColorConfig.RED + "Không" + ColorConfig.RESET + "          |");
        System.out.println("           '---------------------------------------------'");

        int deleteUser;
        while (true) {
            do {
                System.out.print("Mời chọn số: ");
                deleteUser = Config.getInteger();
                if (deleteUser > 2) {
                    System.out.println("Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83D\uDE05\uD83D\uDE05\uD83D\uDE05");
                }
            } while (deleteUser > 2);
            switch (deleteUser) {
                case 1:
                    while (true) {
                        System.out.print("Nhập mật khẩu cũ: ");
                        String password = Config.getString();
                        if (!user.getPassword().equals(password)) {
                            System.out.println(ColorConfig.RED + "Mật khẩu không đúng!!" + ColorConfig.RESET);
                        } else {
                            break;
                        }
                    }
                    System.out.print("Nhập mật khẩu mới: ");
                    String newPassword = Config.getString();
                    System.out.print("Nhập lại mật khẩu mới: ");
                    String confirmPassword = Config.getString();
                    while (true) {
                        if (!newPassword.equals(confirmPassword)) {
                            System.out.println(ColorConfig.RED + "Nhập lại mật khẩu mới không khớp!" + ColorConfig.RESET);
                            System.out.print("Nhập lại mật khẩu mới lần nữa: ");
                            confirmPassword = Config.getString();
                        } else {
                            break;
                        }
                    }
                    user.setPassword(newPassword);
                    boolean updatePassword = userController.updateUser(user);
                    if (updatePassword) {
                        System.out.println(ColorConfig.BLUE + " Đổi mật khẩu thành công!!!" + ColorConfig.RESET);
                        new ProfileMenu();
                        break;
                    }
                    break;
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Bạn nhập dưới " + ColorConfig.RED + "0 " + ColorConfig.RESET + "làm gì, quỳ xin đạn đấy đọc lại đề bài đi !!! \uD83D\uDE11\uD83D\uDE11\uD83D\uDE11");
            }
        }

    }

    // cập nhập thông tin người đăng nhập ok  ----> USER / PM / ADMIN
    public void UpdateUser() {
        User user = userController.getUserLogin();
        System.out.println(" .------------------------------------------------------------------------------------------------------------------------------------------------------.");
        System.out.println(" |         Tên Người dùng           |        Tên Đăng Nhập         |          Email             |        Mật khẩu        |        Số Điện Thoại         |");
        System.out.println(" '------------------------------------------------------------------------------------------------------------------------------------------------------'");
        System.out.printf(" |        %-26s|         %-20s |    %-24s|    %-16s    |     %-18s       |\n", user.getName(), user.getUserName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
        System.out.println(" '------------------------------------------------------------------------------------------------------------------------------------------------------'\n");
        System.out.println(ColorConfig.BLUE + "                                                              Bạn có muốn sửa thông tin không." + ColorConfig.RESET);
        System.out.println("                                                      .---------------------------------------------.");
        System.out.println("                                                      |        1." + ColorConfig.GREEN + "có" + ColorConfig.RESET + "                " + "2." + ColorConfig.RED + "Không" + ColorConfig.RESET + "          |");
        System.out.println("                                                      '---------------------------------------------'");
        int updateUser;
        while (true) {
            do {
                System.out.print("Mời chọn số: ");
                updateUser = Config.getInteger();
            } while (updateUser > 2);
            switch (updateUser) {
                case 1:
                    while (true) {
                        String name;
                        boolean checkName;
                        do {
                            System.out.print("Nhập tên mới: ");
                            name = Config.getString();
                            if (name.trim().equals("")) {
                                System.out.print(ColorConfig.RED + "Không được để trống !!!" + "\n" + ColorConfig.RESET);
                                checkName = false;
                            } else if (name.length() < 4 || name.length() > 40) {
                                System.out.print(ColorConfig.RED + "Tên phải trong khoảng 5>40 ký tự !!!\n" + ColorConfig.RESET);
                                checkName = false;
                            } else if (!name.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                                System.out.print(ColorConfig.RED + "Không được nhập số.\n" + ColorConfig.RESET);
                                checkName = false;
                            } else {
                                checkName = true;
                            }
                        } while (!checkName);
                        String userName;
                        boolean checkUserName;
                        do {
                            System.out.print("Nhập tên đăng nhập mới: ");
                            userName = Config.getString();
                            if (userName.trim().equals("")) {
                                System.out.print(ColorConfig.RED + "Không được để trống !!!" + "\n" + ColorConfig.RESET);
                                checkUserName = false;
                            } else if (userName.length() < 4 || userName.length() > 20) {
                                System.out.print(ColorConfig.RED + "Tên phải trong khoảng 5>20 ký tự !!!\n" + ColorConfig.RESET);
                                checkUserName = false;
                            } else {
                                checkUserName = true;
                            }
                        } while (!checkUserName);
                        String email;
                        while (true) {
                            System.out.print("Nhập email mới: ");
                            email = Config.getString();
                            if (email.matches("^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
                                break;
                            }
                            System.out.println("Nhập sai định dạng vui lòng nhập lại !!!");
                        }
                        String numberPhone;
                        do {
                            System.out.print("Nhập số điện thoại: ");
                            numberPhone = Config.getString();
                        } while (!numberPhone.matches("^0\\d{9,10}$"));
                        user.setName(name);
                        user.setUserName(userName);
                        user.setEmail(email);
                        user.setPhoneNumber(Integer.parseInt(numberPhone));
                        boolean updateStatus = userController.updateUser(user);
                        if (updateStatus) {
                            System.out.println("Sửa Thành Công !!!");
                            break;
                        }
                        System.out.println("trùng tên đăng nhập hoặc email vui lòng nhập lại");
                    }
                    new ProfileMenu();
                    break;
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Chỉ được chọn 1 hoặc 2 !!!!");
            }
        }
    }

    // xóa user
    public void deleteByUser() {
        System.out.println(" ☺.---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |          Tên Người dùng           |        Tên Đăng Nhập         |              Email             |        Mật khẩu        |          Số Điện Thoại           |      Trạng thái       |      Dạng Tài Khoản      |");
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf(" |  %-4s|    %-30s |    %-25s |    %-25s   |    %-16s    |     %-18s           |         %-10s    |   %-17s      |\n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getPhoneNumber(), userList.get(i).isStatus(), userList.get(i).getRoles());
        }
        System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'");
        while (true) {
            int number;
            do {
                System.out.print("Nhập " + ColorConfig.RED + "id " + ColorConfig.RESET + "tài khoản muốn xóa: ");
                number = Config.getInteger();
                if (number < 3) {
                    System.out.println("                      Bạn nhập dưới " + ColorConfig.RED + "3 " + ColorConfig.RESET + "rồi !!!");
                }
            } while (number < 3);
            if (userController.detailUser(number) == null) {
                System.out.println(ColorConfig.RED + "Không tìm thấy user !!!" + ColorConfig.RESET);
            } else {
                System.out.println(ColorConfig.BLUE + "                                     Bạn có muốn xóa user không." + ColorConfig.RESET);
                System.out.println("            .----------------------------------------------------------------------------------.");
                System.out.println("            |" + "           1." + ColorConfig.BLUE + " Có." + ColorConfig.RESET + "                                    2. " + ColorConfig.RED + "Không" + ColorConfig.RESET + "                     |");
                System.out.println("            '----------------------------------------------------------------------------------'");
                int choice;
                while (true) {
                    do {
                        System.out.print("Mời chọn số: ");
                        choice = Config.getInteger();
                    } while (choice > 2);
                    switch (choice) {
                        case 1:
                            userController.deleteUser(number);
                            System.out.println(ColorConfig.GREEN + "----> Xóa thành công !!!" + ColorConfig.RESET);
                            Config.backUserMenu();
                            break;
                        case 2:
                            Config.backUserMenu();
                            break;
                        default:
                            System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                    }
                }
            }
        }
    }

    // like sp
    public void likeFastFood() {
        System.out.println(ColorConfig.BLUE + "                             \uD83C\uDF39——————————————————————  SẢN PHẨM HIỆN ĐANG BÁN  ——————————————————————\uD83C\uDF39" + ColorConfig.RESET);
        System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < fastFoodList.size(); i++) {
            if (fastFoodList.get(i).checkDay()) {
                System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getPrice(), fastFoodList.get(i).editExpirationDate(), fastFoodList.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(i).getQuantity(), fastFoodList.get(i).getCategory().getCategoryName());
            }
        }
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        System.out.println("            .------------------------------------------------------------------------------------------------------.");
        System.out.println("            | " + ColorConfig.GREEN + "        1. Xem chi tiết sản phẩm theo " + ColorConfig.RESET + ColorConfig.RED + "id " + ColorConfig.RESET + ColorConfig.GREEN + "của sản phẩm." + ColorConfig.RESET + ColorConfig.RED + "                        2. Thoát. " + ColorConfig.RESET + "             |");
        System.out.println("            '------------------------------------------------------------------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("Mời chọn số: ");
                choice = Config.getInteger();
                if (choice > 2) {
                    System.out.println("          Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                }
            } while (choice > 2);
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Nhập " + ColorConfig.RED + "id " + ColorConfig.RESET + "sản phẩm muốn xem thông tin chi tiết: ");
                        int targetId = Config.getInteger();
                        if (fastFoodController.detailFastFood(targetId) == null) {
                            System.out.println(ColorConfig.RED + "Không tìm thấy sản phẩm !!!" + ColorConfig.RESET);
                        } else {
                            for (int i = 0; i < fastFoodList.size(); i++) {
                                if (fastFoodList.get(i).getId() == targetId && !fastFoodList.get(i).checkDay()) {
                                    System.out.println(ColorConfig.RED + "Sản phẩm bạn tìm hiện tại không khả dụng vì đã quá hạn sử dụng mời chọn lại !!!");
                                } else if (fastFoodList.get(i).getId() == targetId && fastFoodList.get(i).checkDay()) {
                                    System.out.println(ColorConfig.BLUE + "                             \uD83E\uDDE1——————————————————————  CHI TIẾT CỦA SẢN PHẨM " + fastFoodList.get(i).getFoodName() + "  ——————————————————————\uD83E\uDDE1" + ColorConfig.RESET);
                                    System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getPrice(), fastFoodList.get(i).editExpirationDate(), fastFoodList.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(i).getQuantity(), fastFoodList.get(i).getCategory().getCategoryName());
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.println(" ☺.------------------------------------------------.☺");
                                    System.out.println(" |                     LIKE                          |");
                                    System.out.println(" '---------------------------------------------------'");
                                    System.out.printf(" |                      %-10s                   |\n", fastFoodList.get(i).getLikeUser().size());
                                    System.out.println(" '---------------------------------------------------'");
                                    List<CommentFood> commentFoodList = fastFoodList.get(i).getCommentFoodList();  // phần commnet
                                    System.out.println(" ☺.----------------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" | " + ColorConfig.BLUE + "       Người đã đánh giá sản phẩm         " + ColorConfig.RESET + " |   " + ColorConfig.GREEN_ITALIC + "                                  COMMENT                                              " + ColorConfig.RESET + "    |");
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                    for (int k = 0; k < commentFoodList.size(); k++) {
                                        System.out.printf(" |         %-30s     |                      %-60s            |\n", commentFoodList.get(k).getUser().getName(), commentFoodList.get(k).getComment());
                                    }
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");

                                    System.out.println("            .----------------------------------------------------------------------------------------------------------------------------.");
                                    System.out.println("            | " + ColorConfig.GREEN + "        1. Thích sản phẩm. " + ColorConfig.RESET + ColorConfig.BLUE + "                        2. Đánh giá sản phẩm. " + ColorConfig.RESET + ColorConfig.RED + "                 3.Xem chi tiết sản phẩm khác." + ColorConfig.RESET + "    |");
                                    System.out.println("            '----------------------------------------------------------------------------------------------------------------------------'");

                                    int choiceLike;
                                    while (true) {
                                        do {
                                            System.out.print("Mời chọn số: ");
                                            choiceLike = Config.getInteger();
                                            if (choiceLike > 3) {
                                                System.out.println("          Nhập quá " + ColorConfig.RED + "3 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                                            }
                                        } while (choiceLike > 3);
                                        switch (choiceLike) {
                                            case 1:
                                                User user = userController.getUserLogin(); // lấy đối tượng đang đăng nhập
                                                FastFood fastFood = fastFoodList.get(i); // lấy sản phẩm đang xem
                                                List<User> fastFoodLikeUser = fastFood.getLikeUser(); // lấy list user trong sản phẩm đang xem
                                                if (fastFoodLikeUser.isEmpty()) {
                                                    fastFoodLikeUser.add(user); // rỗng thêm
                                                    fastFoodController.createFastFood(fastFood); // lưu lại data
                                                    System.out.println(ColorConfig.GREEN_BOLD + "Like thành công !!!" + ColorConfig.RESET);
                                                    likeFastFood();
                                                } else {
                                                    boolean checkLike = true;
                                                    for (int j = 0; j < fastFoodLikeUser.size(); j++) {
                                                        if (fastFoodLikeUser.get(j).getId() == user.getId()) {
                                                            checkLike = true;
                                                            System.out.println(ColorConfig.RED + "bạn đã like sản phẩm này rồi !!!" + ColorConfig.RESET);
                                                            break;
                                                        } else {
                                                            checkLike = false;
                                                        }
                                                    }
                                                    if (!checkLike) {
                                                        fastFoodLikeUser.add(user);
                                                        fastFoodController.createFastFood(fastFood);
                                                        System.out.println(ColorConfig.BLUE + "Like thành công !!!" + ColorConfig.RESET);
                                                        likeFastFood();
                                                    }
                                                }
                                                break;
                                            case 2:
                                                System.out.println(" ☺.----------------------------------------------------------------------------------------------------------------------------------------.☺");
                                                System.out.println(" |                                                       COMMENT                                                                             |");
                                                System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                                String comment;
                                                boolean checkCommnet;
                                                do {
                                                    System.out.print("        Nhập cảm nghĩ của bạn: ");
                                                    comment = Config.getString();
                                                    if (comment.trim().equals("")) {
                                                        System.out.print(ColorConfig.RED + "Không được để trống." + "\n" + ColorConfig.RESET);
                                                        checkCommnet = false;
                                                    } else {
                                                        checkCommnet = true;
                                                    }
                                                } while (!checkCommnet);
                                                System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                                User commnetUser = userController.getUserLogin();  // có thằng đăng nhập viết commnet;
                                                FastFood commemtFastFood = fastFoodList.get(i);    // có thằn sp đang xem
                                                List<CommentFood> fastFoodListComment = commemtFastFood.getCommentFoodList();
                                                int index;
                                                if (fastFoodListComment.isEmpty()) {
                                                    index = 1;
                                                } else {
                                                    index = fastFoodListComment.get(fastFoodListComment.size() - 1).getCommentId() + 1;
                                                }
                                                CommentFood commentFood = new CommentFood(index, commnetUser, comment);
                                                fastFoodListComment.add(commentFood);
                                                fastFoodController.createFastFood(commemtFastFood); // lưu lại comment của thằng user vào data food
                                                likeFastFood();
                                                break;
                                            case 3:
                                                likeFastFood();
                                                break;
                                            default:
                                                System.out.println(ColorConfig.RED + "chọn sai định dạng chỉ được nhập từ 1 ----> 3 mời chọn lại" + ColorConfig.RESET);
                                        }
                                    }
                                }
                            }
                        }
                    }
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println(ColorConfig.RED + "                                        Chỉ được chọn 1 và 2 mời chọn lại !!!" + ColorConfig.RESET);
            }
        }
    }

    // đăng xuất
    public void logout() {
        System.out.println("\n                                       ------------------------- " + ColorConfig.RED + "  ĐĂNG XUẤT THÀNH CÔNG !!!  " + ColorConfig.RESET + "-------------------------");
        userController.logoutUser();
        new NavBar();
    }

    // shop mua hàng -----> USER    lol nó mất time chưa css
    public void shoppingCart() {
        System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < fastFoodController.showListFood().size(); i++) {
            if (fastFoodList.get(i).checkDay()) {
                System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getPrice(), fastFoodList.get(i).editExpirationDate(), fastFoodList.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(i).getQuantity(), fastFoodList.get(i).getCategory().getCategoryName());
            }
        }
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        System.out.println("            .----------------------------------------------------------------------------------.");
        System.out.println("            | " + ColorConfig.GREEN + "        1. Thêm sản phẩm vào giỏ hàng" + ColorConfig.RESET + ColorConfig.RED + "                     2. Thoát. " + ColorConfig.RESET + "             |");
        System.out.println("            '----------------------------------------------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("nhập lựa chọn: ");
                choice = Config.getInteger();
            } while (choice > 2);
            switch (choice) {
                case 1:
                    addFoodToOder();
                    break;
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Nhập 1 hoặc 2 !!!");
            }
        }
    }


    public void addFoodToOder() {
        System.out.println("            .----------------------------------------------------------------------------------.");
        System.out.println("            |" + "        1." + ColorConfig.BLUE + " Chọn sản phẩm theo" + ColorConfig.RESET + ColorConfig.RED + " id" + ColorConfig.RESET + "                 2. " + ColorConfig.RED + "Quay lại" + ColorConfig.RESET + ".                     |");
        System.out.println("            '----------------------------------------------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("Mời chọn 1 hoặc 2: ");
                choice = Config.getInteger();
                if (choice > 2) {
                    System.out.println("Nhập vượt quá 2 !!!");
                }
            } while (choice > 2);
            switch (choice) {
                case 1:
                    System.out.print("Nhập " + ColorConfig.RED + "id" + ColorConfig.RESET + " của sản phẩm: ");
                    int id = Config.getInteger();
                    List<FastFood> updateFastFood = new ArrayList<>();
                    if (user.getListOder().size() > 0) {
                        Oder oder = user.getListOder().get(user.getListOder().size() - 1);
                        if (oder.isStatus()) {
                            int idOder = user.getListOder().get(user.getListOder().size() - 1).getId() + 1;
                            Oder newOder = new Oder(idOder, updateFastFood, user, false);
                            addFoodToCart(newOder, id);
                            user.getListOder().add(newOder);
                            userController.updateUser(user);
                            oderController.saveListOder(newOder);
                            System.out.println("Thêm sản phẩm thành công");
                            shoppingCart();
                        } else {
                            addFoodToCart(oder, id);
                            List<Oder> oderList = user.getListOder();
                            oderList.remove(oderList.size() - 1);
                            oderList.add(oder);
                            userController.updateUser(user);
                            oderController.updateOder(oder);
                            System.out.println("Thêm sản phẩm Thành Công !!!");
                            shoppingCart();
                        }
                    } else {
                        Oder newOder = new Oder(1, updateFastFood, user, false);
                        addFoodToCart(newOder, id);
                        user.getListOder().add(newOder);
                        userController.updateUser(user);
                        System.out.println("Thêm Thành Công ...");
                        shoppingCart();
                    }
                case 2:
                    new ProfileMenu();
                default:
                    System.out.println("Bạn đã nhập quá 2");
            }
        }
    }

    public void addFoodToCart(Oder oder, int id) {
        boolean checkFood = false;
        for (int i = 0; i < fastFoodController.showListFood().size(); i++) {
            if (fastFoodController.showListFood().get(i).getId() == id) {
                if (fastFoodController.showListFood().get(i).getQuantity() > 0 && fastFoodController.showListFood().get(i).checkDay()) {
                    checkFood = true;
                    break;
                }
            }
        }

        if (checkFood) {
            boolean checkList = false;
            for (int i = 0; i < oder.getListOderUser().size(); i++) {
                if (oder.getListOderUser().get(i).getId() == id) {
                    oder.getListOderUser().get(i).setQuantity(oder.getListOderUser().get(i).getQuantity() + 1);
                    checkList = true;
                    break;
                }
            }
            FastFood updateFastFood = fastFoodController.detailFastFood(id);
            FastFood addFastFood = new FastFood(updateFastFood.getId(), updateFastFood.getFoodName(), updateFastFood.getPrice(), 1, updateFastFood.getExpirationDate(), updateFastFood.getCategory(), updateFastFood.getLikeUser(), updateFastFood.getCommentFoodList());
            updateFastFood.setQuantity(updateFastFood.getQuantity() - 1);
            fastFoodController.updateFastFood(updateFastFood);
            if (!checkList) {
                oder.getListOderUser().add(addFastFood);
            }
        } else {
            System.out.println(ColorConfig.RED + "                                             Không tìm thấy sản phẩm hoặc sản phẩm đã bán hết !!!" + ColorConfig.RESET);
            shoppingCart();
        }
    }

    public void detailHistory() {
        List<Oder> oderUser = user.getListOder();
        System.out.println(ColorConfig.BLUE + "\n                                  \uD83C\uDF39——————————————————————  LỊCH SỬ MUA HÀNG  ——————————————————————\uD83C\uDF39\n" + ColorConfig.RESET);
        for (int i = 0; i < oderUser.size(); i++) {
            System.out.println(ColorConfig.BLUE + "                                                          Hóa đơn đặt hàng thứ " + ColorConfig.RESET + ColorConfig.RED + oderUser.get(i).getId() + ColorConfig.RESET);
            System.out.println(" ☺.-------------------------------------------------------------------------------------------------------------------------------------.☺");
            System.out.println(" |             Tên Sản phẩm             |         Giá sản phẩm         |   Số Lượng   |      Loại sản phẩm      |     trạng thái          |");
            System.out.println(" '----------------------------------------------------------------------------------------------------------------------------------------'");
            List<FastFood> fastFoodOder = oderUser.get(i).getListOderUser();
            int total = 0;
            for (int j = 0; j < fastFoodOder.size(); j++) {
                System.out.printf(" |               %-11s            |           %-10s$        |      %-5s   |     %-16s    |    %-16s     |\n", fastFoodOder.get(j).getFoodName(), fastFoodOder.get(j).getPrice(), fastFoodOder.get(j).getQuantity(), fastFoodOder.get(j).getCategory().getCategoryName(), oderUser.get(i).isStatus() ? "Đã chuyển hàng" : "Đang chuyển hàng");
                total += fastFoodOder.get(j).getPrice() * fastFoodOder.get(j).getQuantity();
            }
            System.out.println(" '----------------------------------------------------------------------------------------------------------------------------------------'\n");
            System.out.println("                                                " + ColorConfig.RED + "        Tổng" + ColorConfig.RESET + " tiền hóa đơn là   " + ColorConfig.CYAN + total + ColorConfig.RESET + " $\n");
        }
        System.out.println(" .-------------------------------------------------------.");
        System.out.println(" |        1. " + ColorConfig.BLUE + "Đặt hàng" + ColorConfig.RESET + "." + "                  2." + ColorConfig.RED + "Thoát" + ColorConfig.RESET + "." + "         |");
        System.out.println(" '-------------------------------------------------------'");
        int choiceShop;
        while (true) {
            do {
                System.out.print("Mời chọn: ");
                choiceShop = Config.getInteger();
            } while (choiceShop > 2);
            switch (choiceShop) {
                case 1:
                    detailOderUser(oderUser);
                    System.out.println("đơn hàng này đã được đặt bạn có muốn mua sản phẩm nữa không");
                    System.out.println("1. có " +"2. không");
                    int choiceMenu;
                    while (true) {
                        do {
                            System.out.print("Mời Chọn: ");
                            choiceMenu = Config.getInteger();
                        } while (choiceMenu > 2);
                        switch (choiceMenu) {
                            case 1:
                                shoppingCart();
                                break;
                            case 2:
                                new ProfileMenu();
                                break;
                            default:
                                System.out.println("nhập 1 hoặc 2 thôi ");
                        }
                    }
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("nhập 1 hoặc 2 thôi ");
            }
        }
    }

    public void detailOderUser(List<Oder> oder) {
        List<Oder> oderList = oderController.showListOder();
        int oderId;
        if (oderController.showListOder().isEmpty()) {
            oderId = 1;
        } else {
            oderId = oderController.showListOder().get(oderController.showListOder().size() - 1).getId() + 1;
        }
        for (int i = 0; i < oder.size(); i++) {
            if (!oder.get(i).isStatus()) {
                for (int j = 0; j < oderList.size(); j++) {
                    if (oderList.get(j).getUser().getId() == oder.get(i).getUser().getId() && oderList.get(j).isStatus() == oder.get(i).isStatus() && oderList.get(j).getListOderUser().size() == oder.get(i).getListOderUser().size()) {
                        System.out.println("trùng ");
                        new ProfileMenu();
                    }
                }
                List<FastFood> fastFoods = oder.get(i).getListOderUser();
                Oder oderUser = new Oder(oderId, fastFoods, user, false);
                oderController.updateOder(oderUser);
                System.out.println("Đặt hàng thành công !!!");
                new ProfileMenu();
            }
        }
    }
}
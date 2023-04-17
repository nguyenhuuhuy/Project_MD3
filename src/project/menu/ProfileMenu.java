package project.menu;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.UserController;
import project.model.Role;
import project.model.RoleName;
import project.model.User;

import project.view.oderView.OderView;
import project.view.userView.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfileMenu {
    UserController userController = new UserController();

    public ProfileMenu() {
        User user = userController.getUserLogin();
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName() == RoleName.ADMIN) {
                System.out.println("                                                .---------------------------------------------------------." + "\n" +
                        "                                                |                    " + ColorConfig.RED + " ADMIN MANAGE " + ColorConfig.RESET + "                       |" + "\n" +
                        "                                                |---------------------------------------------------------|" + "\n" +
                        "                                                |                 1. Quản lý Category.                    |" + "\n" +
                        "                                                |                 2. Quản lý FastFood.                    |" + "\n" +
                        "                                                |                 3. Quản lý User.                        |" + "\n" +
                        "                                                |                 4. Quản lý đơn hàng.                    |" + "\n" +
                        "                                                |                 5. Tổng doanh thu.                      |" + "\n" +
                        "                                                |                 6. " + ColorConfig.RED + "Đăng xuất" + ColorConfig.RESET + "." + "                           |" + "\n" +
                        "                                                '---------------------------------------------------------'");
                int menuAdmin;
                while (true) {
                    do {
                        System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                                "|                                                      |" + ColorConfig.RESET);
                        System.out.print(ColorConfig.BLUE_ITALIC + "     Mời chọn số : ➯ " + ColorConfig.RESET);
                        menuAdmin = Config.getInteger();
                        System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                                "'------------------------------------------------------'" + ColorConfig.RESET);
                        if (menuAdmin > 6) {
                            System.out.println("    Bạn nhìn xem có mấy số ở " + ColorConfig.RED + "menu " + ColorConfig.RESET + "thế !!!\uD83D\uDE41\uD83D\uDE41\uD83D\uDE41");
                        }
                    } while (menuAdmin > 6);
                    switch (menuAdmin) {
                        case 1:
                            new CategoryMenuAdm();
                            break;
                        case 2:
                            new FastFoodMenuAdm();
                            break;
                        case 3:
                            new UserMenuAdm();
                            break;
                        case 4:
                            new OderView().showListOder();
                            break;
                        case 5:
                            new OderView().totalAllOder();
                            break;
                        case 6:
                            new UserView().logout();
                        default:
                            System.out.println("  Chỉ có " + ColorConfig.RED + "1 " + ColorConfig.RESET + "----> " + ColorConfig.RED + "5 " + ColorConfig.RESET + " thôi bạn ơi, xin bạn đấy chọn lại đi !!!\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22");
                    }
                }

            } else if (roles.get(0).getName() == RoleName.PM) {
                System.out.println("                                                .---------------------------------------------------------." + "\n" +
                        "                                                |                    " + ColorConfig.RED + " PM MANAGE " + ColorConfig.RESET + "                          |" + "\n" +
                        "                                                |---------------------------------------------------------|" + "\n" +
                        "                                                |                 1. Đổi mật khẩu.                        |" + "\n" +
                        "                                                |                 2. Quản lý Category.                    |" + "\n" +
                        "                                                |                 3. Quản lý FastFood.                    |" + "\n" +
                        "                                                |                 4. Khóa tài khoản user.                 |" + "\n" +
                        "                                                |                 5. Quản lý đơn hàng.                    |" + "\n" +
                        "                                                |                 6. " + ColorConfig.RED + "Đăng xuất" + ColorConfig.RESET + ".                           |" + "\n" +
                        "                                                '---------------------------------------------------------'\n");
                int menuPm;
                while (true) {
                    do {
                        System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                                "|                                                      |" + ColorConfig.RESET);
                        System.out.print(ColorConfig.BLUE_ITALIC + "     Mời chọn số : ➯ " + ColorConfig.RESET);
                        menuPm = Config.getInteger();
                        System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                                "'------------------------------------------------------'" + ColorConfig.RESET);
                        if (menuPm > 6) {
                            System.out.println("    Bạn nhìn xem có mấy số ở " + ColorConfig.RED + "menu " + ColorConfig.RESET + "thế !!!\uD83D\uDE41\uD83D\uDE41\uD83D\uDE41");
                        }
                    } while (menuPm > 6);
                    switch (menuPm) {
                        case 1:
                            new UserView().changePassword();  // đổi mật khẩu
                            break;
                        case 2:
                            new CategoryMenuAdm();    // quản lý phân loại sản phẩm
                            break;
                        case 3:
                            new FastFoodMenuAdm();    // quản lý sản phẩm
                            break;
                        case 4:
                            new UserView().blockUser();   // khóa tài khoản user
                            break;
                        case 5:
                            new OderView().showListOder();  // quản lý đơn đặt hàng
                            break;
                        case 6:
                            new UserView().logout();
                        default:
                            System.out.println("  Chỉ có " + ColorConfig.RED + "1 " + ColorConfig.RESET + "----> " + ColorConfig.RED + "6 " + ColorConfig.RESET + " thôi bạn ơi, xin bạn đấy chọn lại đi !!!\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22");
                    }
                }
            } else if (roles.get(0).getName() == RoleName.USER) {

                int menuUser;
                System.out.println("                                               .-------------------------------------------------------------." + "\n" +
                        "                                               |                       " + ColorConfig.BLUE + " USER MANAGE " + ColorConfig.RESET + "                         |" + "\n" +
                        "                                               |-------------------------------------------------------------|" + "\n" +
                        "                                               |                  1. Sửa password.                           |" + "\n" +
                        "                                               |                  2. Sửa Thông Tin User.                     |" + "\n" +
                        "                                               |                  3. Đặt hàng FastFood.                      |" + "\n" +
                        "                                               |                  4. Xem đơn hàng đã đặt.                    |" + "\n" +
                        "                                               |                  5. Xem chi tiết sản phẩm.                  |" + "\n" +
                        "                                               |                  6. " + ColorConfig.RED + "Đăng xuất" + ColorConfig.RESET + ".                              |" + "\n" +
                        "                                               '-------------------------------------------------------------'\n");
                while (true) {
                    do {
                        System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                                "|                                                      |" + ColorConfig.RESET);
                        System.out.print(ColorConfig.BLUE_ITALIC + "     Mời chọn số : ➯ " + ColorConfig.RESET);
                        menuUser = Config.getInteger();
                        System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                                "'------------------------------------------------------'" + ColorConfig.RESET);
                        if (menuUser > 6) {
                            System.out.println("    Bạn nhìn xem có mấy số ở " + ColorConfig.RED + "menu " + ColorConfig.RESET + "thế !!!\uD83D\uDE41\uD83D\uDE41\uD83D\uDE41");
                        }
                    } while (menuUser > 6);
                    switch (menuUser) {
                        case 1:
                            new UserView().changePassword();  // đổi pass chưa check hết
                            break;
                        case 2:
                            new UserView().UpdateUser();    // tạm ổn
                            break;
                        case 3:
                            new UserView().shoppingCart();   // tạm ổn cần check thêm
                            break;
                        case 4:
                            new UserView().detailHistory();
                            break;
                        case 5:
                            new UserView().likeFastFood();  // đã like và comment được ok nhất
                        case 6:
                            new UserView().logout();
                        default:
                            System.out.println("  Chỉ có " + ColorConfig.RED + "1 " + ColorConfig.RESET + "----> " + ColorConfig.RED + "5 " + ColorConfig.RESET + " thôi bạn ơi, xin bạn đấy chọn lại đi !!!\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22");
                    }
                }
            }
        }
    }
}

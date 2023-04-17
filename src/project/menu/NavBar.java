package project.menu;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.UserController;
import project.model.User;
import project.view.fastFoodView.FastFoodView;
import project.view.userView.UserView;


public class NavBar {
    UserController userController = new UserController();

    public NavBar() {
        User user = userController.getUserLogin();

        if (user != null) {
            System.out.println(
                    "                                              .————————————————————————————————————————————————————————————————.\n" +
                            "                                                            \uD83E\uDD17\uD83E\uDD17     WELCOME  " + ColorConfig.BLUE + user.getName() + ColorConfig.RESET + "    \uD83E\uDD17\uD83E\uDD17                    \n" +
                            "                                              '————————————————————————————————————————————————————————————————'\n");
            new ProfileMenu();
//            System.out.println("1.vào phần phân quyền.");
//            int chooseMenu = Config.getInteger();
//            switch (chooseMenu){
//                case 1:
//                    new ProfileMenu();
//                    break;
//            }
        } else {
            System.out.println(
                    "                                              .————————————————————————————————————————————————————————————————.\n" +
                            "                                              ║              " + ColorConfig.BLUE_ITALIC + "\uD83D\uDC9E\uD83D\uDC9E" + "WELCOME TO HUY RESTAURANT" + "\uD83D\uDC9E\uD83D\uDC9E" + ColorConfig.RESET + "                ║\n" +
                            "                                              ║----------------------------------------------------------------║\n" +
                            "                                              ║                  1. Đăng Ký.                                   ║\n" +
                            "                                              ║                  2. Đăng Nhập.                                 ║\n" +
                            "                                              ║                  3. Danh sách sản phẩm.                        ║\n" +
                            "                                              ║                  4. Tìm kiếm sản phẩm.                         ║\n" +
                            "                                              ║                  5. Top 10 đồ ăn bán chạy nhất.                ║\n" +
                            "                                              ║                  6. Top 10 sản phẩm có like nhiều nhất.        ║\n" +
                            "                                              ║                  7. " + ColorConfig.RED + "Thoát trương trình" + ColorConfig.RESET + ".                        ║\n" +
                            "                                              '————————————————————————————————————————————————————————————————'\n")
            ;
            int choose;
            while (true) {
                do {
                    System.out.println(".------------------------------------------------------.");
                    System.out.println("|                                                      |");
                    System.out.print("     Mời chọn số: ");
                    choose = Config.getInteger();
                    System.out.println("|                                                      |");
                    System.out.println("'------------------------------------------------------'\n");
                    if (choose > 7) {
                        System.out.println("          Nhập quá " + ColorConfig.RED + "6 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                    }
                } while (choose > 7);
                switch (choose) {
                    case 1:
                        new UserView().formRegister();
                        break;
                    case 2:
                        new UserView().formLogin();
                        break;
                    case 3:
                        new FastFoodView().showTableFastFood();
                        break;
                    case 4:
                        new FastFoodView().searchFastFood();  // bơ sờ phếch
                        break;
                    case 5:
                        // chưa làm
                        break;
                    case 6:
                        new FastFoodView().topLike();  // bơ sờ phếch
                        break;
                    case 7:
                        System.out.println(ColorConfig.BLUE + "                                    \uD83D\uDC93\uD83D\uDC93\uD83D\uDC93Cảm ơn đã sử dụng dịch vụ của chúng tôi chúc bạn có một này mới vui vẻ!!! \uD83D\uDC93\uD83D\uDC93\uD83D\uDC93" + ColorConfig.RESET);
                        System.exit(0);
                    default:
                        System.out.println("Có từ " + ColorConfig.RED + "1 " + ColorConfig.RESET + "-----> " + ColorConfig.RED + "7 " + ColorConfig.RESET + "thôi bạn ơi, xin đó nhập lại đi !!!\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");
                }
            }

        }

    }
}

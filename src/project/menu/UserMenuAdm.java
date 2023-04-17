package project.menu;

import project.config.ColorConfig;
import project.config.Config;
import project.view.userView.UserView;

public class UserMenuAdm {
    public UserMenuAdm() {
        System.out.println("                                                .---------------------------------------------------------." + "\n" +
                "                                                |                    " + ColorConfig.BLUE + " USER  MANAGE " + ColorConfig.RESET + "                       |" + "\n" +
                "                                                |---------------------------------------------------------|" + "\n" +
                "                                                |                 1. Xem thông tin user.                  |" + "\n" +
                "                                                |                 2. Khóa tài khoản user.                 |" + "\n" +
                "                                                |                 3. Thay đổi Role cho user.              |" + "\n" +
                "                                                |                 4. Xóa tài khoản user.                  |" + "\n" +
                "                                                |                 5. " + ColorConfig.RED + "Quay lại Menu" + ColorConfig.RESET + ".                       |" + "\n" +
                "                                                '---------------------------------------------------------'");
        int chooseManu;
        while (true) {
            do {
                System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                        "|                                                      |" + ColorConfig.RESET);
                System.out.print(ColorConfig.BLUE_ITALIC + "     Mời chọn số : ➯ " + ColorConfig.RESET);
                chooseManu = Config.getInteger();
                System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                        "'------------------------------------------------------'" + ColorConfig.RESET);
            } while (chooseManu > 5);
            switch (chooseManu) {
                case 1:
                    new UserView().showListUser();
                    break;
                case 2:
                    new UserView().blockUser();
                    break;
                case 3:
                    new UserView().changeRole();
                    break;
                case 4:
                    new UserView().deleteByUser();
                    break;
                case 5:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Chỉ được chọn từ 1 --> 4 vui lòng chọn lại!");
            }
        }
    }
}

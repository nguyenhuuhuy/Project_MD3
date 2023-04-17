package project.view.oderView;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.FastFoodController;
import project.controller.OderController;
import project.controller.UserController;
import project.menu.CategoryMenuAdm;
import project.menu.ProfileMenu;
import project.model.User;
import project.model.food.FastFood;
import project.model.oderList.Oder;

import java.util.*;

public class OderView {
    FastFoodController fastFoodController = new FastFoodController();
    List<FastFood> fastFoodList = fastFoodController.showListFood();
    OderController oderController = new OderController();
    List<Oder> oderList = oderController.showListOder();
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    User user = userController.getUserLogin();

    // xem danh sách đơn hàng
    public void showListOder() {
        System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |          TÊN NGƯỜI ĐẶT HÀNG        |                 DANH SÁCH ĐẶT ĐỒ               |             TÌNH TRẠNG                  |");
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < oderList.size(); i++) {
            System.out.printf(" |  %-4s|            %-20s    |                        %-15s         |              %-10s                 |\n", oderList.get(i).getId(), oderList.get(i).getUser().getName(), oderList.get(i).getListOderUser().size(), oderList.get(i).isStatus() ? "Đã duyệt" : "Chờ duyệt");
        }
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        System.out.println("                   .----------------------------------------------------------------.");
        System.out.println("                   |        1. " + ColorConfig.BLUE + "XEM CHI TIẾT" + ColorConfig.RESET + "." + "                     2." + ColorConfig.CYAN + "THOÁT " + ColorConfig.RESET + ".          |");
        System.out.println("                   '----------------------------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("Mời chọn: ");
                choice = Config.getInteger();
                if (choice > 2) {
                    System.out.println("Nhập quá 3 rồi bạn.");
                }
            } while (choice > 2);
            switch (choice) {
                case 1:
                    showListUserFindByIdAndAcceptListOder();
                    break;
                case 2:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Nhập từ 1 -------> 2 thôi quỳ !!!");
            }
        }
    }

    // chi tiết đơn hàng và phê duyệt đặt hàng
    public void showListUserFindByIdAndAcceptListOder() {
        System.out.print("Nhập " + ColorConfig.RED + "id " + ColorConfig.RESET + "để xem chi tiết đơn đặt hàng: ");
        int targetId = Config.getInteger();
        if (oderController.detailOder(targetId) == null) {
            System.out.println("Không tìm thấy đơn hàng !!!");
        } else {
            Oder oder = oderController.detailOder(targetId);
            User user = oder.getUser();                                            // có thằng user đã mua hàng
            List<FastFood> fastFoodListUser = oder.getListOderUser();              // có list sản phẩm của user mua hàng
            System.out.println("                                                    " + ColorConfig.CYAN + user.getName() + ColorConfig.RESET + " Đã đặt hàng !!!");
            System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
            System.out.println(" |                DANH SÁCH ĐẶT HÀNG                         |       SỐ LƯỢNG         |          GIÁ         |          GIÁ TIỀN        |");
            System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
            int total = 0;
            int totalAll = 0;
            for (int i = 0; i < fastFoodListUser.size(); i++) {
                total = fastFoodListUser.get(i).getPrice() * fastFoodListUser.get(i).getQuantity();
                System.out.printf(" |                     %-20s                  |         %-8s       |         %-10s   |           %-12s$  |\n", fastFoodListUser.get(i).getFoodName(), fastFoodListUser.get(i).getQuantity(), fastFoodListUser.get(i).getPrice(), total);
                totalAll += fastFoodListUser.get(i).getPrice() * fastFoodListUser.get(i).getQuantity();
            }
            System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println(" |                                        " + ColorConfig.RED + "TỔNG TIỀN" + ColorConfig.RESET + "                                                          |           " + totalAll + "         $  |");
            System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'\n");
            System.out.println("                   .-------------------------------------------------------------------.");
            System.out.println("                   |        1. " + ColorConfig.BLUE + "XÁC NHẬN ĐẶT HÀNG" + ColorConfig.RESET + "." + "                 2." + ColorConfig.CYAN + "QUAY LẠI" + ColorConfig.RESET + ".          |");
            System.out.println("                   '-------------------------------------------------------------------'");
            int choiceOder;
            while (true) {
                do {
                    System.out.print("Mời chọn: ");
                    choiceOder = Config.getInteger();
                } while (choiceOder > 2);
                switch (choiceOder) {
                    case 1:
                        if (!oder.isStatus()) {
                            List<Oder> oderUser = user.getListOder();
                            for (int i = 0; i < oderUser.size(); i++) {
                                if (oderUser.get(i).getUser().getId() == oder.getUser().getId()) {
                                    oder.setStatus(true);
                                    oderUser.get(oderUser.size() - 1).setStatus(true);
                                    userController.updateUser(user);
                                    oderController.saveListOder(oder);
                                }
                            }
                            System.out.println("Đã phê duyệt đơn hàng !!!");
                            while (true) {
                                System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại: ");
                                String backMenu = Config.getString();
                                if (backMenu.trim().equalsIgnoreCase("back")) {
                                    showListOder();
                                }
                            }
                        } else {
                            System.out.println(ColorConfig.BLUE + " Đơn này đã được xác nhận rồi !!!" + ColorConfig.RESET);
                            while (true) {
                                System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại: ");
                                String backMenu = Config.getString();
                                if (backMenu.trim().equalsIgnoreCase("back")) {
                                    showListOder();
                                }
                            }
                        }
                    case 2:
                        showListOder();
                        break;
                    default:
                        System.out.println("chỉ được chọn 1 và 2 mời nhập lại !!!!");
                }
            }
        }
    }

    public void totalAllOder() {
        int totalAll = 0;
        for (int i = 0; i < oderList.size(); i++) {
            int total = 0;
            if (oderList.get(i).isStatus()) {
                System.out.println(ColorConfig.BLUE + "                                                    Hóa đơn đặt hàng thứ " + ColorConfig.RESET + ColorConfig.RED + oderList.get(i).getId() + ColorConfig.RESET + " Của " + ColorConfig.CYAN + oderList.get(i).getUser().getName() + ColorConfig.RESET);

                System.out.println(" ☺.-------------------------------------------------------------------------------------------------------------------------------------.☺");
                System.out.println(" |             Tên Sản phẩm             |         Giá sản phẩm         |   Số Lượng   |      Loại sản phẩm      |     trạng thái          |");
                System.out.println(" '----------------------------------------------------------------------------------------------------------------------------------------'");
                List<FastFood> fastFoods = oderList.get(i).getListOderUser();
                for (int j = 0; j < fastFoods.size(); j++) {
                    System.out.printf(" |               %-11s            |           %-10s$        |      %-5s   |     %-16s    |    %-16s     |\n", fastFoods.get(j).getFoodName(), fastFoods.get(j).getPrice(), fastFoods.get(j).getQuantity(), fastFoods.get(j).getCategory().getCategoryName(), oderList.get(i).isStatus() ? "Đã chuyển hàng" : "Đang chuyển hàng");
                    total += fastFoods.get(j).getPrice() * fastFoods.get(j).getQuantity();
                }
                System.out.println(" '----------------------------------------------------------------------------------------------------------------------------------------'\n");
            }
            totalAll += total;
        }
        System.out.println("                                         .---------------------------------------------.");
        System.out.println("                                                 " + ColorConfig.RED + "TỔNG" + ColorConfig.RESET + " DOANH THU CỦA " + ColorConfig.RED + oderList.size() + ColorConfig.RESET + " LÀ: " + ColorConfig.CYAN_ITALIC + totalAll + ColorConfig.RESET + " $");
        System.out.println("                                         '---------------------------------------------'");
        while (true) {
            System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại Menu: ");
            String backMenu = Config.getString();
            if (backMenu.trim().equalsIgnoreCase("back")) {
                new ProfileMenu();
                break;
            }
        }
    }
}


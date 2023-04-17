package project.view.fastFoodView;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.CategoryController;
import project.controller.FastFoodController;
import project.controller.UserController;
import project.menu.FastFoodMenuAdm;
import project.menu.NavBar;
import project.menu.ProfileMenu;
import project.model.Role;
import project.model.RoleName;
import project.model.User;
import project.model.category.Category;
import project.model.commentfood.CommentFood;
import project.model.food.FastFood;
import project.view.categoryView.CategoryView;
import project.view.userView.UserView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FastFoodView {
    UserController userController = new UserController();
    User user = userController.getUserLogin();
    CategoryController categoryController = new CategoryController();
    FastFoodController fastFoodController = new FastFoodController();
    List<FastFood> fastFoodList = fastFoodController.showListFood();
    List<Category> categoryList = categoryController.showListCategory();

    // bảng hiển cả danh sách hết hạn
    public void showTableAll() {
        System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < fastFoodList.size(); i++) {
            System.out.printf(" |  %-4s|    %-16s |    %-14s |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getPrice(), fastFoodList.get(i).editExpirationDate(), fastFoodList.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(i).getQuantity(), fastFoodList.get(i).getCategory().getCategoryName());
        }
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
    }

    // hiện danh sách sản phẩm theo ngày còn hạn ok
    public void showTableFastFood() {
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
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName() == RoleName.ADMIN) {
                Config.backFastFoodMenu();
            } else if (roles.get(0).getName() == RoleName.PM) {

            } else if (roles.get(0).getName() == RoleName.USER) {
                new ProfileMenu();
            }
        }
        System.out.println("            .----------------------------------------------------------------------------------.");
        System.out.println("            | " + ColorConfig.GREEN + "        1. Thêm sản phẩm vào giỏ hàng" + ColorConfig.RESET + ColorConfig.RED + "                     2. Thoát. " + ColorConfig.RESET + "             |");
        System.out.println("            '----------------------------------------------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("Mời chọn số: ");
                choice = Config.getInteger();
            } while (choice > 2);
            switch (choice) {
                case 1:
                    if (user == null) {
                        System.out.println(ColorConfig.RED + "                          Bạn chưa đăng nhập, cần đăng nhập để thêm sản phẩm !!!" + ColorConfig.RESET);
                        System.out.println("            .----------------------------------------------------------------------------------.");
                        System.out.println("            | " + ColorConfig.BLUE + "         1. Tới trang đăng nhập. " + ColorConfig.RESET + ColorConfig.RED + "                     2. Thoát. " + ColorConfig.RESET + "                 |");
                        System.out.println("            '----------------------------------------------------------------------------------'");
                        int choiceLogin;
                        while (true) {
                            do {
                                System.out.print("Mời chọn số: ");
                                choiceLogin = Config.getInteger();
                            } while (choiceLogin > 2);
                            switch (choiceLogin) {
                                case 1:
                                    new UserView().formLogin();
                                    break;
                                case 2:
                                    new NavBar();
                                    break;
                                default:
                                    System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                            }
                        }
                    }
                    break;
                case 2:
                    new NavBar();
                    break;
                default:
                    System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
            }
        }
    }

    // thêm mới sản phẩm ok
    public void ceateFastFood() {
        int foodId;
        if (fastFoodList.isEmpty()) {
            foodId = 1;
        } else {
            foodId = Config.findMaxIdByFastFood(fastFoodList) + 1;
        }
        String foodName;
        boolean check;
        do {
            System.out.print("Nhập tên sản Phẩm: ");
            foodName = Config.getString();
            if (foodName.trim().equals("")) {
                System.out.print(ColorConfig.RED + "Không được để trống." + "\n" + ColorConfig.RESET);
                check = false;
            } else if (foodName.length() < 5 || foodName.length() > 40) {
                System.out.print(ColorConfig.RED + "Tên sản phẩm không được dưới 5 ký tự.\n" + ColorConfig.RESET);
                check = false;
            } else if (!foodName.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                System.out.print(ColorConfig.RED + "Không được nhập số.\n" + ColorConfig.RESET);
                check = false;
            } else {
                check = true;
            }
        } while (!check);
        int prince;
        do {
            System.out.print("Nhập giá sản phẩm: ");
            prince = Config.getInteger();
        } while (prince <= 0);
        int quantity;
        do {
            System.out.print("Nhập số lượng sản phẩm: ");
            quantity = Config.getInteger();
        } while (quantity < 0);
        int year;
        do {
            System.out.print("Nhập năm sản suất: ");
            year = Config.getInteger();
            if (year <= 2000) {
                System.out.println(ColorConfig.RED + "Năm sản xuất phải lơn hơn năm bây giờ!!" + ColorConfig.RESET);
            }
        } while (year <= 2000);
        int mouth;
        do {
            System.out.print("Nhập tháng sản suất: ");
            mouth = Config.getInteger();
            if (mouth < 1) {
                System.out.println(ColorConfig.RED + "không được nhập số dưới 0" + ColorConfig.RESET);
            } else if (mouth > 12) {
                System.out.println(ColorConfig.RED + "chỉ có 12 tháng vui lòng nhập lại" + ColorConfig.RESET);
            }
        } while (mouth < 1 || mouth > 12);
        int day;
        do {
            System.out.print("Nhập ngày sản suất: ");
            day = Config.getInteger();
            if (day < 1) {
                System.out.println(ColorConfig.RED + "không được nhập số dưới 0" + ColorConfig.RESET);
            } else if (day > 31) {
                System.out.println(ColorConfig.RED + "Chỉ có 31 ngày vui lòng nhập lại" + ColorConfig.RESET);
            }
        } while (day < 1 || day > 31);
        System.out.println(ColorConfig.BLUE + "     Bảng phân loại sản phẩm" + ColorConfig.RESET);
        new CategoryView().tableCategoly();
        int categoryId;
        while (true) {
            System.out.print("Nhập loại sản phẩm theo " + ColorConfig.RED + "id" + ColorConfig.RESET + " : ");
            categoryId = Config.getInteger();
            if (categoryController.detailFastFood(categoryId) == null) {
                System.out.println(ColorConfig.RED_BRIGHT + "id không tồn tại." + ColorConfig.RESET);
            } else {
                FastFood fastFood = new FastFood(foodId, foodName, prince, quantity);
                fastFood.setExpirationDate(year, mouth, day);                          // xét ngày tháng năm
                fastFood.setCategory(categoryList.get(categoryId - 1));                // phân loại sản phẩm theo category
                fastFoodController.createFastFood(fastFood);
                System.out.println(ColorConfig.GREEN + "--->Thêm mới thành công!!" + ColorConfig.RESET);
                Config.backFastFoodMenu();
            }
        }
    }

    public void editFastFood() {
        showTableAll();
        while (true) {
            System.out.print("Chọn cản phẩm muốn sửa theo " + ColorConfig.RED + "id" + ColorConfig.RESET + ": ");
            int targetId = Config.getInteger();
            if (fastFoodController.detailFastFood(targetId) == null) {
                System.out.println(ColorConfig.RED + "Không tìm thấy sản phẩm !!" + ColorConfig.RESET);
            } else {
                String newFoodName;
                Boolean check;
                do {
                    System.out.print("Nhập tên sản Phẩm: ");
                    newFoodName = Config.getString();
                    if (newFoodName.trim().equals("")) {
                        System.out.print(ColorConfig.RED + "Không được để trống." + "\n" + ColorConfig.RESET);
                        check = false;
                    } else if (newFoodName.length() < 5 || newFoodName.length() > 40) {
                        System.out.print(ColorConfig.RED + "Tên học sinh không được dưới 5 ký tự.\n" + ColorConfig.RESET);
                        check = false;
                    } else if (!newFoodName.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                        System.out.print(ColorConfig.RED + "Không được nhập số.\n" + ColorConfig.RESET);
                        check = false;
                    } else {
                        check = true;
                    }
                } while (!check);
                int newPrince;
                while (true) {
                    System.out.print("Nhập giá sản phẩm: ");
                    newPrince = Config.getInteger();
                    if (newPrince > 0) {
                        break;
                    }
                }
                int newQuantity;
                while (true) {
                    System.out.print("Nhập số lượng sản phẩm: ");
                    newQuantity = Config.getInteger();
                    if (newQuantity >= 0) {
                        break;
                    }
                }
                int newYear;
                while (true) {
                    System.out.print("Nhập năm sản suất: ");
                    newYear = Config.getInteger();
                    if (newYear > 2000) {
                        break;
                    } else {
                        System.out.println(ColorConfig.RED + "Năm sản xuất phải lơn hơn năm bây giờ!!" + ColorConfig.RESET);
                    }
                }
                int newMouth;
                do {
                    System.out.print("Nhập tháng sản suất: ");
                    newMouth = Config.getInteger();
                    if (newMouth < 1) {
                        System.out.println(ColorConfig.RED + "không được nhập số dưới 0" + ColorConfig.RESET);
                    } else if (newMouth > 12) {
                        System.out.println(ColorConfig.RED + "chỉ có 12 tháng vui lòng nhập lại" + ColorConfig.RESET);
                    }
                } while (newMouth < 1 || newMouth > 12);
                int newDay;
                do {
                    System.out.print("Nhập ngày sản suất: ");
                    newDay = Config.getInteger();
                    if (newDay < 1) {
                        System.out.println(ColorConfig.RED + "không được nhập số dưới 0" + ColorConfig.RESET);
                    } else if (newDay > 31) {
                        System.out.println(ColorConfig.RED + "Chỉ có 31 ngày vui lòng nhập lại" + ColorConfig.RESET);
                    }
                } while (newDay < 1 || newDay > 31);
                System.out.println("Bảng phân loại sản phẩm");
                new CategoryView().tableCategoly();
                int categoryId;
                while (true) {
                    System.out.print("Nhập loại sản phẩm theo " + ColorConfig.RED + "id" + ColorConfig.RESET + ": ");
                    categoryId = Config.getInteger();
                    if (categoryController.detailFastFood(categoryId) == null) {
                        System.out.println(ColorConfig.RED_BRIGHT + "id không tồn tại." + ColorConfig.RESET);
                    } else {
                        FastFood editFastFood = new FastFood(targetId, newFoodName, newPrince, newQuantity);
                        editFastFood.setExpirationDate(newYear, newMouth, newDay);                          // xét ngày tháng năm
                        editFastFood.setCategory(categoryList.get(categoryId - 1));                // phân loại sản phẩm theo category
                        fastFoodController.createFastFood(editFastFood);
                        System.out.println(ColorConfig.GREEN + "Sửa thành công!!" + ColorConfig.RESET);
                        Config.backFastFoodMenu();
                    }
                }
            }
        }
    }

    // xóa ok
    public void deleteFastFood() {
        System.out.println(ColorConfig.BLUE + "                                    -----------------  List FastFood All  -----------------" + ColorConfig.RESET);
        showTableAll();
        while (true) {
            System.out.print("Nhập " + ColorConfig.RED_BRIGHT + "id" + ColorConfig.RESET + " của sản phẩm muốn xóa: ");
            int targetId = Config.getInteger();
            if (fastFoodController.detailFastFood(targetId) == null) {
                System.out.println(ColorConfig.RED + "Không tìm thấy sản phẩm !!!" + ColorConfig.RESET);
            } else {
                while (true) {
                    try {
                        System.out.print(ColorConfig.GREEN + "                                      Bạn có chắc muốn xóa không !!!\n" + ColorConfig.RESET + ColorConfig.BLUE + "                                           1.Yes" + ColorConfig.RESET + ColorConfig.RED + "         2.No\n" + ColorConfig.RESET);
                        int deleteFastFood;
                        System.out.print("Mời Chọn: ");
                        deleteFastFood = Config.getInteger();
                        switch (deleteFastFood) {
                            case 1:
                                fastFoodController.deleteFastFood(targetId);
                                System.out.println(ColorConfig.GREEN + "----> Xóa thành công !!!" + ColorConfig.RESET);
                                Config.backFastFoodMenu();
                            case 2:
                                new FastFoodMenuAdm();
                        }
                    } catch (NumberFormatException er) {
                        System.out.println(ColorConfig.RED_BRIGHT + "Nhập sai định dạng, vui lòng nhập lại!" + ColorConfig.RESET);
                    }
                }
            }
        }
    }

    public void detailFastFood() {
        System.out.println(ColorConfig.BLUE + "                             \uD83C\uDF39——————————————————————  SẢN PHẨM HIỆN ĐANG BÁN  ——————————————————————\uD83C\uDF39" + ColorConfig.RESET);
        System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
        System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
        System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
        for (int i = 0; i < fastFoodList.size(); i++) {
            System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getPrice(), fastFoodList.get(i).editExpirationDate(), fastFoodList.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(i).getQuantity(), fastFoodList.get(i).getCategory().getCategoryName());
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
                            for (int h = 0; h < fastFoodList.size(); h++) {
                                if (fastFoodList.get(h).getId() == targetId && !fastFoodList.get(h).checkDay()) {
                                    System.out.println("\n                                                  Sản phẩm bạn tìm hiện tại của bạn đã " + ColorConfig.RED + "hết hạn" + ColorConfig.RESET + " !!!     \n");
                                    System.out.println("                             \uD83E\uDDE1——————————————————————  CHI TIẾT CỦA SẢN PHẨM " + ColorConfig.BLUE + fastFoodList.get(h).getFoodName() + ColorConfig.RESET + "  ——————————————————————\uD83E\uDDE1");
                                    System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(h).getId(), fastFoodList.get(h).getFoodName(), fastFoodList.get(h).getPrice(), fastFoodList.get(h).editExpirationDate(), fastFoodList.get(h).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(h).getQuantity(), fastFoodList.get(h).getCategory().getCategoryName());
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.println("                                 ☺.------------------------------------------------.☺");
                                    System.out.println("                                 |                    " + ColorConfig.BLUE + " LIKE " + ColorConfig.RESET + "                         |");
                                    System.out.println("                                 '---------------------------------------------------'");
                                    System.out.printf("                                 |                      %-10s                   |\n", fastFoodList.get(h).getLikeUser().size());
                                    System.out.println("                                 '---------------------------------------------------'");
                                    List<CommentFood> commentFoodList = fastFoodList.get(h).getCommentFoodList();  // phần commnet
                                    System.out.println(" ☺.----------------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" | " + ColorConfig.BLUE + "       Người đã đánh giá sản phẩm         " + ColorConfig.RESET + " |   " + ColorConfig.GREEN_ITALIC + "                                  COMMENT                                              " + ColorConfig.RESET + "    |");
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                    for (int k = 0; k < commentFoodList.size(); k++) {
                                        System.out.printf(" |         %-30s     |                      %-60s            |\n", commentFoodList.get(k).getUser().getName(), commentFoodList.get(k).getComment());
                                    }
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.println("            .-------------------------------------------------------------------------------------.");
                                    System.out.println("            | " + ColorConfig.GREEN + "        1. Xem chi tiết sản phẩm khác. " + ColorConfig.RESET + ColorConfig.BLUE + "                        2. thoát. " + ColorConfig.RESET + "           |");
                                    System.out.println("            '-------------------------------------------------------------------------------------'");
                                    int choiceBack;
                                    while (true) {
                                        do {
                                            System.out.print("Mời chọn số: ");
                                            choiceBack = Config.getInteger();
                                            if (choiceBack > 2) {
                                                System.out.println("          Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                                            }
                                        } while (choiceBack > 2);
                                        switch (choiceBack) {
                                            case 1:
                                                detailFastFood();
                                                break;
                                            case 2:
                                                new FastFoodMenuAdm();
                                                break;
                                            default:
                                                System.out.println(ColorConfig.RED + "                                        Chỉ được chọn 1 và 2 mời chọn lại !!!" + ColorConfig.RESET);
                                        }
                                    }
                                } else if (fastFoodList.get(h).getId() == targetId && fastFoodList.get(h).checkDay()) {
                                    System.out.println("\n                             \uD83E\uDDE1——————————————————————  CHI TIẾT CỦA SẢN PHẨM " + ColorConfig.BLUE + fastFoodList.get(h).getFoodName() + ColorConfig.RESET + "  ——————————————————————\uD83E\uDDE1");
                                    System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", fastFoodList.get(h).getId(), fastFoodList.get(h).getFoodName(), fastFoodList.get(h).getPrice(), fastFoodList.get(h).editExpirationDate(), fastFoodList.get(h).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", fastFoodList.get(h).getQuantity(), fastFoodList.get(h).getCategory().getCategoryName());
                                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.println("                                 ☺.------------------------------------------------.☺");
                                    System.out.println("                                 |                    " + ColorConfig.BLUE + " LIKE " + ColorConfig.RESET + "                         |");
                                    System.out.println("                                 '---------------------------------------------------'");
                                    System.out.printf("                                 |                      %-10s                   |\n", fastFoodList.get(h).getLikeUser().size());
                                    System.out.println("                                 '---------------------------------------------------'");
                                    List<CommentFood> commentFoodList = fastFoodList.get(h).getCommentFoodList();  // phần commnet
                                    System.out.println(" ☺.----------------------------------------------------------------------------------------------------------------------------------------.☺");
                                    System.out.println(" | " + ColorConfig.BLUE + "       Người đã đánh giá sản phẩm         " + ColorConfig.RESET + " |   " + ColorConfig.GREEN_ITALIC + "                                  COMMENT                                              " + ColorConfig.RESET + "    |");
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                    for (int k = 0; k < commentFoodList.size(); k++) {
                                        System.out.printf(" |         %-30s     |                      %-60s            |\n", commentFoodList.get(k).getUser().getName(), commentFoodList.get(k).getComment());
                                    }
                                    System.out.println(" '-------------------------------------------------------------------------------------------------------------------------------------------'");
                                    System.out.println("            .-------------------------------------------------------------------------------------.");
                                    System.out.println("            | " + ColorConfig.GREEN + "        1. Xem chi tiết sản phẩm khác. " + ColorConfig.RESET + ColorConfig.BLUE + "                        2. thoát. " + ColorConfig.RESET + "           |");
                                    System.out.println("            '-------------------------------------------------------------------------------------'");
                                    int choiceBack;
                                    while (true) {
                                        do {
                                            System.out.print("Mời chọn số: ");
                                            choiceBack = Config.getInteger();
                                            if (choiceBack > 2) {
                                                System.out.println("          Nhập quá " + ColorConfig.RED + "2 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                                            }
                                        } while (choiceBack > 2);
                                        switch (choiceBack) {
                                            case 1:
                                                detailFastFood();
                                                break;
                                            case 2:
                                                new FastFoodMenuAdm();
                                                break;
                                            default:
                                                System.out.println(ColorConfig.RED + "                                        Chỉ được chọn 1 và 2 mời chọn lại !!!" + ColorConfig.RESET);
                                        }
                                    }
                                }
                            }
                        }
                    }
                case 2:
                    new FastFoodMenuAdm();
                    break;
                default:
                    System.out.println("Có từ " + ColorConfig.RED + "1 " + ColorConfig.RESET + "-----> " + ColorConfig.RED + "2 " + ColorConfig.RESET + "thôi bạn ơi, xin đó nhập lại đi !!!\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");

            }
        }
    }

    // tìm kiếm bơ phếch
    public void searchFastFood() {
        List<FastFood> searchFood = new ArrayList<>();
        String targetFastFoodByName;
        boolean checkFastFoodName;
        do {
            System.out.print("Nhập Tên sản phẩm muốn tìm kiếm: ");
            targetFastFoodByName = Config.getString();
            if (targetFastFoodByName.trim().equals("")) {
                System.out.print(ColorConfig.RED + "Không được để trống." + "\n" + ColorConfig.RESET);
                checkFastFoodName = false;
            } else if (targetFastFoodByName.length() < 2 || targetFastFoodByName.length() > 20) {
                System.out.print(ColorConfig.RED + "Tên tìm không được dưới 1 ký tự.\n" + ColorConfig.RESET);
                checkFastFoodName = false;
            } else if (!targetFastFoodByName.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                System.out.print(ColorConfig.RED + "Không được nhập số.\n" + ColorConfig.RESET);
                checkFastFoodName = false;
            } else {
                checkFastFoodName = true;
            }
        } while (!checkFastFoodName);
        for (FastFood element : fastFoodList) {
            if (element.getFoodName().toLowerCase().contains(targetFastFoodByName)) {
                searchFood.add(element);
            }
        }
        if (searchFood.isEmpty()) {
            System.out.println(ColorConfig.GREEN + "                               Không tìm thấy sản phẩm bạn đang muốn tìm !!!" + ColorConfig.RESET);
            System.out.println(ColorConfig.BLUE + "                                   Bạn có muốn tìm sản phẩm tiếp không." + ColorConfig.RESET);
            System.out.println("            .----------------------------------------------------------------------------------.");
            System.out.println("            | " + "        1." + ColorConfig.GREEN + " Ấn phím " + ColorConfig.RESET + ColorConfig.RED + "1 " + ColorConfig.RESET + ColorConfig.GREEN + "để tìm kiếm tiếp. " + ColorConfig.RESET + "                     2" + ColorConfig.RED + ". Thoát. " + ColorConfig.RESET + "           |");
            System.out.println("            '----------------------------------------------------------------------------------'");
            int choice;
            while (true) {
                do {
                    System.out.print("Mời chọn số: ");
                    choice = Config.getInteger();
                } while (choice > 2);
                switch (choice) {
                    case 1:
                        searchFastFood();
                    case 2:
                        new NavBar();
                    default:
                        System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                }
            }
        } else {
            System.out.println(ColorConfig.BLUE + "                             \uD83C\uDF39——————————————————————  SẢN PHẨM BẠN ĐANG TÌM  ——————————————————————\uD83C\uDF39" + ColorConfig.RESET);
            System.out.println(" ☺.-----------------------------------------------------------------------------------------------------------------------------------.☺");
            System.out.println(" |  ID  |    Tên Sản phẩm     |   Giá sản phẩm    |           Ngày hết hạn            |   Số Lượng   |          Loại sản phẩm           |");
            System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
            for (int i = 0; i < searchFood.size(); i++) {
                if (searchFood.get(i).checkDay() && searchFood.get(i).getFoodName().toLowerCase().contains(targetFastFoodByName)) {
                    System.out.printf(" |  %-4s|    %-16s |    %-8s$      |    %-16s %-10s    |     %-8s |         %-25s|\n", searchFood.get(i).getId(), searchFood.get(i).getFoodName(), searchFood.get(i).getPrice(), searchFood.get(i).editExpirationDate(), searchFood.get(i).checkDay() ? "(Còn Hạn)" : "(Hết hạn)", searchFood.get(i).getQuantity(), searchFood.get(i).getCategory().getCategoryName());
                    System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
                    System.out.println(ColorConfig.BLUE + "                                     Bạn có muốn tìm sản phẩm tiếp không." + ColorConfig.RESET);
                    System.out.println("            .----------------------------------------------------------------------------------.");
                    System.out.println("            |" + "           1." + ColorConfig.BLUE + " Có." + ColorConfig.RESET + "                                    2. " + ColorConfig.RED + "Không" + ColorConfig.RESET + "                     |");
                    System.out.println("            '----------------------------------------------------------------------------------'");
                    int yesAndNo;
                    while (true) {
                        do {
                            System.out.print("Mời chọn số: ");
                            yesAndNo = Config.getInteger();
                        } while (yesAndNo > 2);
                        switch (yesAndNo) {
                            case 1:
                                searchFastFood();
                            case 2:
                                new NavBar();
                            default:
                                System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                        }
                    }
                } else if (!searchFood.get(i).checkDay() && searchFood.get(i).getFoodName().toLowerCase().contains(targetFastFoodByName)) {
                    System.out.println(ColorConfig.RED + "                    Sản phẩm bạn đang tìm đã hết hạn hiện không còn bán trong cửa hàng nữa !!!" + ColorConfig.RESET);
                    System.out.println(ColorConfig.BLUE + "                                     Bạn có muốn tìm sản phẩm tiếp không." + ColorConfig.RESET);
                    System.out.println("            .----------------------------------------------------------------------------------.");
                    System.out.println("            |" + "           1." + ColorConfig.BLUE + " Có." + ColorConfig.RESET + "                                    2. " + ColorConfig.RED + "Không" + ColorConfig.RESET + "                     |");
                    System.out.println("            '----------------------------------------------------------------------------------'");
                    int choiceYesAndNo;
                    while (true) {
                        do {
                            System.out.print("Mời chọn số: ");
                            choiceYesAndNo = Config.getInteger();
                        } while (choiceYesAndNo > 2);
                        switch (choiceYesAndNo) {
                            case 1:
                                searchFastFood();
                            case 2:
                                new NavBar();
                            default:
                                System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                        }
                    }
                }
            }
            System.out.println(" '--------------------------------------------------------------------------------------------------------------------------------------'");
            System.out.println(ColorConfig.GREEN_BOLD + "                               Bạn có muốn tìm sản phẩm tiếp không." + ColorConfig.RESET);
            System.out.println("            .----------------------------------------------------------------------------------.");
            System.out.println("            |" + "           1." + ColorConfig.BLUE + " Có." + ColorConfig.RESET + "                                    2. " + ColorConfig.RED + "Không" + ColorConfig.RESET + "                     |");
            System.out.println("            '----------------------------------------------------------------------------------'");
            int searchItem;
            while (true) {
                do {
                    System.out.print("Mời chọn số: ");
                    searchItem = Config.getInteger();
                } while (searchItem > 2);
                switch (searchItem) {
                    case 1:
                        searchFastFood();
                        break;
                    case 2:
                        new NavBar();
                        break;
                    default:
                        System.out.println("Nhập sai định dạng mời chọn " + ColorConfig.RED + "1 " + ColorConfig.RESET + "hoặc " + ColorConfig.RED + "2 " + ColorConfig.RESET + "!!!");
                }
            }
        }
    }

    // top 10 sản phẩm có like nhiều nhất
    public void topLike() {
        System.out.println(ColorConfig.BLUE + "                        \uD83C\uDF39——————————————————————  TOP 10 SẢN PHẨM CÓ LIKE CAO NHẤT  ——————————————————————\uD83C\uDF39" + ColorConfig.RESET);
        System.out.println("                                           ☺.------------------------------------------.☺");
        System.out.println("                                           |  ID  |    Tên Sản phẩm     |   Lượt Like    |");
        System.out.println("                                           '---------------------------------------------'");
        Config.bubbleSortList(fastFoodList);
        for (int i = 0; i < fastFoodList.size(); i++) {
            System.out.printf("                                           |  %-4s|    %-16s |       %-4s     |\n", fastFoodList.get(i).getId(), fastFoodList.get(i).getFoodName(), fastFoodList.get(i).getLikeUser().size());
        }
        System.out.println("                                           '---------------------------------------------'\n");
        System.out.println("                                           .--------------------------------------------.");
        System.out.println("                                           |          Bấm phím " + ColorConfig.RED + "1 " + ColorConfig.RESET + "để" + ColorConfig.RED + " quay lại" + ColorConfig.RESET + ".           |");
        System.out.println("                                           '--------------------------------------------'");
        int choice;
        while (true) {
            do {
                System.out.print("Mời chọn: ");
                choice = Config.getInteger();
                if (choice > 1) {
                    System.out.println("                                                  chọn số" + ColorConfig.RED + " 1 " + ColorConfig.RESET + "bạn ơi !!!\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");
                }
            } while (choice > 1);
            switch (choice) {
                case 1:
                    new NavBar();
                    break;
                default:
                    System.out.println("                                Được chọn mỗi" + ColorConfig.RED + " 1 " + ColorConfig.RESET + "thôi ấn số khác làm gì không đọc hướng dẫn à !!! \uD83D\uDE20\uD83D\uDE20\uD83D\uDE20" + ColorConfig.RESET);
            }
        }
    }


}
